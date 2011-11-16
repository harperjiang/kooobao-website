package com.kooobao.gsm.domain.entity.store;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.gsm.domain.entity.product.Product;

@Entity
@Table(name = "gsm_storage")
public class Storage {

	@Id
	@Column(name = "product_id", columnDefinition = "decimal(10)")
	private long productId;

	@OneToOne
	@JoinColumn(name = "product_id", columnDefinition = "decimal(10)", updatable = false, insertable = false)
	private Product product;

	@Column(name = "count", columnDefinition = "decimal(5)")
	private int count;

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

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

}
