package com.kooobao.lm.book;

import java.util.ArrayList;
import java.util.List;

public class DummyBookService implements BookService {

	private List<Book> books = initBooks();
	
	@Override
	public List<Book> findRecommend(List<Book> selected) {
		return selected;
	}

	@Override
	public List<String> getHotWords() {
		List<String> words = new ArrayList<String>();
		words.add("Good");
		words.add("Bad");
		return words;
	}

	@Override
	public Book getBook(long oid) {
		for(Book book : books) {
			if(book.getOid() == oid)
				return book;
		}
		return null;
	}

	@Override
	public List<Book> searchBooks(String keyword) {
		return books;
	}

	private List<Book> initBooks() {
		ArrayList<Book> bks = new ArrayList<Book>();

		Book book = new Book();
		book.setOid(1);
		book.setName("这是第一本书");
		book.setBrief("这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓");
		book.setAuthor("大头");
		book.setPublisher("猪头出版社");
		bks.add(book);

		book = new Book();
		book.setOid(2);
		book.setName("这是第二本书");
		book.setBrief("这是第二本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓.这是第一本书的简介，有点长有点短，反正都是废话，长短也无所谓");
		book.setAuthor("大头");
		book.setPublisher("猪头出版社");
		bks.add(book);

		return bks;
	}
}
