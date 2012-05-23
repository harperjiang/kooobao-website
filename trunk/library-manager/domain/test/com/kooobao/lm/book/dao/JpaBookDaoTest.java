package com.kooobao.lm.book.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;
import com.kooobao.lm.optlog.entity.BorrowCount;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaBookDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaBookDao bookDao;

	@Resource
	JpaCategoryDao categoryDao;

	@Before
	public void prepareData() {
		Category category1 = new Category();
		category1.setName("Good Category");
		category1.setOid(100);
		category1 = categoryDao.store(category1);

		Book book1 = new Book();
		book1.setName("Good Book");
		book1.setCategory(category1);
		book1 = bookDao.store(book1);

		Book book2 = new Book();
		book2.setName("Bad Book");
		book2 = bookDao.store(book2);

		BorrowCount bc = new BorrowCount();
		bc.setBook(book1);
		bc.setCount(5);
		bookDao.getEntityManager().persist(bc);

		BorrowCount bc2 = new BorrowCount();
		bc2.setBook(book2);
		bc2.setCount(12);
		bookDao.getEntityManager().persist(bc2);
	}

	@Test
	public void testFindByCategory() {
		Category category = categoryDao.find(100);
		PageSearchResult<Book> result = bookDao.findByCategory(category, 0, 10);
		assertEquals(1, result.getCount());
		assertEquals("Good Book", result.getResult().get(0).getName());
	}

	@Test
	public void testFindByKeyword() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPopularBooks() {
		PageSearchResult<Book> books = bookDao.getPopularBooks(0, 10);
		assertEquals(2, books.getCount());
		assertEquals("Bad Book", books.getResult().get(0).getName());
	}

	@Test
	public void testGetLatestBooks() {
		PageSearchResult<Book> books = bookDao.getLatestBooks(0, 10);
		assertEquals(2,books.getCount());
		assertEquals("Good Book",books.getResult().get(0).getName());
	}

	@Test
	public void testGetEditorRecommendBooks() {
		fail("Not yet implemented");
	}

}
