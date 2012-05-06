package com.kooobao.lm.book;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.lm.profile.Library;

public class Stock extends VersionEntity {

	private Library library;

	private Book book;

	private int stock;

	private int available;

	public Library getLibrary() {
		return library;
	}

	public void setLibrary(Library library) {
		this.library = library;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

}
