package com.kooobao.cws.web.book;

import java.util.List;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.cws.domain.book.Category;
import com.kooobao.cws.service.book.BookService;

public class CategoryInfoBean extends AbstractBean {

	private List<Category> roots;

	private List<Category> leaves;

	public CategoryInfoBean() {
	}

	protected synchronized void init() {
		if (null != roots)
			return;
		roots = getBookService().getRootCategory();
	}

	public List<Category> getRoots() {
		if (null == roots) {
			init();
		}
		return roots;
	}

	public synchronized List<Category> getLeaves() {
		if (null == leaves)
			init();
		return leaves;
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
}
