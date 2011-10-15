package com.kooobao.gsm.domain.entity.delivery;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.gsm.domain.entity.order.OrderItem;

@Entity
@Table(name = "gsm_delivery_item")
public class DeliveryItem extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name = "header_id", referencedColumnName = "obj_id", columnDefinition = "decimal(10)")
	private Delivery header;

	@OneToOne(cascade = { CascadeType.MERGE })
	@JoinColumn(name = "order_item_id", referencedColumnName = "obj_id")
	private OrderItem orderItem;

	@Column(name = "item_count", columnDefinition = "decimal(5)")
	private int count;

	public Delivery getHeader() {
		return header;
	}

	public void setHeader(Delivery header) {
		this.header = header;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
