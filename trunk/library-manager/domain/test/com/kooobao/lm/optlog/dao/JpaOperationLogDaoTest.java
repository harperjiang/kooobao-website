package com.kooobao.lm.optlog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import com.kooobao.lm.optlog.entity.BorrowCount;
import com.kooobao.lm.optlog.entity.SearchLog;
import com.kooobao.lm.optlog.entity.SearchSummary;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaOperationLogDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaOperationLogDao operationLogDao;

	@Before
	public void prepareData() {
		SearchSummary summary = new SearchSummary();
		summary.setKeyword("Keyword1");
		summary.setSearchCount(6);
		operationLogDao.store(summary);

		summary = new SearchSummary();
		summary.setKeyword("Keyword2");
		summary.setSearchCount(7);
		operationLogDao.store(summary);

		summary = new SearchSummary();
		summary.setKeyword("Keyword3");
		summary.setSearchCount(12);
		operationLogDao.store(summary);

		BorrowCount bc = new BorrowCount();
		Book book = new Book();
		book.setOid(1000);
		operationLogDao.getEntityManager().persist(book);
		bc.setBook(book);
		bc.setCount(1298);
		operationLogDao.getEntityManager().persist(bc);
	}

	@Test
	public void testLogSearch() {
		operationLogDao.logSearch("Keyword1");
		operationLogDao.logSearch("Keyword2");
		operationLogDao.logSearch("Keyword1");

		List<SearchLog> logs = operationLogDao.getEntityManager()
				.createQuery("select s from SearchLog s", SearchLog.class)
				.getResultList();
		assertEquals(3, logs.size());

		Long count = operationLogDao
				.getEntityManager()
				.createQuery(
						"select count(s) from SearchLog s where s.keyword =:kwd",
						Long.class).setParameter("kwd", "Keyword1")
				.getSingleResult();
		assertEquals(2, count.intValue());
	}

	@Test
	public void testGetHotSearchWords() {
		List<String> result = operationLogDao.getHotSearchWords();
		assertEquals(3, result.size());

		assertEquals("Keyword3", result.get(0));
		assertEquals("Keyword2", result.get(1));
		assertEquals("Keyword1", result.get(2));
	}

	@Test
	public void testLogBorrow() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBorrowedBooks() {
		Book book = operationLogDao.getBorrowedBooks(0, 10).getResult().get(0);
		assertEquals(1000,book.getOid());
		
	}

}
