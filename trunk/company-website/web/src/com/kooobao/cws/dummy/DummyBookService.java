package com.kooobao.cws.dummy;

import java.util.ArrayList;
import java.util.List;

import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.service.book.BookService;

public class DummyBookService implements BookService {

	@Override
	public List<Book> getLatestBooks() {
		List<Book> books = new ArrayList<Book>();
		Book book = new Book();
		book.setPictureUrl("http://img.kooobao.cn/storage/sample.gif");
		book.setName("这是一本书");
		book.setBrief("这是关于这本书的一个Brief，大家随便看看就可以了，不要太在意");
		books.add(book);
		
		book = new Book();
		book.setPictureUrl("http://img.kooobao.cn/storage/sample.gif");
		book.setName("这是另一本书");
		book.setBrief("这是关于另一本书的一个Brief，大家随便看看就可以了，不要太在意。如果太长了怎么办呢？太长了就得戒掉一块");
		books.add(book);
		
		return books;
	}

	@Override
	public List<Book> getHotBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeBook(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Book> findBooks(String name, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
