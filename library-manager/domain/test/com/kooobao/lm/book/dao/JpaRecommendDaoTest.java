package com.kooobao.lm.book.dao;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaRecommendDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaRecommendDao recommendDao;
	
	@Test
	public void testRecommend1() {
		fail("Not yet implemented");
	}

	@Test
	public void testRecommend2() {
		fail("Not yet implemented");
	}
}
