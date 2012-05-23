package com.kooobao.lm.optlog.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.kooobao.lm.book.entity.Book;

@Entity
@Table(name = "lm_optlog_borrowcount")
public class BorrowCount {

	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "borrow_count")
	private long count;

	@Column(name = "update_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	public BorrowCount() {
		
	}
	
	public BorrowCount(Book book, Long count) {
		this.book = book;
		this.count = count;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
