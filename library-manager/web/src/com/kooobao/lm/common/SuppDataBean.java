package com.kooobao.lm.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import com.kooobao.common.domain.entity.StatusUtils;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFStartupAware;
import com.kooobao.lm.bizflow.entity.TransactionState;
import com.kooobao.lm.book.BookService;
import com.kooobao.lm.book.entity.Category;

public class SuppDataBean extends AbstractBean implements JSFStartupAware {

	private List<SelectItem> transactionStates;

	private List<Category> categories;

	@Override
	public void init() {
		transactionStates = new ArrayList<SelectItem>();
		for (TransactionState state : TransactionState.values()) {
			transactionStates
					.add(new SelectItem(state, StatusUtils.text(state)));
		}
		
		categories = getBookService().getRootCategories();
	}

	@Override
	public void dispose() {

	}

	public List<Category> getCategories() {
		return categories;
	}

	public List<SelectItem> getTransactionStates() {
		return transactionStates;
	}

	public void setTransactionStates(List<SelectItem> transactionStates) {
		this.transactionStates = transactionStates;
	}

	public Date getCurrentTime() {
		return new Date();
	}

	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

}
