package com.kooobao.lm.bizflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.lm.bizflow.dao.ExpireRecordDao;
import com.kooobao.lm.bizflow.dao.TransactionDao;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Operation;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.dao.StockDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Stock;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.rule.dao.RuleDao;
import com.kooobao.lm.rule.entity.DeliveryDayRule;
import com.kooobao.lm.rule.entity.PenaltyRule;

public class DefaultBusinessService implements BusinessService {

	public void expireTransactions() {
		List<Transaction> toExpire = getTransactionDao().findToExpire(
				new Date());
		for (Transaction expire : toExpire) {
			expireOneTransaction(expire);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected void expireOneTransaction(Transaction expire) {
		expire.expire();
		ExpireRecord er = new ExpireRecord();
		er.setDueTime(new Date());
		er.setTransaction(expire);
		getExpireRecordDao().store(er);
	}

	public void updateExpireRecords() {
		Cursor<ExpireRecord> cursor = getExpireRecordDao()
				.findActivateRecords();
		while (cursor.hasNext()) {
			ExpireRecord record = cursor.next();
			updateExpireRecord(record);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected void updateExpireRecord(ExpireRecord record) {
		if (TransactionState.RETURN_EXPIRED == record.getTransaction()
				.getState()) {
			// Update Penalty
			BigDecimal oldPenalty = record.getPenalty();
			PenaltyRule rule = getRuleDao().getPenaltyRule();
			BigDecimal penalty = rule.getPenalty(record.getTransaction()
					.getVisitor(), record.getDueTime(), new Date());
			record.setPenalty(penalty);
			// Update User Remaining
			Visitor visitor = record.getTransaction().getVisitor();
			visitor.changeDeposit(penalty.subtract(oldPenalty).negate(), "");
			// Update User Status
			if (visitor.getDeposit().compareTo(BigDecimal.ZERO) < 0)
				visitor.setStatus(VisitorStatus.LOCKED.name());
			// getVisitorDao().store(visitor);
		}
	}

	public void clearInactivateVisitors() {
		// TODO Auto-generated method stub

	}

	public void reserveStocks() {
		Cursor<Transaction> requested = getTransactionDao().getTransactions(
				TransactionState.BORROW_REQUESTED);
		while (requested.hasNext()) {
			Transaction next = requested.next();
			if (!next.isStockReserved()) {
				// TODO Auto cancel?
				updateStock(next);
			}
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	protected void updateStock(Transaction next) {
		Transaction tran = getTransactionDao().find(next.getOid());
		Stock stock = getStockDao().findByBook(tran.getBook());
		if (stock.getAvailable() > 0) {
			stock.setAvailable(stock.getAvailable() - 1);
			tran.setStockReserved(true);
		}
	}

	public void assumeReceived() {
		Cursor<Transaction> requested = getTransactionDao().getTransactions(
				TransactionState.BORROW_SENT);
		DeliveryDayRule ddr = getRuleDao().getDeliveryDayRule();
		while (requested.hasNext()) {
			Transaction next = requested.next();
			int day = ddr.getDeliveryDay(next);
			assumeReceive(next, day);
		}
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private void assumeReceive(Transaction next, int day) {
		Operation send = next.getOperations().get(
				next.getOperations().size() - 1);
		if (System.currentTimeMillis() - send.getCreateTime().getTime() >= day * 86400000l)
			next.assumeReceived();
	}

	static int FACTOR_CATEGORY = 2;
	static int FACTOR_TAG = 1;

	public void buildBookAssociations() {
		// 3 Scores for same category, 1 scores for each similar tag
		// TODO Make a full recalculate now. Consider change to increasingly
		// calculation later
		getBookDao().clearAssociations();

		Cursor<Book> books = getBookDao().findAll();
		List<Book> bookList = new ArrayList<Book>();
		while (books.hasNext())
			bookList.add(books.next());
		Collections.sort(bookList, new Comparator<Book>() {
			public int compare(Book o1, Book o2) {
				return Long.valueOf(o1.getOid()).compareTo(o2.getOid());
			}
		});
		for (int i = 0; i < bookList.size(); i++) {
			Book iBook = (Book) bookList.get(i);
			for (int j = i; j < bookList.size(); j++) {
				Book jBook = (Book) bookList.get(j);

				int score = 0;
				if (iBook.getCategory().getOid() == jBook.getCategory()
						.getOid()) {
					score += FACTOR_CATEGORY;
				}
				Set<String> tagA = new HashSet<String>();
				tagA.addAll(iBook.getTags());
				Set<String> tagB = new HashSet<String>();
				tagB.addAll(jBook.getTags());

				Collection result = CollectionUtils.intersection(tagA, tagB);
				score += FACTOR_TAG * result.size();

				getBookDao().addAssociation(iBook, jBook, score);
			}
		}
	}

	private TransactionDao transactionDao;

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	private ExpireRecordDao expireRecordDao;

	public ExpireRecordDao getExpireRecordDao() {
		return expireRecordDao;
	}

	public void setExpireRecordDao(ExpireRecordDao expireRecordDao) {
		this.expireRecordDao = expireRecordDao;
	}

	private VisitorDao visitorDao;

	public VisitorDao getVisitorDao() {
		return visitorDao;
	}

	public void setVisitorDao(VisitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	private RuleDao ruleDao;

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	private StockDao stockDao;

	public StockDao getStockDao() {
		return stockDao;
	}

	public void setStockDao(StockDao stockDao) {
		this.stockDao = stockDao;
	}

	private BookDao bookDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
