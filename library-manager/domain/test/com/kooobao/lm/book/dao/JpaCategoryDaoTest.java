package com.kooobao.lm.book.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.book.entity.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaCategoryDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaCategoryDao categoryDao;

	@Before
	public void prepareData() {
		Category category1 = new Category();
		category1.setName("Good Category");
		category1.setOid(100);
		category1 = categoryDao.store(category1);

		Category category2 = new Category();
		category2.setName("Good Category 2");
		category2.setOid(101);
		category2 = categoryDao.store(category2);

		Category category3 = new Category();
		category3.setName("Child Category");
		category3.setOid(110);
		category3.setParent(category2);
		category3 = categoryDao.store(category3);

	}

	@Test
	public void testGetRootCategory() {
		List<Category> categories = categoryDao.getRootCategories();
		assertEquals(2, categories.size());
		assertEquals("Good Category", categories.get(0).getName());
		assertEquals("Good Category 2", categories.get(1).getName());
	}

}
