package com.kooobao.lm.purchase.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.kooobao.lm.book.entity.Book;

@Embeddable
public class PurchaseItem {

	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "book_count")
	private int count;

	@Column(name = "discount")
	private BigDecimal discount;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

}
