package com.kooobao.lm.book;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.SelfRefreshBean;

public class HotSearchBean extends SelfRefreshBean {

	@Override
	protected void refresh() {
		List<String> hotWords = getBookService().getHotWords();
		setHotWord1(hotWords.get(0));
		setHotWord2(hotWords.get(1));
	}
	
	public String hotSearch() {
		String keyword = getParameter("word");
		if(!StringUtils.isEmpty(keyword)) {
			SearchBookBean searchBean = findBean("searchBookBean");
			searchBean.setKeyword(keyword);
			return searchBean.search();
		}
		return "failed";
	}
	
	private String hotWord1;
	
	private String hotWord2;

	public String getHotWord1() {
		return hotWord1;
	}

	public void setHotWord1(String hotWord1) {
		this.hotWord1 = hotWord1;
	}

	public String getHotWord2() {
		return hotWord2;
	}

	public void setHotWord2(String hotWord2) {
		this.hotWord2 = hotWord2;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
