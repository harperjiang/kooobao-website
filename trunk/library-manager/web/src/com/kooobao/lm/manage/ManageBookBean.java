package com.kooobao.lm.manage;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.BookService;
import com.kooobao.lm.book.entity.Book;

@ManagedBean(name = "manageBookBean")
@SessionScoped
public class ManageBookBean extends PageSearchBean {

	private Book book = new Book();

	private List<Book> books;

	private String keyword;

	private String tags;

	@Override
	public String search() {
		PageSearchResult<Book> result = getBookService().searchBooks(
				getKeyword(), getRecordStart(), getRecordStop());
		setRecordCount(result.getCount());
		setBooks(result.getResult());
		return "success";
	}

	public String select() {
		setBook(getBookService().getBook(
				Long.parseLong(getParameter("book_id"))));
		return "success";
	}

	public String save() {
		setBook(getBookService().save(getBook()));
		return "success";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
