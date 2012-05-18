package com.kooobao.lm.book;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.book.entity.Book;

@ManagedBean(name = "searchBookBean")
@SessionScoped
public class SearchBookBean extends AbstractBean {

	public String search() {
		setSearched(getBookService().searchBooks(getKeyword()));
		return "success";
	}

	private List<Book> searched;

	public List<Book> getSearched() {
		return searched;
	}

	public void setSearched(List<Book> searched) {
		this.searched = searched;
	}

	public List<Book> getRecommend() {
		return getBookService().findRecommend(getSearched());
	}

	public int getSize() {
		return getSearched().size();
	}

	@ManagedProperty("#{bookService}")
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
