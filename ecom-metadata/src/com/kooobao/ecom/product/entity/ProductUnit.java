package com.kooobao.ecom.product.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class ProductUnit {

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "obj_id")
	private Product product;

	@Column(name = "product_count")
	private int count;

	public ProductUnit() {
	}

	public ProductUnit(Product product, int count) {
		super();
		this.product = product;
		this.count = count;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
