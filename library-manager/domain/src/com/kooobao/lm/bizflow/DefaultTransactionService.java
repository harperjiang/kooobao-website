package com.kooobao.lm.bizflow;

import java.util.Date;
import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.dao.ExpireRecordDao;
import com.kooobao.lm.bizflow.dao.TransactionDao;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.dao.RecommendDao;
import com.kooobao.lm.book.dao.StockDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Stock;
import com.kooobao.lm.profile.dao.VisitorDao;
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
		if (v.getStatus().equals(VisitorStatus.LOCKED.name()))
			return null;
		transaction.setState(TransactionState.BORROW_REQUESTED);
		// Set Due date
		DueRule dueRule = getRuleDao().getDueRule();
		Date dueDate = dueRule.getDueDate(v, transaction.getBook(), new Date());
		transaction.setDueTime(dueDate);
		return getTransactionDao().store(transaction);
	}

	public Transaction approveBorrow(Transaction transaction) {
		// Reduce Stock
		Stock stock = getStockDao().findByBook(transaction.getBook());
		stock.setStock(stock.getStock() - 1);
		//
		transaction.approve();
		return getTransactionDao().store(transaction);
	}

	public Transaction confirmReturn(Transaction transaction) {
		ExpireRecord expireRecord = getExpireRecordDao().findByTransaction(
				transaction);
		if (null != expireRecord && expireRecord.isActive()) {
			expireRecord.setReturnTime(new Date());
		}
		transaction.returnReceived();
		// Add Stock
		Stock stock = getStockDao().findByBook(transaction.getBook());
		stock.setStock(stock.getStock() + 1);
		return getTransactionDao().store(transaction);
	}

	public long getExpiredTransactionCount(Visitor visitor) {
		return getTransactionDao().getTransactionCount(visitor,
				TransactionState.RETURN_EXPIRED);
	}

	public List<Transaction> getActiveTransactions(Visitor visitor) {
		return getTransactionDao().getTransactions(visitor,
				TransactionState.BORROW_SENT, TransactionState.RETURN_WAIT,
				TransactionState.RETURN_SENT, TransactionState.RETURN_EXPIRED);
	}

	static final int LIMIT = 10;

	public List<Book> getRecommendBooks(Visitor visitor) {
		return getRecommendDao().recommend(visitor, (Book) null, LIMIT);
	}

	public PageSearchResult<Transaction> findTransaction(Visitor visitor,
			TransactionSearchBean searchBean) {
		return getTransactionDao().search(visitor, searchBean);
	}

	public PageSearchResult<ExpireRecord> searchExpiredRecords(Visitor visitor,
			SearchBean searchBean) {
		return getExpireRecordDao().search(visitor, searchBean);
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
}
