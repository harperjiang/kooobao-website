package com.kooobao.lm.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name="lm_book_comment")
public class Comment extends SimpleEntity {

	@Column(name="visitor_id")
	private String visitorId;
	
	@Column(name="rating")
	private int rating;
	
	@Column(name="content")
	private String content;
	
	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
