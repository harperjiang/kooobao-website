package com.kooobao.common.web.bean;

public class PageSearchBean extends AbstractBean {

	private int pageCount;

	private int currentPage = 1;

	private int pageSize = 10;

	public String prevPage() {
		if (currentPage > 0)
			currentPage--;
		return "success";
	}

	public String nextPage() {
		if (currentPage < pageCount)
			currentPage++;
		return "success";
	}

	protected int getRecordStart() {
		return (currentPage - 1) * pageSize;
	}

	protected int getRecordStop() {
		return currentPage * pageSize - 1;
	}

	public int getPageCount() {
		return pageCount;
	}

	protected void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
