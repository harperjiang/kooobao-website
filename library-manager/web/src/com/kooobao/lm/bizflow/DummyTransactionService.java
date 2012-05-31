package com.kooobao.lm.bizflow;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.entity.DeliveryMethod;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.ExpireRecordState;
import com.kooobao.lm.bizflow.entity.Operation;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Comment;
import com.kooobao.lm.profile.entity.Visitor;

public class DummyTransactionService implements TransactionService {

	@Override
	public List<Transaction> getActiveTransactions(Visitor visitor) {
		List<Transaction> trans = new ArrayList<Transaction>();

		Transaction tran = new Transaction();
		tran.setOid(100);
		Book book = new Book();
		book.setName("Book1");
		tran.setBook(book);
		tran.setCreateTime(new Date());
		tran.setState(TransactionState.BORROW_APPROVED);
		tran.setDueTime(new Date());
		tran.setDelivery(DeliveryMethod.EXPRESS);
		tran.getAddress().setName("王小三");
		tran.getAddress().setLocation("上海市猪头区傻瓜路1239号452弄123房间鞋柜第一层");
		tran.getAddress().setPhone("11344442233");

		Operation op1 = new Operation();
		op1.setCreateTime(new Date());
		op1.setFromState((TransactionState) null);
		op1.setToState(TransactionState.BORROW_REQUESTED);
		op1.setDescription("您的订单已经提交，正在审核");
		tran.getOperations().add(op1);

		Operation op2 = new Operation();
		op2.setCreateTime(new Date());
		op2.setFromState(TransactionState.BORROW_REQUESTED);
		op2.setToState(TransactionState.BORROW_APPROVED);
		op2.setDescription("您的订单已经通过审核，正在出库中");
		tran.getOperations().add(op2);

		Operation op3 = new Operation();
		op3.setCreateTime(new Date());
		op3.setFromState(TransactionState.BORROW_APPROVED);
		op3.setToState(TransactionState.BORROW_SENT);
		op3.setDescription("您的订单已经出库，快递编号为XX1232124223");
		tran.getOperations().add(op3);

		Transaction tran2 = new Transaction();
		tran.setOid(101);
		Book book3 = new Book();
		book3.setName("Book 3");
		tran2.setBook(book3);
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
		return getActiveTransactions(null).get(0);
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageSearchResult<Transaction> findTransaction(Visitor visitor,
			TransactionSearchBean searchBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageSearchResult<ExpireRecord> searchExpiredRecords(Visitor visitor,
			SearchBean searchBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction requestBorrow(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getExpiredTransactionCount(Visitor visitor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Transaction confirmReturn(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction approveBorrow(Transaction transaction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ExpireRecord findExpireRecord(Transaction tran) {
		ExpireRecord er = new ExpireRecord();
		er.setPenalty(new BigDecimal("432.4"));
		er.setState(ExpireRecordState.ACTIVE);
		return er;
	}

	@Override
	public Transaction sendBorrow(Transaction transaction, String expressInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction sendReturn(Transaction transaction, String expressInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction cancel(Transaction tran, String reason) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction interrupt(Transaction tran, String reason) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction addComment(Transaction tran, Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

}
