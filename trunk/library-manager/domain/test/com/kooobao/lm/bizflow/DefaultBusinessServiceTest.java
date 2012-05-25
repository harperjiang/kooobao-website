package com.kooobao.lm.bizflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
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

	@Before
	public void prepare() {
		Visitor visitor = new Visitor();
		visitor.setOid(10);
		visitor.setDeposit(new BigDecimal(100));
		visitor.setStatus(VisitorStatus.ACTIVE);
		visitorDao.store(visitor);

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
		fail("Not yet implemented");
	}

	@Test
	public void testAssumeReceived() {
		fail("Not yet implemented");
	}

}
