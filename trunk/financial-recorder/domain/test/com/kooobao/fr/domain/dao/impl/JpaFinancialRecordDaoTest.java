package com.kooobao.fr.domain.dao.impl;

import static org.junit.Assert.fail;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.common.web.Utilities;
import com.kooobao.fr.domain.dao.FinancialRecordDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaFinancialRecordDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name = "financialRecordDao")
	private FinancialRecordDao financialRecordDao;

	@Test
	public void testGetCountByCreateBy() {
		financialRecordDao.getCountByCreateBy("actor");
	}

	@Test
	public void testGetCountByStatus() {
		financialRecordDao.getCountByStatus(new String[] { "A", "B", "C", "D",
				"E" });
	}

	@Test
	public void testGetRecordsByCreatedBy() {
		financialRecordDao.getRecordsByCreatedBy("actor", 20);
	}

	@Test
	public void testGetRecordsByStatus() {
		financialRecordDao.getRecordsByStatus(
				new String[] { "A", "B", "C", "D" }, 15);
	}

	@Test
	public void testSearchCount() {
		financialRecordDao.searchCount(Utilities.offset(new Date(), -5),
				new Date(), new String[] { "A", "B", "C" });
	}

	@Test
	public void testSearch() {
		financialRecordDao.search(Utilities.offset(new Date(), -3), new Date(),
				new String[] { "A", "B" }, 5, 20);
	}

}
