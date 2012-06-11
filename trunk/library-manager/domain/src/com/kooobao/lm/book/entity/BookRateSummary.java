package com.kooobao.lm.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_book_ratesum")
public class BookRateSummary extends SimpleEntity {

	@Column(name = "rate1")
	private int rate1;

	@Column(name = "rate2")
	private int rate2;

	@Column(name = "rate3")
	private int rate3;

	@Column(name = "rate4")
	private int rate4;

	@Column(name = "rate5")
	private int rate5;

	@OneToOne
	@JoinColumn(name = "book_id")
	private Book book;

	@Column(name = "merge_mark")
	private boolean mergeMark;

	public boolean isMergeMark() {
		return mergeMark;
	}

	public void setMergeMark(boolean mergeMark) {
		this.mergeMark = mergeMark;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getRate() {
		if (rate1 + rate2 + rate3 + rate4 + rate5 == 0) {
			return 0;
		}
		return (int) Math.ceil(((float) (rate1 * 1 + rate2 * 2 + rate3 * 3
				+ rate4 * 4 + rate5 * 5))
				/ ((float) rate1 + rate2 + rate3 + rate4 + rate5));
	}

	public int getRate1() {
		return rate1;
	}

	public void setRate1(int rate1) {
		this.rate1 = rate1;
	}

	public int getRate2() {
		return rate2;
	}

	public void setRate2(int rate2) {
		this.rate2 = rate2;
	}

	public int getRate3() {
		return rate3;
	}

	public void setRate3(int rate3) {
		this.rate3 = rate3;
	}

	public int getRate4() {
		return rate4;
	}

	public void setRate4(int rate4) {
		this.rate4 = rate4;
	}

	public int getRate5() {
		return rate5;
	}

	public void setRate5(int rate5) {
		this.rate5 = rate5;
	}
}
