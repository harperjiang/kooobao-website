package com.kooobao.cws.service.book;

import java.util.List;

import com.kooobao.cws.domain.book.Book;

public interface BookService {

	List<Book> getLatestBooks();
	
	List<Book> getHotBooks();
	
	void addBook(Book book);
	
	void removeBook(Book book);
	
	List<Book> findBooks(String name,String keyword);
}
