package com.kooobao.lm.book;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;

@ManagedBean(name = "searchBookBean")
@SessionScoped
public class SearchBookBean extends PageSearchBean {

	public String search() {
		PageSearchResult<Book> result = getBookService().searchBooks(
				getKeyword(), getRecordStart(), getRecordStop());
		setSearched(result.getResult());
		setRecordCount(result.getPageCount());
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
