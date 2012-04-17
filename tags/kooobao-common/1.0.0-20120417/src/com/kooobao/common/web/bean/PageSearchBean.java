package com.kooobao.common.web.bean;

public abstract class PageSearchBean extends AbstractBean {

	private int pageCount;

	private int currentPage = 1;

	private int pageSize = 10;

	public String prevPage() {
		if (currentPage > 0)
			currentPage--;
		return search();
	}

	public String nextPage() {
		if (currentPage < pageCount)
			currentPage++;
		return search();
	}

	public abstract String search();

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

	protected void setRecordCount(int recordCount) {
		setPageCount((int) Math
				.ceil(((double) recordCount / (double) getPageSize())));
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
