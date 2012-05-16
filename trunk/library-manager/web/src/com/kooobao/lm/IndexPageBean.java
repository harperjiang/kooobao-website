package com.kooobao.lm;

import java.util.List;

import com.kooobao.common.web.bean.SelfRefreshBean;
import com.kooobao.lm.book.Book;
import com.kooobao.lm.book.BookService;

public class IndexPageBean extends SelfRefreshBean {

	private List<Book> popularBooks;

	private List<Book> newBooks;

	private List<Book> editorRecommendBooks;

	private List<Book> otherBorrowBooks;

	protected void refresh() {
		popularBooks = getBookService().getPopularBooks();
		newBooks = getBookService().getNewBooks();
		editorRecommendBooks = getBookService().getEditorRecommendBooks();
		otherBorrowBooks = getBookService().getOtherBorrowBooks();
	}

	public List<Book> getPopularBooks() {
		return popularBooks;
	}

	public List<Book> getNewBooks() {
		return newBooks;
	}

	public List<Book> getEditorRecommendBooks() {
		return editorRecommendBooks;
	}

	public List<Book> getOtherBorrowBooks() {
		return otherBorrowBooks;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
}
