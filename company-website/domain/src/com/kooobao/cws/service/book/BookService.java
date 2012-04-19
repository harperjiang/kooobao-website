package com.kooobao.cws.service.book;

import java.util.List;

import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.domain.book.Category;

public interface BookService {

	List<Book> getLatestBooks();

	List<Book> getHotBooks();

	List<Book> findBooks(String name, String keyword);

	List<Book> getBooksUnderCategory(Category category);
	
	Book getBook(long bookOid);

	/**
	 * Get the first book under given category, supporting sub categories
	 * 
	 * @param category
	 * @return
	 */
	Book getFirstBookUnderCategory(Category category);

	void saveBook(Book book);

	void removeBook(Book book);

	List<Category> getRootCategories();

	Category getCategory(long oid);
}
