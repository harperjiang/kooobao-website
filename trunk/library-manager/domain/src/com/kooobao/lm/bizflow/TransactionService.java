package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public interface TransactionService {

	public Transaction getTransaction(long transId);

	public List<Transaction> findTransactions(Visitor visitor);

	public Transaction saveTransaction(Transaction transaction);

	Transaction requestBorrow(Transaction transaction);

	Transaction confirmReturn(Transaction transaction);

	int getExpiredTransactionCount(Visitor visitor);

	List<Transaction> getActiveTransactions(Visitor visitor);

	List<Book> getRecommendBooks(Visitor visitor);

	PageSearchResult<Transaction> findTransaction(Visitor visitor,
			TransactionSearchBean searchBean);

	PageSearchResult<ExpireRecord> searchExpiredRecords(Visitor visitor,
			SearchBean searchBean);

	public static class TransactionSearchBean extends SearchBean {

		private TransactionState state;

		public TransactionState getState() {
			return state;
		}

		public void setState(TransactionState state) {
			this.state = state;
		}

	}

}
