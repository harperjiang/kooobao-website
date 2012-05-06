package com.kooobao.lm.bizflow;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.book.Book;

@ManagedBean
@SessionScoped
public class BorrowConfirmBean extends AbstractBean {

	private List<Book> borrowed;

	public List<Book> getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(List<Book> borrowed) {
		this.borrowed = borrowed;
	}
	
	
}
