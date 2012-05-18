package com.kooobao.lm.bizflow;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kooobao.lm.book.entity.Book;

public class Cart {

	private String uuid;

	private Date updateTime;
	
	private List<Book> books = new ArrayList<Book>();

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void add(Book book) {
		books.add(book);
	}

	public List<Book> getBooks() {
		return books;
	}

}
