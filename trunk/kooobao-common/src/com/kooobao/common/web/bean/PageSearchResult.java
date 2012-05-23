package com.kooobao.common.web.bean;

import java.util.List;

public class PageSearchResult<T> {

	private int count;

	private List<T> result;

	public PageSearchResult(int count, List<T> result) {
		this.count = count;
		this.result = result;
	}

	/**
	 * @deprecated Use {@link #getCount()} instead
	 * @return
	 */
	public int getPageCount() {
		return count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * @deprecated Use {@link #setCount(int)} instead
	 * @param pageCount
	 */
	public void setPageCount(int pageCount) {
		this.count = pageCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
