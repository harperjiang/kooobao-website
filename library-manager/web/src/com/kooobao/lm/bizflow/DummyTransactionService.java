package com.kooobao.lm.bizflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Library;
import com.kooobao.lm.profile.entity.Visitor;

public class DummyTransactionService implements TransactionService {

	@Override
	public Transaction createTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExpiredBookCount(Visitor visitor) {
		return (int) (System.currentTimeMillis() % 100);
	}

	@Override
	public int getBorrowedBookCount(Visitor visitor) {
		return (int) (System.currentTimeMillis() % 100);
	}

	@Override
	public List<Transaction> getActiveTransactions(Visitor visitor) {
		List<Transaction> trans = new ArrayList<Transaction>();

		Transaction tran = new Transaction();
	
		tran.setId("XN12321resd");
		Book book = new Book();
		book.setName("Book1");
		tran.setBook(book);
		tran.setDueTime(new Date());
		tran.setCount(1);

		Transaction tran2 = new Transaction();
		tran2.setId("DSFe433fadfa");
		Book book3 = new Book();
		book3.setName("Book 3");
		tran2.setBook(book3);
		tran2.setCount(1);
		tran2.setDueTime(new Date());

		trans.add(tran);
		trans.add(tran2);

		return trans;
	}

	@Override
	public List<Book> getRecommendBooks(Visitor visitor) {
		List<Book> books = new ArrayList<Book>();

		Book book = new Book();
		book.setName("Good");
		Book book1 = new Book();
		book1.setName("bakd");

		books.add(book);
		books.add(book1);

		return books;
	}

	@Override
	public Transaction getTransaction(long transId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> findTransactions(Library library, Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageSearchResult<Transaction> findTransaction(Visitor visitor,
			SearchBean searchBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageSearchResult<ExpireRecord> searchExpiredRecords(Visitor visitor,
			ExpireRecordSearchBean searchBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFavorite(Visitor visitor, long bookOid) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void deleteFavorite(Visitor visitor, long bookOid) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<FavoriteRecord> searchFavoriteRecords(Visitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}

}
