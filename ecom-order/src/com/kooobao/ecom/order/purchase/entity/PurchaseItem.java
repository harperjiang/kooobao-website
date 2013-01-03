package com.kooobao.ecom.order.purchase.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.ecom.product.entity.Product;

@Entity
@Table(name = "order_purchase_item")
public class PurchaseItem extends SimpleEntity {

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "obj_id")
	private Product product;

	@Column(name = "product_count")
	private int count;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	@Column(name = "total_amount")
	private BigDecimal totalAmount;

	@ManyToOne
	@JoinColumn(name = "purchase_id", referencedColumnName = "obj_id")
	private Purchase header;

	// Reference Id
	@Column(name = "ref_id")
	private String refId;

	@Column(name = "sent_count")
	private int sentCount;

	public PurchaseItem() {
		super();
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Purchase getHeader() {
		return header;
	}

	public void setHeader(Purchase header) {
		this.header = header;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

	public int getUnsentCount() {
		return getCount() - getSentCount();
	}

	public void send(int count) {
		sentCount += count;
	}
}
