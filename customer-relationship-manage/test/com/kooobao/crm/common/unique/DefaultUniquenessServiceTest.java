package com.kooobao.crm.common.unique;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DefaultUniquenessServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	DefaultUniquenessService uniquenessService;

	@Test
	public void testStore() {
		UniqueEntry ue = new UniqueEntry();
		ue.setCategory("CATEGORY1");
		List<String> array = new ArrayList<String>();
		array.add("val1");
		array.add("val2");
		ue.getAttributes().put("key1", array);
		List<String> array1 = new ArrayList<String>();
		array1.add("val1");
		array1.add("val2");
		ue.getAttributes().put("key2", array1);
		UniqueResult ur = uniquenessService.store(ue);
		assertEquals(0, ur.getScore());

		UniqueEntry ue1 = new UniqueEntry();
		ue1.setCategory("CATEGORY1");
		List<String> array2 = new ArrayList<String>();
		array2.add("val1");
		ue1.getAttributes().put("key2", array2);
		ur = uniquenessService.store(ue1);
		assertEquals(1, ur.getScore());
	}

	@Test
	public void testDiscard() {
		UniqueEntry ue = new UniqueEntry();
		ue.setCategory("CATEGORY1");
		List<String> array = new ArrayList<String>();
		array.add("val1");
		array.add("val2");
		ue.getAttributes().put("key1", array);
		List<String> array1 = new ArrayList<String>();
		array1.add("val1");
		array1.add("val2");
		ue.getAttributes().put("key2", array1);
		UniqueResult ur = uniquenessService.store(ue);
		assertEquals(0, ur.getScore());
		
		uniquenessService.discardEntry(ur.getUuid());

		UniqueEntry ue1 = new UniqueEntry();
		ue1.setCategory("CATEGORY1");
		List<String> array2 = new ArrayList<String>();
		array2.add("val1");
		ue1.getAttributes().put("key2", array2);
		ur = uniquenessService.store(ue1);
		assertEquals(0, ur.getScore());
	}
}
