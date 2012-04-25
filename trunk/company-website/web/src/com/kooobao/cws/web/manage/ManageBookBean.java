package com.kooobao.cws.web.manage;

import java.util.List;

import javax.faces.component.UIData;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.cws.domain.book.Book;
import com.kooobao.cws.service.book.BookService;

public class ManageBookBean extends PageSearchBean {

	@Override
	public String search() {
		List<Book> books = getBookService().findBooks(getSearch().getKeyword());
		setBooks(books);
		return "success";
	}

	public String save() {
		getBookService().saveBook(getBook());
		return "success";
	}

	public String edit() {
		UIData dataTable = (UIData) getComponent("resultDataTable");
		Book select = (Book) dataTable.getRowData();
		setBook(select);
		return "success";
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
		if (null == search)
			search = new SearchBookBean();
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
		private String keyword;

		public String getKeyword() {
			return keyword;
		}

		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}

	}
}
