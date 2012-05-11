package com.kooobao.lm.bizflow;

import java.util.Date;
import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.Book;
import com.kooobao.lm.profile.Library;
import com.kooobao.lm.profile.Visitor;

public interface TransactionService {

	public Transaction getTransaction(long transId);

	public List<Transaction> findTransactions(Library library, Visitor visitor);

	public Transaction saveTransaction(Transaction transaction);

	Transaction createTransaction(Transaction transaction);

	int getExpiredBookCount(Visitor visitor);

	List<Transaction> getActiveTransactions(Visitor visitor);

	List<Book> getRecommendBooks(Visitor visitor);

	int getBorrowedBookCount(Visitor visitor);

	PageSearchResult<Transaction> findTransaction(Visitor visitor,
			SearchBean searchBean);

	public PageSearchResult<ExpireRecord> searchExpiredRecords(Visitor visitor,
			ExpireRecordSearchBean searchBean);

	public List<FavoriteRecord> searchFavoriteRecords(
			Visitor visitor);
	
	public void deleteFavorite(Visitor visitor, long bookOid);
	
	public static class SearchBean {

		private Date fromDate;

		private Date toDate;

		private TransactionState state;

		private int from;

		private int to;

		public Date getFromDate() {
			return fromDate;
		}

		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}

		public Date getToDate() {
			return toDate;
		}

		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}

		public TransactionState getState() {
			return state;
		}

		public void setState(TransactionState state) {
			this.state = state;
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public int getTo() {
			return to;
		}

		public void setTo(int to) {
			this.to = to;
		}

	}

	public static class ExpireRecordSearchBean {
		private Date fromDate;
		private Date toDate;
		private int from;
		private int to;

		public Date getFromDate() {
			return fromDate;
		}

		public void setFromDate(Date fromDate) {
			this.fromDate = fromDate;
		}

		public Date getToDate() {
			return toDate;
		}

		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}

		public int getFrom() {
			return from;
		}

		public void setFrom(int from) {
			this.from = from;
		}

		public int getTo() {
			return to;
		}

		public void setTo(int to) {
			this.to = to;
		}

	}

	

}