package com.kooobao.lm.bizflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.bizflow.dao.ExpireRecordDao;
import com.kooobao.lm.bizflow.dao.TransactionDao;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.ExpireRecordState;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.dao.JpaBookDao;
import com.kooobao.lm.book.dao.StockDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.BookRelation;
import com.kooobao.lm.book.entity.Category;
import com.kooobao.lm.book.entity.Comment;
import com.kooobao.lm.book.entity.Stock;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class DefaultBusinessServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	DefaultBusinessService businessService;

	@Resource
	TransactionDao transactionDao;

	@Resource
	ExpireRecordDao expireRecordDao;

	@Resource
	VisitorDao visitorDao;

	@Resource
	JpaBookDao bookDao;

	@Resource
	StockDao stockDao;

	@Before
	public void prepare() {
		Visitor visitor = new Visitor();
		visitor.setOid(10);
		visitor.setDeposit(new BigDecimal(100));
		visitor.setStatus(VisitorStatus.ACTIVE);
		visitor = visitorDao.store(visitor);

		Category category = new Category();
		bookDao.getEntityManager().persist(category);

		Book book = new Book();
		book.setOid(1);
		book.setCategory(category);
		book.getTags().add("AAA");
		book.getTags().add("CCC");
		
		Comment c = new Comment();
		c.setRating(5);
		c.setCreateTime(new Date());
		book.addComment(c);
		
		bookDao.store(book);
		
		

		Book book2 = new Book();
		book2.setOid(2);
		book2.setCategory(category);
		book2.getTags().add("AAA");
		book2.getTags().add("BBB");
		bookDao.store(book2);

		Stock stock = new Stock();
		stock.setBook(book);
		stock.setAvailable(5);
		stock.setOid(5);
		stockDao.store(stock);

		Transaction notReserved = new Transaction();
		notReserved.setState(TransactionState.BORROW_REQUESTED);
		notReserved.setBook(book);
		notReserved.setOid(5);
		transactionDao.store(notReserved);

		Transaction toExpire = new Transaction();
		toExpire.setOid(10);
		toExpire.setVisitor(visitor);
		toExpire.setState(TransactionState.RETURN_WAIT);
		toExpire.setDueTime(new Date(System.currentTimeMillis() - 86400000l));
		transactionDao.store(toExpire);

		Transaction expired = new Transaction();
		expired.setOid(11);
		expired.setVisitor(visitor);
		expired.setState(TransactionState.RETURN_EXPIRED);
		expired.setDueTime(new Date(System.currentTimeMillis() - 86400000l));
		transactionDao.store(expired);

		ExpireRecord er = new ExpireRecord();
		er.setOid(10);
		er.setTransaction(expired);
		er.setCreateTime(new Date(System.currentTimeMillis() - 86400000l));
		er.setDueTime(er.getCreateTime());
		er.setState(ExpireRecordState.ACTIVE);
		expireRecordDao.store(er);

	}

	@Test
	public void testExpireTransactions() {
		businessService.expireTransactions();
		Transaction t = transactionDao.find(10);
		assertEquals(TransactionState.RETURN_EXPIRED, t.getState());
	}

	@Test
	public void testUpdateExpireRecords() {
		ExpireRecord er = expireRecordDao.find(10);
		assertEquals(BigDecimal.ZERO, er.getPenalty());
		businessService.updateExpireRecords();
		er = expireRecordDao.find(10);
		assertEquals(ExpireRecordState.ACTIVE, er.getState());
		assertEquals(new BigDecimal("1.00"), er.getPenalty());
	}

	@Test
	public void testClearInactivateVisitors() {
		fail("Not yet implemented");
	}

	@Test
	public void testReserveStocks() {
		businessService.reserveStocks();
		Stock stock = stockDao.find(5);
		assertEquals(4, stock.getAvailable());
		Transaction t = transactionDao.find(5);
		assertTrue(t.isStockReserved());
	}

	@Test
	public void testAssumeReceived() {

	}

	@Test
	public void testBuildBookRelations() {
		businessService.buildBookAssociations();
		BookRelation br = bookDao
				.getEntityManager()
				.createQuery(
						"select br from BookRelation br where br.from.oid = 1",
						BookRelation.class).getSingleResult();
		assertEquals(new BigDecimal("3"), br.getScore());
	}

	@Test
	public void testUpdateBookRating() {
		businessService.updateBookRating(new SimpleDateFormat("yyyy-MM-dd")
				.parse("2010-01-01", new ParsePosition(0)));
	}
}
