package com.kooobao.lm.bizflow;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.dao.ExpireRecordDao;
import com.kooobao.lm.bizflow.dao.TransactionDao;
import com.kooobao.lm.bizflow.entity.DeliveryMethod;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.ExpireRecordState;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.dao.RecommendDao;
import com.kooobao.lm.book.dao.StockDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Comment;
import com.kooobao.lm.book.entity.Stock;
import com.kooobao.lm.finance.dao.FinanceOperationDao;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.rule.dao.RuleDao;
import com.kooobao.lm.rule.entity.DueRule;

public class DefaultTransactionService implements TransactionService {

	public Transaction getTransaction(long transId) {
		return getTransactionDao().find(transId);
	}

	public Transaction saveTransaction(Transaction transaction) {
		return getTransactionDao().store(transaction);
	}

	public Transaction requestBorrow(Transaction transaction) {
		Visitor v = getVisitorDao().find(transaction.getVisitor());
		if (!v.getStatus().equals(VisitorStatus.ACTIVE.name()))
			return null;

		transaction.create();
		// Set Due date
		DueRule dueRule = getRuleDao().getDueRule();
		Date dueDate = dueRule.getDueDate(v, transaction.getBook(), new Date());
		transaction.setDueTime(dueDate);

		return getTransactionDao().store(transaction);
	}

	public Transaction approveBorrow(Transaction transaction, Operator operator) {
		Validate.notNull(operator);
		Visitor v = getVisitorDao().find(transaction.getVisitor());
		// Check stock reservation
		if (!transaction.isStockReserved())
			throw new IllegalStateException("Stock Not Reserved");
		// Check User Account
		if (v.getDeposit().compareTo(transaction.getBook().getListPrice()) < 0)
			throw new InsufficientFundException();
		getFinanceOperationDao().changeVisitorDeposit(v,
				transaction.getBook().getListPrice().negate(),
				"Borrow Book " + transaction.getOid(), operator.getId());
		transaction.approve(null == operator ? null : operator.getId());
		return getTransactionDao().store(transaction);
	}

	public Transaction sendBorrow(Transaction transaction, Operator operator,
			String expressInfo) {
		Validate.notNull(operator);
		Validate.isTrue(!DeliveryMethod.EXPRESS.name().equals(
				transaction.getDelivery())
				|| !StringUtils.isEmpty(expressInfo));
		transaction.sendout(operator.getId(), expressInfo);
		return getTransactionDao().store(transaction);
	}

	public Transaction sendReturn(Transaction transaction, Operator operator,
			String expressInfo) {
		Validate.isTrue(!DeliveryMethod.EXPRESS.name().equals(
				transaction.getDelivery())
				|| !StringUtils.isEmpty(expressInfo));
		transaction.sendback(null == operator ? null : operator.getId(),
				expressInfo);
		return getTransactionDao().store(transaction);
	}

	public Transaction confirmReturn(Transaction transaction,
			Operator operator, String comment) {
		ExpireRecord expireRecord = getExpireRecordDao().findByTransaction(
				transaction);
		if (null != expireRecord && expireRecord.isActive()) {
			expireRecord.setReturnTime(new Date());
			expireRecord.setState(ExpireRecordState.INACTIVE);
		}
		transaction.returnReceived(operator.getId(),
				null == expireRecord ? null : expireRecord.getPenalty(),
				comment);
		// Add Stock
		// TODO Set as async operation
		Stock stock = getStockDao().findByBook(transaction.getBook());
		stock.setAvailable(stock.getAvailable() + 1);
		// Return Motegage
		getFinanceOperationDao().changeVisitorDeposit(transaction.getVisitor(),
				transaction.getBook().getListPrice(),
				"Return Book " + transaction.getOid(), operator.getId());

		return getTransactionDao().store(transaction);
	}

