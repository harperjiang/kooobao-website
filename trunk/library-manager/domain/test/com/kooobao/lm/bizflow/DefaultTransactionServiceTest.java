package com.kooobao.lm.bizflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.bizflow.dao.TransactionDao;
import com.kooobao.lm.bizflow.entity.DeliveryMethod;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.dao.StockDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Comment;
import com.kooobao.lm.book.entity.Stock;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class DefaultTransactionServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	TransactionService transactionService;

	@Resource
	VisitorDao visitorDao;

	@Resource
	BookDao bookDao;

	@Resource
	TransactionDao transactionDao;

	@Resource
	StockDao stockDao;

	@Before
	public void prepare() {
		Visitor locked = new Visitor();
		locked.setStatus(VisitorStatus.LOCKED);
		locked.setOid(1);
		visitorDao.store(locked);

		Visitor active = new Visitor();
		active.setStatus(VisitorStatus.ACTIVE);
		active.setOid(2);
		active.setDeposit(new BigDecimal(120));
		visitorDao.store(active);

		Visitor inactive = new Visitor();
		inactive.setStatus(VisitorStatus.INACTIVE);
		inactive.setOid(3);
		visitorDao.store(inactive);

		Book expBook = new Book();
		expBook.setOid(1);
		expBook.setListPrice(new BigDecimal(240));
		bookDao.store(expBook);

		Book cheapBook = new Book();
		cheapBook.setOid(2);
		cheapBook.setListPrice(new BigDecimal(100));
		bookDao.store(cheapBook);

		Stock stock = new Stock();
		stock.setBook(cheapBook);
		stock.setStock(5);
		stockDao.store(stock);

		Transaction tran = new Transaction();
		tran.setOid(128);
		tran.setVisitor(active);
		tran.setBook(expBook);
		transactionDao.store(tran);
	}

	@Test
	public void testRequestBorrow() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);

		tran = transactionService.getTransaction(1);

		assertTrue(tran.getCreateTime().getTime() + 86400000 * 14 <= tran
				.getDueTime().getTime());
		assertEquals(TransactionState.BORROW_REQUESTED, tran.getState());
		assertEquals(1, tran.getOperations().size());
		assertEquals("您的请求已经提交，正在处理中", tran.getOperations().get(0)
				.getDescription());
		assertEquals(new BigDecimal(120), visitorDao.find(2).getDeposit());
	}

	@Test
	public void testApproveBorrow() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(1));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		try {
			tran = transactionService.approveBorrow(tran, null);
			fail();
		} catch (InsufficientFundException e) {

		}
		tran.setBook(bookDao.find(2));

		tran = transactionService.approveBorrow(tran, null);

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.BORROW_APPROVED, tran.getState());
		assertEquals(2, tran.getOperations().size());
		assertEquals("您的订单已经通过审核，正在出库中", tran.getOperations().get(1)
				.getDescription());
	}

	@Test
	public void testSendout() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setDelivery(DeliveryMethod.EXPRESS);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);

		Operator operator = new Operator();
		operator.setId("myoperator");

		try {
			transactionService.sendBorrow(tran, null, null);
			fail();
		} catch (IllegalArgumentException e) {

		}
		try {
			transactionService.sendBorrow(tran, operator, null);
			fail();
		} catch (IllegalArgumentException e) {

		}
		transactionService.sendBorrow(tran, operator, "顺丰 112444234");

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.BORROW_SENT, tran.getState());
		assertEquals(3, tran.getOperations().size());
		assertEquals("您的订单已经出库,跟踪编号 顺丰 112444234", tran.getOperations().get(2)
				.getDescription());
		assertEquals("myoperator", tran.getOperations().get(2).getOperatorId());
	}

	@Test
	public void testAssumeReceive() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);
		Operator o = new Operator();
		o.setId("good");
		tran = transactionService.sendBorrow(tran, o, "顺丰 112444234");

		tran.assumeReceived();
		transactionDao.store(tran);

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_WAIT, tran.getState());
		assertEquals(4, tran.getOperations().size());
		assertEquals("您的订单已经签收", tran.getOperations().get(3).getDescription());
	}

	@Test
	public void testReturnExpired() {
		Visitor v = visitorDao.find(2);
		Operator o = new Operator();
		o.setId("1001");
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);
		tran = transactionService.sendBorrow(tran, o, "顺丰 112444234");

		tran.assumeReceived();
		tran.expire();
		transactionDao.store(tran);

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_EXPIRED, tran.getState());
		assertEquals(5, tran.getOperations().size());
		assertEquals("您的借书已经超期，请尽快归还 ", tran.getOperations().get(4)
				.getDescription());
	}

	@Test
	public void testSendBackReturnWait() {
		Visitor v = visitorDao.find(2);
		Operator o = new Operator();
		o.setId("1001");
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);
		tran = transactionService.sendBorrow(tran, o, "顺丰 112444234");

		tran.assumeReceived();
		tran = transactionDao.store(tran);

		tran = transactionService.sendReturn(tran, o, "自送");

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_SENT, tran.getState());
		assertEquals(5, tran.getOperations().size());
		assertEquals("您已经登记了归还书籍 自送", tran.getOperations().get(4)
				.getDescription());
		assertEquals("1001", tran.getOperations().get(4).getOperatorId());
	}

	@Test
	public void testSendBackReturnExpired() {
		Operator o = new Operator();
		o.setId("1001");

		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);
		tran = transactionService.sendBorrow(tran, o, "顺丰 112444234");

		tran.assumeReceived();
		tran.expire();
		transactionDao.store(tran);

		tran = transactionService.sendReturn(tran, o, "货到付款 顺丰 123333");
		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_EXPIRED, tran.getState());
		assertEquals(6, tran.getOperations().size());
		assertEquals("您的归还记录已提交(货到付款 顺丰 123333)，收到后，我们将为您清除超期记录", tran
				.getOperations().get(5).getDescription());
		assertEquals("1001", tran.getOperations().get(5).getOperatorId());
	}

	@Test
	public void testSendBackReceived() {
		Operator o = new Operator();
		o.setId("1001");

		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		// tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);
		tran = transactionService.sendBorrow(tran, o, "顺丰 112444234");

		tran.assumeReceived();

		tran = transactionService.sendReturn(tran, o, "自送");
		tran = transactionService.confirmReturn(tran, o, "Good Boy");

		// tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_RECEIVED, tran.getState());
		assertEquals(6, tran.getOperations().size());
		assertEquals("您归还的书籍已经收到 ", tran.getOperations().get(5)
				.getDescription());
		assertEquals("1001", tran.getOperations().get(5).getOperatorId());
		assertEquals("Good Boy", tran.getOperations().get(5).getComment());
	}

	@Test
	public void testCancelTransaction() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		transactionService.cancel(tran, "Some Reason");

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.CANCELLED, tran.getState());
		assertEquals(2, tran.getOperations().size());
		assertEquals("您已经选择取消订单，原因:Some Reason", tran.getOperations().get(1)
				.getDescription());
		assertEquals(new BigDecimal(120), visitorDao.find(2).getDeposit());
	}

	@Test
	public void testCancelTransaction2() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);
		tran = transactionService.cancel(tran, "Some Reason");

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.CANCELLED, tran.getState());
		assertEquals(3, tran.getOperations().size());
		assertEquals("您已经选择取消订单，原因:Some Reason", tran.getOperations().get(2)
				.getDescription());
		assertEquals(new BigDecimal(120), visitorDao.find(2).getDeposit());
	}

	@Test
	public void testInterrupt() {
		Operator o = new Operator();
		o.setId("1001");
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		tran = transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		tran = transactionService.approveBorrow(tran, null);
		tran = transactionService.interrupt(tran, o, "Some Reason");

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.INTERRUPTED, tran.getState());
		assertEquals(3, tran.getOperations().size());
		assertEquals("您的订单无法完成，原因:Some Reason", tran.getOperations().get(2)
				.getDescription());
		assertEquals("1001", tran.getOperations().get(2).getOperatorId());
		assertEquals(new BigDecimal(20), visitorDao.find(2).getDeposit());
	}

	@Test
	public void testAddComment() {
		Comment c = new Comment();
		c.setRating(5);
		c.setContent("这是一大段评论");

		Transaction t = transactionService.addComment(transactionDao.find(128),
				c);
		assertEquals("这是一大段评论", t.getComment());
		assertEquals(1, t.getBook().getComments().size());
		assertEquals("这是一大段评论", t.getBook().getComments().get(0).getContent());

	}

	@Test
	public void testGetExpiredTransactionCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetActiveTransactions() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRecommendBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindTransaction() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearchExpiredRecords() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindExpireRecord() {
		fail("Not yet implemented");
	}

}
