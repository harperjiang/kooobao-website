package com.kooobao.crm.common.unique;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.crm.common.unique.JpaUniqueEntryDao;
import com.kooobao.ecom.crm.common.unique.UniqueEntry;
import com.kooobao.ecom.crm.common.unique.UniqueItem;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaUniqueEntryDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaUniqueEntryDao uniqueEntryDao;

	@Before
	public void prepare() {
		UniqueItem ui = new UniqueItem();
		ui.setCategory("category1");
		ui.setKey("KEY1");
		ui.setUuid("abab");
		ui.setValue("GOOD");
		uniqueEntryDao.store(ui);

		ui = new UniqueItem();
		ui.setCategory("category1");
		ui.setKey("KEY1");
		ui.setUuid("abab");
		ui.setValue("GOOD1");
		uniqueEntryDao.store(ui);

		ui = new UniqueItem();
		ui.setCategory("category1");
		ui.setKey("KEY1");
		ui.setUuid("abab");
		ui.setValue("GOOD2");
		uniqueEntryDao.store(ui);

		ui = new UniqueItem();
		ui.setCategory("category1");
		ui.setKey("KEY1");
		ui.setUuid("abad");
		ui.setValue("GOOD1");
		uniqueEntryDao.store(ui);
	}

	@Test
	public void testFindStringStringCollectionOfString() {
		List<String> vals = new ArrayList<String>();
		vals.add("GOOD1");
		vals.add("GOOD");
		assertEquals(2, uniqueEntryDao.find("category1", "KEY1", vals).size());
	}

	@Test
	public void testStoreUniqueEntry() {
		UniqueEntry ue = new UniqueEntry();
		ue.setCategory("category2");
		List<String> values = new ArrayList<String>();
		values.add("AA1");
		values.add("BB1");
		ue.getAttributes().put("DIDA", values);

		String uuid = uniqueEntryDao.store(ue);

		assertEquals(uuid,
				uniqueEntryDao.find("category2", "DIDA", "AA1").get(0)
						.getUuid());
	}

	@Test
	public void testDiscard() {
		assertEquals(1, uniqueEntryDao.find("category1", "KEY1", "GOOD").size());
		uniqueEntryDao.discard("abab");
		assertEquals(0, uniqueEntryDao.find("category1", "KEY1", "GOOD").size());
	}
}
