package com.kooobao.lm.optlog.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.bizflow.entity.Transaction;
import com.kooobao.lm.book.dao.JpaBookDao;
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
	
	@Resource
	JpaBookDao bookDao;

	@Before
	public void prepareData() {
		Date date = new Date(1337783974000l);

		Date date2 = new Date(1337790000000l);

		SearchSummary summary = new SearchSummary();
		summary.setKeyword("Keyword1");
		summary.setSearchCount(6);
		summary.setCreateTime(date);
		operationLogDao.store(summary);

		summary = new SearchSummary();
		summary.setKeyword("Keyword2");
		summary.setSearchCount(7);
		summary.setCreateTime(date);
		operationLogDao.store(summary);

		summary = new SearchSummary();
		summary.setKeyword("Keyword3");
		summary.setSearchCount(12);
		summary.setCreateTime(date);
		operationLogDao.store(summary);

		SearchLog sl = new SearchLog();
		sl.setKeyword("Keyword1");
		sl.setCreateTime(date2);
		operationLogDao.store(sl);

		SearchLog sl2 = new SearchLog();
		sl2.setKeyword("Keyword4");
		sl2.setCreateTime(date2);
		operationLogDao.store(sl2);

		Book book = new Book();
		book.setOid(1000);
		operationLogDao.getEntityManager().persist(book);
		Transaction t = new Transaction();
		t.setCreateTime(date2);
		t.setBook(book);
		operationLogDao.getEntityManager().persist(t);

		t = new Transaction();
		t.setCreateTime(date2);
		t.setBook(book);
		operationLogDao.getEntityManager().persist(t);

		BorrowCount bc = new BorrowCount();
		bc.setBook(book);
		bc.setCount(129);
		bc.setUpdateTime(date);
		operationLogDao.store(bc);
	}

	@Test
	public void testLogSearch() {
		operationLogDao.logSearch("Keyword1");
		operationLogDao.logSearch("Keyword2");
		operationLogDao.logSearch("Keyword1");

		List<SearchLog> logs = operationLogDao.getEntityManager()
				.createQuery("select s from SearchLog s", SearchLog.class)
				.getResultList();
		assertEquals(5, logs.size());

		Long count = operationLogDao
				.getEntityManager()
				.createQuery(
						"select count(s) from SearchLog s where s.keyword =:kwd",
						Long.class).setParameter("kwd", "Keyword1")
				.getSingleResult();
		assertEquals(3, count.intValue());
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
	public void testGetBorrowedBooks() {
		Book book = operationLogDao.getBorrowedBooks(0, 10).getResult().get(0);
		assertEquals(1000, book.getOid());

	}

	@Test
	public void testGetSearchSummaryUpdateTime() {
		assertEquals(1337783974000l, operationLogDao
				.getSearchSummaryUpdateTime().getTime());
	}

	@Test
	public void testGetBorrowCountUpdateTime() {
		assertEquals(1337783974000l, operationLogDao.getBorrowCountUpdateTime()
				.getTime());
	}

	@Test
	public void testGetSearchSummaries() {
		List<SearchSummary> summaries = operationLogDao
				.getSearchSummaries(new Date(1337783974000l));
		assertEquals(2, summaries.size());
		assertTrue("Keyword1".equals(summaries.get(0).getKeyword())
				|| "Keyword4".equals(summaries.get(0).getKeyword()));
	}

	@Test
	public void testGetSearchSummary() {
		SearchSummary ss = operationLogDao.getSearchSummary("Keyword1");
		assertEquals("Keyword1", ss.getKeyword());
		assertEquals(6, ss.getSearchCount());
	}

	@Test
	public void testGetBorrowCount() {
		Date date = new Date(1337783974000l);
		List<BorrowCount> bc = operationLogDao.getBorrowCount(date);
		assertEquals(1, bc.size());
		assertEquals(2, bc.get(0).getCount());
	}
	
	@Test
	public void testGetBorrowCount2() {
		Book book = bookDao.find(1000);
		BorrowCount bc = operationLogDao.getBorrowCount(book);
		assertEquals(129,bc.getCount());
	}
}
