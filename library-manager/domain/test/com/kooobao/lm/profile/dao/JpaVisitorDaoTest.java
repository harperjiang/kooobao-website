package com.kooobao.lm.profile.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Visitor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaVisitorDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource(name = "visitorDao")
	private JpaVisitorDao visitorDao;

	@Before
	public void prepareData() {
		Visitor v1 = new Visitor();
		v1.setId("aaa");
		visitorDao.store(v1);

		ActivationRecord ar = new ActivationRecord();
		ar.setActivationId("32ddssb");
		ar.setVisitorId("harperjiang@msn.com");
		visitorDao.store(ar);

		ActivationRecord toremove = new ActivationRecord();
		toremove.setActivationId("remove");
		toremove.setVisitorId("harperjiang@msn.com");
		visitorDao.store(toremove);
	}

	@Test
	public void testFindString() {
		assertNotNull(visitorDao.find("aaa"));
	}

	@Test
	public void testGetActivationRecord() {
		assertEquals("harperjiang@msn.com",
				visitorDao.getActivationRecord("32ddssb").getVisitorId());
	}

	@Test
	public void testRemoveActivationRecord() {
		visitorDao.removeActivationRecord(visitorDao
				.getActivationRecord("remove"));
		assertNull(visitorDao.getActivationRecord("remove"));
	}

}
