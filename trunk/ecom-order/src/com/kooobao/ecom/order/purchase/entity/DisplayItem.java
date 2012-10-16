package com.kooobao.ecom.order.purchase.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "order_purchase_ditem")
public class DisplayItem extends SimpleEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "product_count")
	private int count;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	@Column(name = "actual_price")
	private BigDecimal actualPrice;

	@ManyToOne
	@JoinColumn(name = "purchase_id", referencedColumnName = "obj_id")
	private Purchase header;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
	}

	public Purchase getHeader() {
		return header;
	}

	public void setHeader(Purchase header) {
		this.header = header;
	}

	public BigDecimal getDiscount() {
		return getActualPrice().divide(
				getUnitPrice().multiply(new BigDecimal(count)), 4,
				RoundingMode.HALF_UP);
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
