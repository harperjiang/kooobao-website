package com.kooobao.ecom.setting.dao;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.setting.entity.SettingEntry;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaSettingDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaSettingDao settingDao;

	@Before
	public void prepare() {
		SettingEntry se = new SettingEntry();
		se.setKey("CustomerSetting.customerLimit");
		se.setValue("5");
		settingDao.store(se);

		se = new SettingEntry();
		se.setKey("CustomerSetting.customerRetainTime");
		se.setValue("6");
		settingDao.store(se);

		se = new SettingEntry();
		se.setKey("CustomerSetting.exchangeTimes");
		se.setValue("7");
		settingDao.store(se);

		se = new SettingEntry();
		se.setKey("CustomerSetting.hintLimit");
		se.setValue("8");
		settingDao.store(se);

		se = new SettingEntry();
		se.setKey("CustomerSetting.hintRetainTime");
		se.setValue("9");
		settingDao.store(se);
	}

	@Test
	public void testGetCustomerSetting() {
		CustomerSetting cs = settingDao.getSetting(CustomerSetting.class);
		assertEquals(5, cs.getCustomerLimit());
		assertEquals(6, cs.getCustomerRetainTime());
		assertEquals(7, cs.getExchangeTimes());
		assertEquals(8, cs.getHintLimit());
		assertEquals(9, cs.getHintRetainTime());
	}

	@Test
	public void testSetCustomerSetting() {
		CustomerSetting cs = new CustomerSetting();
		cs.setCustomerLimit(100);
		cs.setCustomerRetainTime(200);
		cs.setExchangeTimes(300);
		cs.setHintLimit(400);
		cs.setHintRetainTime(500);
		settingDao.setSetting(cs);

		assertEquals("100", settingDao
				.getEntry("CustomerSetting.customerLimit").getValue());
		assertEquals("200",
				settingDao.getEntry("CustomerSetting.customerRetainTime")
						.getValue());
		assertEquals("300", settingDao
				.getEntry("CustomerSetting.exchangeTimes").getValue());
		assertEquals("400", settingDao.getEntry("CustomerSetting.hintLimit")
				.getValue());
		assertEquals("500",
				settingDao.getEntry("CustomerSetting.hintRetainTime")
						.getValue());
	}

}
