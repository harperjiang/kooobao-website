package com.kooobao.lm.book;

import java.util.List;

import com.kooobao.lm.book.dao.BookDao;
import com.kooobao.lm.book.dao.CategoryDao;
import com.kooobao.lm.book.entity.Book;
import com.kooobao.lm.book.entity.Category;

public class DefaultBookService implements BookService {

	public List<Book> findRecommend(List<Book> selected) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> findRecommend(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getHotWords() {
		// TODO Auto-generated method stub
		return null;
	}

	public Book getBook(long oid) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> searchBooks(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> getRootCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getPopularBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getNewBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getEditorRecommendBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getOtherBorrowBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Book> getBooksInCategory(Category selectedCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	public Category getCategory(long categoryOid) {
		// TODO Auto-generated method stub
		return null;
	}

	private BookDao bookDao;

	private CategoryDao categoryDao;

	public BookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

}
