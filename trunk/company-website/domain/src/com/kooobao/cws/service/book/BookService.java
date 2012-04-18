package com.kooobao.cws.service.book;

import java.util.List;

import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.domain.book.Category;

public interface BookService {

	List<Book> getLatestBooks();
	
	List<Book> getHotBooks();
	
	List<Book> findBooks(String name,String keyword);
	
	void saveBook(Book book);
	
	void removeBook(Book book);
	
	List<Category> getRootCategory();
}
