package com.kooobao.lm.book.dao;

import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaBookDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaBookDao bookDao;
	
	@Before
	public void prepareData() {
		Category category1 = new Category();
		category1.setName("Good Category");
		
		Book book1 = new Book();
	}
	
	@Test
	public void testFindByCategory() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindByKeyword() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPopularBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetLatestBooks() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetEditorRecommendBooks() {
		fail("Not yet implemented");
	}

}
