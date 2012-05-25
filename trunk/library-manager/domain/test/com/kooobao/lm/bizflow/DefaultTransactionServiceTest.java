package com.kooobao.lm.bizflow;

import static org.junit.Assert.assertEquals;
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
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.dao.StockDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Stock;
import com.kooobao.lm.profile.dao.VisitorDao;
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
	}

	@Test
	public void testRequestBorrow() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(1));
		try {
			transactionService.requestBorrow(tran);
			fail("Insufficient Fund!");
		} catch (InsufficientFundException e) {

		}
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);

		tran = transactionService.getTransaction(1);

		assertEquals(tran.getCreateTime().getTime() + 86400000 * 14, tran
				.getDueTime().getTime());
		assertEquals(TransactionState.BORROW_REQUESTED, tran.getState());
		assertEquals(1, tran.getOperations().size());
		assertEquals("您的请求已经提交，正在处理中", tran.getOperations().get(0)
				.getDescription());
		assertEquals(new BigDecimal(20), visitorDao.find(2).getDeposit());
	}

	@Test
	public void testApproveBorrow() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		transactionService.approveBorrow(tran);

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
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		transactionService.approveBorrow(tran);
		transactionService.sendBorrow(tran, "顺丰 112444234");

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.BORROW_SENT, tran.getState());
		assertEquals(3, tran.getOperations().size());
		assertEquals("您的订单已经出库,跟踪编号 顺丰 112444234", tran.getOperations().get(2)
				.getDescription());
	}

	@Test
	public void testAssumeReceive() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		transactionService.approveBorrow(tran);
		transactionService.sendBorrow(tran, "顺丰 112444234");

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
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		transactionService.approveBorrow(tran);
		transactionService.sendBorrow(tran, "顺丰 112444234");

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
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		transactionService.approveBorrow(tran);
		transactionService.sendBorrow(tran, "顺丰 112444234");

		tran.assumeReceived();
		tran = transactionDao.store(tran);

		transactionService.sendReturn(tran, "自送");

		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_SENT, tran.getState());
		assertEquals(5, tran.getOperations().size());
		assertEquals("您已经登记了归还书籍 自送", tran.getOperations().get(4)
				.getDescription());
	}

	@Test
	public void testSendBackReturnExpired() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		transactionService.approveBorrow(tran);
		transactionService.sendBorrow(tran, "顺丰 112444234");

		tran.assumeReceived();
		tran.expire();
		transactionDao.store(tran);

		transactionService.sendReturn(tran, "货到付款 顺丰 123333");
		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_EXPIRED, tran.getState());
		assertEquals(6, tran.getOperations().size());
		assertEquals("您的归还记录已提交(货到付款 顺丰 123333)，收到后，我们将为您清除超期记录", tran
				.getOperations().get(5).getDescription());
	}

	@Test
	public void testSendBackReceived() {
		Visitor v = visitorDao.find(2);
		Transaction tran = new Transaction();
//		tran.setOid(1);
		tran.setVisitor(v);
		tran.setBook(bookDao.find(2));
		transactionService.requestBorrow(tran);
		tran.setStockReserved(true);
		transactionService.approveBorrow(tran);
		transactionService.sendBorrow(tran, "顺丰 112444234");

		tran.assumeReceived();

		transactionService.sendReturn(tran, "自送");
		transactionService.confirmReturn(tran);

//		tran = transactionService.getTransaction(1);

		assertEquals(TransactionState.RETURN_RECEIVED, tran.getState());
		assertEquals(6, tran.getOperations().size());
		assertEquals("您归还的书籍已经收到 ", tran.getOperations().get(5)
				.getDescription());

	}

	@Test
	public void testCancelTransaction() {
		transactionService.cancel(null, null);
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
