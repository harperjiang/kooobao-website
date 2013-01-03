package com.kooobao.ecom.storage.supply.entity;

import org.harper.bookstore.domain.profile.ContactInfo;

public class OrderContact extends ContactInfo {

	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
