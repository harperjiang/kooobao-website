package com.kooobao.lm.book;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.SelfRefreshBean;

public class HotSearchBean extends SelfRefreshBean {

	@Override
	protected void refresh() {
		hotWords = getBookService().getHotWords();
	}

	public String hotSearch() {
		String keyword = getParameter("word");
		if (!StringUtils.isEmpty(keyword)) {
			SearchBookBean searchBean = findBean("searchBookBean");
			searchBean.setKeyword(keyword);
			return searchBean.search();
		}
		return "failed";
	}

	private List<String> hotWords;

	public List<String> getHotWords() {
		return hotWords;
	}

	public void setHotWords(List<String> hotWords) {
		this.hotWords = hotWords;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
