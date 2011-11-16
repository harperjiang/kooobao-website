package com.kooobao.gsm.service;

import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

public class DeliveryService {

	public Delivery save(Delivery delivery) {
		return null;
	}

	private DeliveryDao deliveryDao;

	private OrderDao orderDao;

	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}

	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

}
