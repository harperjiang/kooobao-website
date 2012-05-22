package com.kooobao.lm.bizflow;

import java.util.List;

import com.kooobao.lm.bizflow.dao.FavouriteDao;
import com.kooobao.lm.bizflow.entity.FavoriteRecord;
import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.profile.entity.Visitor;

public class DefaultFavouriteService implements FavouriteService {

	public List<FavoriteRecord> searchFavoriteRecords(Visitor visitor) {
		return getFavouriteDao().find(visitor);
	}

	public void addFavorite(Visitor visitor, long bookOid) {
		Book book = getBookDao().find(bookOid);
		if (book == null)
			return;
		FavoriteRecord fr = new FavoriteRecord();
		fr.setVisitor(visitor);
		fr.setFavorite(book);
		getFavouriteDao().store(fr);
	}

	public void deleteFavorite(Visitor visitor, long bookOid) {
		Book book = getBookDao().find(bookOid);
		if (null != book) {
			FavoriteRecord fr = getFavouriteDao().find(visitor, book);
			getFavouriteDao().remove(fr);
		}
	}

	private FavouriteDao favouriteDao;

	private BookDao bookDao;

	public FavouriteDao getFavouriteDao() {
		return favouriteDao;
	}

	public void setFavouriteDao(FavouriteDao favouriteDao) {
		this.favouriteDao = favouriteDao;
	}

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

}
