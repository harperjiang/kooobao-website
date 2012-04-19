package com.kooobao.cws.web.index;

import java.util.List;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.service.book.BookService;

public class IndexPageBean extends AbstractBean {

	public String accessCategory() {
		return "success";
	}
	
	public String accessBook() {
		return "success";
	}
	
	public List<Book> getLatestBooks() {
		return getBookService().getLatestBooks();
	}

	public List<Book> getHotBooks() {
		return getBookService().getHotBooks();
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
