package com.kooobao.lm.book.dao;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.BookRelation;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaRecommendDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaRecommendDao recommendDao;

	@Resource
	JpaBookDao bookDao;

	@Before
	public void prepare() {
		Book book1 = new Book();
		book1.setOid(1);
		Book book2 = new Book();
		book2.setOid(2);
		Book book3 = new Book();
		book3.setOid(3);
		bookDao.store(book1);
		bookDao.store(book2);
		bookDao.store(book3);

		BookRelation br1 = new BookRelation();
		br1.setFrom(book1);
		br1.setTo(book2);
		br1.setScore(new BigDecimal("6"));
		recommendDao.getEntityManager().persist(br1);
		br1 = new BookRelation();
		br1.setFrom(book2);
		br1.setTo(book1);
		br1.setScore(new BigDecimal("6"));
		recommendDao.getEntityManager().persist(br1);
		br1 = new BookRelation();
		br1.setFrom(book3);
		br1.setTo(book1);
		br1.setScore(new BigDecimal("4"));
		recommendDao.getEntityManager().persist(br1);
		br1 = new BookRelation();
		br1.setFrom(book1);
		br1.setTo(book3);
		br1.setScore(new BigDecimal("4"));
		recommendDao.getEntityManager().persist(br1);
		br1 = new BookRelation();
		br1.setFrom(book2);
		br1.setTo(book3);
		br1.setScore(new BigDecimal("2"));
		recommendDao.getEntityManager().persist(br1);
		br1 = new BookRelation();
		br1.setFrom(book3);
		br1.setTo(book2);
		br1.setScore(new BigDecimal("2"));
		recommendDao.getEntityManager().persist(br1);
	}

	@Test
	public void testRecommend1() {
		Book book = new Book();
		book.setOid(2);
		Book book1 = new Book();
		book1.setOid(3);
		List<Book> s = new ArrayList<Book>();
		s.add(book);
		s.add(book1);
		List<Book> books = recommendDao.recommend(null, s, 10);
		assertEquals(1, books.size());
		assertEquals(1, books.get(0).getOid());
	}

	@Test
	public void testRecommend2() {
		Book book = new Book();
		book.setOid(2);
		List<Book> books = recommendDao.recommend(null, book, 10);
		assertEquals(2, books.size());
		assertEquals(1, books.get(0).getOid());
		assertEquals(3, books.get(1).getOid());
	}
}
