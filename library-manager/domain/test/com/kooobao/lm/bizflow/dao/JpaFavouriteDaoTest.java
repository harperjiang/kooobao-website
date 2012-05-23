package com.kooobao.lm.bizflow.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.book.dao.JpaBookDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.dao.JpaVisitorDao;
import com.kooobao.lm.profile.entity.Visitor;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaFavouriteDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaFavouriteDao favouriteDao;
	@Resource
	JpaVisitorDao visitorDao;
	@Resource
	JpaBookDao bookDao;

	@Before
	public void prepare() {
		Book book = new Book();
		book.setOid(100);
		favouriteDao.getEntityManager().persist(book);

		Visitor v = new Visitor();
		v.setOid(100);
		favouriteDao.getEntityManager().persist(v);

		FavoriteRecord fr = new FavoriteRecord();
		fr.setFavorite(book);
		fr.setVisitor(v);
		favouriteDao.store(fr);

		Book book1 = new Book();
		book1.setOid(101);
		favouriteDao.getEntityManager().persist(book1);

		FavoriteRecord fr1 = new FavoriteRecord();
		fr1.setVisitor(v);
		fr1.setFavorite(book1);
		favouriteDao.store(fr1);
	}

	@Test
	public void testFindVisitorBook() {
		FavoriteRecord fr = favouriteDao.find(visitorDao.find(100),
				bookDao.find(100));
		assertNotNull(fr);
	}

	@Test
	public void testFindVisitor() {
		List<FavoriteRecord> frs = favouriteDao.find(visitorDao.find(100));
		assertEquals(2,frs.size());
	}

}
