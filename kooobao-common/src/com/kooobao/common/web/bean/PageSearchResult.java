package com.kooobao.common.web.bean;

import java.util.List;

public class PageSearchResult<T> {

	private long count;

	private List<T> result;

	public PageSearchResult(long count, List<T> result) {
		this.count = count;
		this.result = result;
	}

	/**
	 * @deprecated Use {@link #getCount()} instead
	 * @return
	 */
	public long getPageCount() {
		return count;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	/**
	 * @deprecated Use {@link #setCount(long)} instead
	 * @param pageCount
	 */
	public void setPageCount(long pageCount) {
		this.count = pageCount;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

}
