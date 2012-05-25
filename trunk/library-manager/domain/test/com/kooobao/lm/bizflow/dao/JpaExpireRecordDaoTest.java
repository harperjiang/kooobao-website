package com.kooobao.lm.bizflow.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.bizflow.SearchBean;
import com.kooobao.lm.bizflow.entity.ExpireRecord;
import com.kooobao.lm.bizflow.entity.ExpireRecordState;
import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.profile.dao.JpaVisitorDao;
import com.kooobao.lm.profile.entity.Visitor;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaExpireRecordDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaExpireRecordDao expireRecordDao;

	@Resource
	JpaVisitorDao visitorDao;

	@Resource
	JpaTransactionDao transactionDao;

	@Before
	public void prepare() {
		Visitor v = new Visitor();
		v.setId("Goodboy");
		expireRecordDao.getEntityManager().persist(v);

		Transaction t = new Transaction();
		t.setOid(100);
		t.setVisitor(v);
		Transaction t1 = new Transaction();
		t1.setOid(1001);
		t1.setVisitor(v);

		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		ExpireRecord er1 = new ExpireRecord();
		er1.setTransaction(t);
		er1.setState(ExpireRecordState.ACTIVE);
		er1.setCreateTime(sdf.parse("20120301", new ParsePosition(0)));
		expireRecordDao.store(er1);

		er1 = new ExpireRecord();
		er1.setTransaction(t1);
		er1.setState(ExpireRecordState.INACTIVE);
		er1.setCreateTime(sdf.parse("20120501", new ParsePosition(0)));
		expireRecordDao.store(er1);
	}

	@Test
	public void testSearch() {
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Visitor v = visitorDao.find("Goodboy");
		SearchBean sb = new SearchBean();
		sb.setFrom(0);
		sb.setTo(10);
		sb.setFromDate(sdf.parse("20120101", new ParsePosition(0)));
		sb.setToDate(sdf.parse("20120401", new ParsePosition(0)));
		PageSearchResult<ExpireRecord> result = expireRecordDao.search(v, sb);
		assertEquals(1, result.getCount());
	}

	@Test
	public void testFindByTransaction() {
		assertNotNull(expireRecordDao.findByTransaction(transactionDao
				.find(100)));
	}

	@Test
	public void testFindActivateRecords() {
		Cursor<ExpireRecord> cursor = expireRecordDao.findActivateRecords();
		
		ExpireRecord er = cursor.next();
		assertTrue(er.isActive());
		
		assertFalse(cursor.hasNext());
	}

}
