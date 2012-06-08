package com.kooobao.lm.profile.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.profile.entity.Operator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaOperatorDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	OperatorDao operatorDao;

	@Before
	public void prepare() {
		Operator o = new Operator();
		o.setId("Good");
		o.setOid(1);
		operatorDao.store(o);
	}

	@Test
	public void testFindString() {
		assertEquals(1,operatorDao.find("Good").getOid());
	}

}
