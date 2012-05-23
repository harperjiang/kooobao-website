package com.kooobao.lm.book.dao;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Stock;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaStockDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaStockDao stockDao;
	
	@Resource
	JpaBookDao bookDao;
	
	@Before
	public void prepare() {
		Book book = new Book();
		book.setOid(100);
		book.setName("这是一本啥书");
		
		book = bookDao.store(book);
		
		Stock stock = new Stock();
		stock.setBook(book);
		stock.setStock(100);
		stockDao.store(stock);
	}
	
	@Test
	public void testFindByBook() {
		Book book = bookDao.find(100);
		Stock stock = stockDao.findByBook(book);
		assertEquals(100,stock.getStock());
	}

}
