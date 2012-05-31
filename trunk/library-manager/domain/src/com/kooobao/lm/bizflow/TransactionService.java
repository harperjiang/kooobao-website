package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Comment;
import com.kooobao.lm.profile.entity.Visitor;

public interface TransactionService {

	Transaction getTransaction(long transId);

	Transaction saveTransaction(Transaction transaction);

	Transaction requestBorrow(Transaction transaction);

	Transaction approveBorrow(Transaction transaction);

	Transaction sendBorrow(Transaction transaction, String expressInfo);

	Transaction sendReturn(Transaction transaction, String expressInfo);

	Transaction cancel(Transaction tran, String reason);

	/**
	 * Interrupt transaction will deactivate related expiration record.
	 * Checked-out stocks will not be resumed.
	 * 
	 * @param tran
	 * @param reason
	 * @return
	 */
	Transaction interrupt(Transaction tran, String reason);

	Transaction confirmReturn(Transaction transaction);

	long getExpiredTransactionCount(Visitor visitor);

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

	ExpireRecord findExpireRecord(Transaction tran);

	Transaction addComment(Transaction tran, Comment comment);
}
