package com.kooobao.lm.optlog;

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
public class DefaultOptLogServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	DefaultOptLogService optLogService;
	
	@Test
	public void testSumSearchKeyword() {
		fail("Not yet implemented");
	}

	@Test
	public void testCountBorrowBook() {
		fail("Not yet implemented");
	}

}
