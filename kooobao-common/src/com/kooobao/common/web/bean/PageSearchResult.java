package com.kooobao.common.web.bean;

import java.util.List;

public class PageSearchResult<T> {

	private int pageCount;

	private List<T> result;
	
	public PageSearchResult(int count,List<T> result) {
		this.pageCount = count;
		this.result = result;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
