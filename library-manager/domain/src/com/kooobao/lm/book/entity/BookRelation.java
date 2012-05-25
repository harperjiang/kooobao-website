package com.kooobao.lm.book.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lm_book_relation")
public class BookRelation {

	@OneToOne
	@JoinColumn(name = "from_book")
	private Book from;

	@OneToOne
	@JoinColumn(name = "to_book")
	private Book to;

	@Column(name = "score")
	private BigDecimal score;

	public Book getFrom() {
		return from;
	}

	public void setFrom(Book from) {
		this.from = from;
	}

	public Book getTo() {
		return to;
	}

	public void setTo(Book to) {
		this.to = to;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

}
