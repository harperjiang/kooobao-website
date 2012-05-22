package com.kooobao.lm.optlog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.lm.book.entity.Book;

@Entity
@Table(name = "lm_optlog_borrowcount")
public class BorrowCount {

	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "borrow_count")
	private int count;

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

}
