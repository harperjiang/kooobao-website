package com.kooobao.cws.service.book;

import java.util.List;

import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.domain.book.BookDao;
import com.kooobao.cws.domain.book.Category;
import com.kooobao.cws.domain.book.CategoryDao;

public class DefaultBookService implements BookService {

	private int LIMIT = 10;

	@Override
	public List<Book> getLatestBooks() {
		return getBookDao().getLatestBooks(LIMIT);
	}

	@Override
	public List<Book> getHotBooks() {
		return getBookDao().getHotBooks(LIMIT);
	}

	@Override
	public List<Book> findBooks(String keyword) {
		return getBookDao().findBooks(keyword);
	}

	@Override
	public List<Book> getBooksUnderCategory(Category category) {
		if (category.isLeaf()) {
			return getBookDao().getByCategory(category, BookDao.UNLIMITED);
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Book getBook(long bookOid) {
		return getBookDao().find(bookOid);
	}

	@Override
	public Book getFirstBookUnderCategory(Category category) {
		Category current = category;
		while(!current.isLeaf()) {
			current = current.getChildren().get(0);
		}
		return getBookDao().getByCategory(current, 1).get(0);
	}

	@Override
	public void saveBook(Book book) {
		getBookDao().store(book);
	}

	@Override
	public void removeBook(Book book) {
		getBookDao().remove(book);
	}

	@Override
	public List<Category> getRootCategories() {
		return getCategoryDao().getCategory(null);
	}

	@Override
	public Category getCategory(long oid) {
		return getCategoryDao().find(oid);
	}

	private BookDao bookDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}