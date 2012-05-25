package com.kooobao.lm.bizflow;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class DefaultBusinessServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void testExpireTransactions() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateExpireRecords() {
		fail("Not yet implemented");
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
