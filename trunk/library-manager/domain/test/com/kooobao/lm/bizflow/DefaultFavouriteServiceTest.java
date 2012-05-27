package com.kooobao.lm.bizflow;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.bizflow.dao.FavouriteDao;
import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.Visitor;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = true)
@ContextConfiguration(locations = { "/application-context.xml" })
public class DefaultFavouriteServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	DefaultFavouriteService favouriteService;

	@Resource
	VisitorDao visitorDao;

	@Resource
	BookDao bookDao;

	@Resource
	FavouriteDao favouriteDao;

	@Before
	public void prepare() {
		Visitor v = new Visitor();
		v.setOid(100);
		visitorDao.store(v);

		Book book = new Book();
		book.setOid(120);
		bookDao.store(book);

		Book book1 = new Book();
		book1.setOid(130);
		bookDao.store(book1);

		FavoriteRecord fr = new FavoriteRecord();
		fr.setVisitor(v);
		fr.setFavorite(book);
		favouriteDao.store(fr);
	}

	@Test
	public void testSearchFavoriteRecords() {
		List<FavoriteRecord> favs = favouriteService
				.searchFavoriteRecords(visitorDao.find(100));
		assertEquals(1, favs.size());
		assertEquals(120, favs.get(0).getFavorite().getOid());
	}

	@Test
	public void testAddFavorite() {
		favouriteService.addFavorite(visitorDao.find(100), 130);
		List<FavoriteRecord> favs = favouriteService
				.searchFavoriteRecords(visitorDao.find(100));
		assertEquals(2, favs.size());
		assertTrue(120 == favs.get(0).getFavorite().getOid()
				|| 130 == favs.get(0).getFavorite().getOid());
		assertTrue(120 == favs.get(1).getFavorite().getOid()
				|| 130 == favs.get(1).getFavorite().getOid());
	}

	@Test
	public void testDeleteFavorite() {
		favouriteService.deleteFavorite(visitorDao.find(100), 120);
		List<FavoriteRecord> favs = favouriteService
				.searchFavoriteRecords(visitorDao.find(100));
		assertEquals(0, favs.size());
	}

}
