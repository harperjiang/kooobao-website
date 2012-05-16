package com.kooobao.lm.book;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean(name = "bookBean")
@SessionScoped
public class BookBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		// FIXIT for test only
		book = getBookService().getPopularBooks().get(0);
		recommendBooks = getBookService().findRecommend(book);
	}
	
	private Book book;

	private List<Book> recommendBooks;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getRecommendBooks() {
		return recommendBooks;
	}

	@ManagedProperty("#{bookService}")
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	
}
