package com.kooobao.lm.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;

@Entity
@Table(name="lm_book_stock")
public class Stock extends VersionEntity {

	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;

	@Column(name="stock_count")
	private int stock;

	@Column(name="avail_count")
	private int available;

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
