package com.kooobao.lm.bizflow.entity;

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.book.entity.Book;

public class Adjust extends SimpleEntity {

	private Book book;

	private int adjustCount;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getAdjustCount() {
		return adjustCount;
	}

	public void setAdjustCount(int adjustCount) {
		this.adjustCount = adjustCount;
	}

	
}
