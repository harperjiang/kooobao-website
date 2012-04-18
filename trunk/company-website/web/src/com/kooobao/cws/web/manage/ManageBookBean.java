package com.kooobao.cws.web.manage;

import java.util.List;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.service.book.BookService;

public class ManageBookBean extends PageSearchBean {

	@Override
	public String search() {
		// TODO Auto-generated method stub
		return null;
	}

	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	private SearchBookBean search;

	public SearchBookBean getSearch() {
		return search;
	}

	public void setSearch(SearchBookBean search) {
		this.search = search;
	}

	private Book book;

	public Book getBook() {
		if (null == book)
			book = new Book();
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public static class SearchBookBean {
		private String name;
		private String content;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}
}
