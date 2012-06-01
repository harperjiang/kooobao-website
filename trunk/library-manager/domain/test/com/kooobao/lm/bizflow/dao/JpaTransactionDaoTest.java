package com.kooobao.lm.bizflow.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.TransactionService.TransactionSearchBean;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.profile.dao.JpaVisitorDao;
import com.kooobao.lm.profile.entity.Visitor;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaTransactionDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaTransactionDao transactionDao;

	@Resource
	JpaVisitorDao visitorDao;

	@Before
	public void prepare() {
		Visitor v = new Visitor();
		v.setOid(100);
		visitorDao.store(v);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Transaction expire = new Transaction();
		expire.setOid(125);
		expire.setCreateTime(sdf.parse("20120501", new ParsePosition(0)));
		expire.setVisitor(v);
		expire.setState(TransactionState.RETURN_WAIT);
		expire.setDueTime(sdf.parse("20120501", new ParsePosition(0)));
		transactionDao.store(expire);
	}

	@Test
	public void testFindToExpire() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		List<Transaction> tran = transactionDao.findToExpire(sdf.parse(
				"20120505", new ParsePosition(0)));
		assertEquals(1, tran.size());
	}

	@Test
	public void testGetTransactionCount() {
		Visitor v = visitorDao.find(100);
		assertEquals(1, transactionDao.getTransactionCount(v,
				TransactionState.RETURN_WAIT));
		assertEquals(1, transactionDao.getTransactionCount(v,
				TransactionState.RETURN_WAIT,
				TransactionState.BORROW_REQUESTED));
		assertEquals(0, transactionDao.getTransactionCount(v,
				TransactionState.BORROW_REQUESTED));
	}

	@Test
	public void testGetTransactions() {
		Visitor v = visitorDao.find(100);
		assertEquals(
				1,
				transactionDao.getTransactions(v,
						TransactionState.RETURN_WAIT).size());
		assertEquals(
				1,
				transactionDao.getTransactions(v,
						TransactionState.RETURN_WAIT,
						TransactionState.BORROW_REQUESTED).size());
		assertEquals(
				0,
				transactionDao.getTransactions(v,
						TransactionState.BORROW_REQUESTED).size());
	}

	@Test
	public void testSearch() {
		Visitor v = visitorDao.find(100);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		TransactionSearchBean tsb = new TransactionSearchBean();
		tsb.setFrom(0);
		tsb.setTo(10);
		tsb.setFromDate(sdf.parse("20120401", new ParsePosition(0)));
		tsb.setToDate(sdf.parse("20120601", new ParsePosition(0)));
		tsb.setState(TransactionState.RETURN_WAIT);
		PageSearchResult<Transaction> tr = transactionDao.search(v, tsb);

		assertEquals(1, tr.getCount());
	}

}
