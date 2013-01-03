package com.kooobao.ecom.order.returnreq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.ecom.order.purchase.entity.PurchaseItem;
import com.kooobao.ecom.product.entity.Product;

@Entity
@Table(name = "order_returnreq_item")
public class ReturnItem extends SimpleEntity {

	@OneToOne
	@JoinColumn(name = "original_item_id", referencedColumnName = "obj_id")
	private PurchaseItem originalItem;

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "obj_id")
	private Product product;

	@Column(name = "product_count")
	private int count;

	@ManyToOne
	@JoinColumn(name = "request_id", referencedColumnName = "obj_id")
	private ReturnRequest header;

	public PurchaseItem getOriginalItem() {
		return originalItem;
	}

	public void setOriginalItem(PurchaseItem originalItem) {
		this.originalItem = originalItem;
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

	public ReturnRequest getHeader() {
		return header;
	}

	public void setHeader(ReturnRequest header) {
		this.header = header;
	}

}
