package com.kooobao.lm.book;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.book.entity.Book;

@ManagedBean(name = "searchBookBean")
@SessionScoped
public class SearchBookBean extends PageSearchBean {

	public String search() {
		if(StringUtils.isEmpty(keyword))
		{
			addMessage(FacesMessage.SEVERITY_WARN, "关键字为空", "请输入关键字进行搜索");
			return "failed";
		}
		PageSearchResult<Book> result = getBookService().searchBooks(
				getKeyword(), getRecordStart(), getPageSize());
		setSearched(result.getResult());
		setRecordCount(result.getCount());
//		setRecommend(getBookService().findRecommend(getSearched()));
		setRecommend(getSearched());
		return "success";
	}

	private List<Book> searched;

	public List<Book> getSearched() {
		return searched;
	}

	public void setSearched(List<Book> searched) {
		this.searched = searched;
	}

	private List<Book> recommend;

	public List<Book> getRecommend() {
		return recommend;
	}

	public void setRecommend(List<Book> recommend) {
		this.recommend = recommend;
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
