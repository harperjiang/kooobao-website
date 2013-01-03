package com.kooobao.ecom.order.delivery.entity;

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
@Table(name = "order_delivery_item")
public class DeliveryItem extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name = "delivery_id", referencedColumnName = "obj_id")
	private Delivery header;

	@OneToOne
	@JoinColumn(name = "order_item_id", referencedColumnName = "obj_id")
	private PurchaseItem orderItem;

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "obj_id")
	private Product product;

	@Column(name = "product_count")
	private int count;

	public Delivery getHeader() {
		return header;
	}

	public void setHeader(Delivery header) {
		this.header = header;
	}

	public PurchaseItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(PurchaseItem orderItem) {
		this.orderItem = orderItem;
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
