package com.kooobao.ecom.storage.stock.entity;

import java.math.BigDecimal;

import org.harper.bookstore.domain.Entity;
import org.harper.bookstore.domain.Item;
import org.harper.bookstore.domain.profile.Book;

public class StockTakingItem extends Entity implements Item {

	private Book book;

	private int currentCount;

	private int originCount;

	private BigDecimal unitPrice;

	private StockTaking header;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(int currentCount) {
		this.currentCount = currentCount;
	}

	public int getOriginCount() {
		return originCount;
	}

	public void setOriginCount(int originCount) {
		this.originCount = originCount;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public StockTaking getHeader() {
		return header;
	}

	public void setHeader(StockTaking header) {
		this.header = header;
	}

	@Override
	public int getCount() {
		return currentCount;
	}

	@Override
	public void setCount(int count) {
		setCurrentCount(count);
	}

	public BigDecimal getDiscrepancy() {
		if(null == unitPrice)
			return null;
		return new BigDecimal(currentCount-originCount).multiply(unitPrice);
	}
}
