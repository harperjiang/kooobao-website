package com.kooobao.ecom.crm.customer.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.ecom.crm.customer.dao.JpaHintDao;
import com.kooobao.ecom.crm.customer.entity.Hint;
import com.kooobao.ecom.crm.customer.entity.HintStatus;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaHintDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaHintDao hintDao;

	@Before
	public void prepare() {
		Hint hint = new Hint();
		hint.setOwnBy("a");
		hint.setUpdateTime(DateUtils.addDays(new Date(), -3));
		hint.setStatus(HintStatus.FOLLOWUP);
		hintDao.store(hint);

		hint = new Hint();
		hint.setOwnBy("a");
		hint.setUpdateTime(DateUtils.addDays(new Date(), -5));
		hint.setStatus(HintStatus.FOLLOWUP);
		hintDao.store(hint);

		hint = new Hint();
		hint.setOwnBy("b");
		hint.setUpdateTime(DateUtils.addDays(new Date(), -6));
		hint.setStatus(HintStatus.FOLLOWUP);
		hintDao.store(hint);

		hint = new Hint();
		hint.setStatus(HintStatus.FREE);
		hintDao.store(hint);

		hint = new Hint();
		hint.setStatus(HintStatus.FREE);
		hintDao.store(hint);
	}

	@Test
	public void testGetHints() {
		List<Hint> hints = hintDao.getHints("a");
		assertEquals(2, hints.size());

		hints = hintDao.getHints("b");
		assertEquals(1, hints.size());
	}

	@Test
	public void testGetFreeHints() {
		assertEquals(2, hintDao.getFreeHints(5).size());
		assertEquals(1, hintDao.getFreeHints(1).size());
	}

	@Test
	public void testGetOvertimeHints() {
		Cursor<Hint> hints = hintDao.getOvertimeHints(4);
		int count = 0;
		while (hints.hasNext()) {
			hints.next();
			count++;
		}
		assertEquals(2, count);
	}

}