	public Transaction cancel(Transaction tran, String reason) {
		Validate.isTrue(!StringUtils.isEmpty(reason));

		// Return Funds
		if (tran.getState() == TransactionState.BORROW_APPROVED)
			getFinanceOperationDao().changeVisitorDeposit(tran.getVisitor(),
					tran.getBook().getListPrice(),
					"User Cancel:" + tran.getOid(), null);
		// Release Stocks
		if (tran.isStockReserved()) {
			Stock stock = getStockDao().findByBook(tran.getBook());
			stock.setAvailable(stock.getAvailable() + 1);
		}
		tran.cancel(reason);

		return getTransactionDao().store(tran);
	}

	public Transaction interrupt(Transaction tran, Operator operator,
			String reason) {
		Validate.notNull(operator);
		tran.interrupt(operator.getId(), reason);
		// Deactivate Expire Records
		ExpireRecord er = getExpireRecordDao().findByTransaction(tran);
		if (null != er) {
			er.setState(ExpireRecordState.INTERRUPTED);
			er.setReturnTime(new Date());
		}
		return getTransactionDao().store(tran);
	}

	public long getExpiredTransactionCount(Visitor visitor) {
		return getTransactionDao().getTransactionCount(visitor,
				TransactionState.RETURN_EXPIRED);
	}

	public List<Transaction> getActiveTransactions(Visitor visitor) {
		Validate.notNull(visitor);
		return getTransactionDao().getTransactions(visitor,
				TransactionState.BORROW_REQUESTED,
				TransactionState.BORROW_APPROVED, TransactionState.BORROW_SENT,
				TransactionState.RETURN_WAIT, TransactionState.RETURN_SENT,
				TransactionState.RETURN_EXPIRED);
	}

	static final int LIMIT = 10;

	public List<Book> getRecommendBooks(Visitor visitor) {
		return getRecommendDao().recommend(visitor, LIMIT);
	}

	public PageSearchResult<Transaction> findTransaction(Visitor visitor,
			TransactionSearchBean searchBean) {
		Validate.notNull(searchBean.getFromDate());
		Validate.notNull(searchBean.getToDate());
		return getTransactionDao().search(visitor, searchBean);
	}

	public PageSearchResult<ExpireRecord> searchExpiredRecords(Visitor visitor,
			SearchBean searchBean) {
		return getExpireRecordDao().search(visitor, searchBean);
	}

	public ExpireRecord findExpireRecord(Transaction tran) {
		return getExpireRecordDao().findByTransaction(tran);
	}

	private TransactionDao transactionDao;

	public TransactionDao getTransactionDao() {
		return transactionDao;
	}

	public void setTransactionDao(TransactionDao transactionDao) {
		this.transactionDao = transactionDao;
	}

	private RecommendDao recommendDao;

	public RecommendDao getRecommendDao() {
		return recommendDao;
	}

	public void setRecommendDao(RecommendDao recommendDao) {
		this.recommendDao = recommendDao;
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

	private FinanceOperationDao financeOperationDao;

	public FinanceOperationDao getFinanceOperationDao() {
		return financeOperationDao;
	}

	public void setFinanceOperationDao(FinanceOperationDao financeOperationDao) {
		this.financeOperationDao = financeOperationDao;
	}

	public Transaction addComment(Transaction tran, Comment comment) {
		tran.setComment(comment.getContent());
		tran.setRating(comment.getRating());
		comment.setCreateTime(new Date());
		comment.setVisitorId(tran.getVisitor().getId());
		tran.getBook().addComment(comment);
		// change deposit
		getFinanceOperationDao().changeVisitorDeposit(tran.getVisitor(),
				getRuleDao().getRewardRule().getCommentReward(),
				"Comment Reward " + tran.getOid(), null);

		getBookDao().store(tran.getBook());
		return getTransactionDao().store(tran);
	}

	public List<Transaction> getTransactionsForComment(Visitor visitor) {
		return getTransactionDao().getTransactionsForComment(visitor, 30);
	}
}
