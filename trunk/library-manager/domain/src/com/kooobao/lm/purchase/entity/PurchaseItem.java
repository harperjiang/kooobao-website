package com.kooobao.lm.purchase.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.book.entity.Book;

@Entity
@Table(name="lm_purchase_item")
public class PurchaseItem extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name="header")
	private Purchase header;
	
	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "book_count")
	private int count;

	private transient BigDecimal discount;

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

	public Purchase getHeader() {
		return header;
	}

	public void setHeader(Purchase header) {
		this.header = header;
	}

}
