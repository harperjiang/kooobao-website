package com.kooobao.gsm.web.delivery;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.Validate;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;
import com.kooobao.gsm.domain.entity.delivery.DeliveryItem;
import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.service.DeliveryService;

@ManagedBean
@SessionScoped
public class PrepareDeliveryBean extends AbstractBean {

	private Order order;

	private Delivery delivery;

	@ManagedProperty("#{orderDao}")
	private OrderDao orderDao;

	@ManagedProperty("#{deliveryService}")
	private DeliveryService deliveryService;

	private long orderId;

	@Override
	public void onPageLoad() {
		if (0 == getOrderId())
			return;
		setOrder(getOrderDao().find(getOrderId()));
		Delivery delivery = new Delivery();
		for (OrderItem item : order.getItems()) {
			DeliveryItem ditem = new DeliveryItem();
			ditem.setOrderItem(item);
			ditem.setCount(0);
			delivery.addItem(ditem);
		}
		delivery.getContact().setAddress(order.getContact().getAddress());
		delivery.getContact().setLocation(order.getContact().getLocation());
		delivery.getContact().setName(order.getContact().getName());
		delivery.getContact().setPhone(order.getContact().getPhone());
		setDelivery(delivery);
		super.onPageLoad();
	}

	protected boolean checkZeroItem() {
		for (DeliveryItem di : delivery.getItems()) {
			if (0 == di.getCount()) {
				di.setHeader(null);
				delivery.getItems().remove(di);
			}
		}
		if (0 == delivery.getItems().size())
			return false;
		return true;
	}

	public String markShortage() {
		Validate.isTrue(DeliveryStatus.NOT_PREPARED.name().equals(
				getOrder().getDeliveryStatus()));
		getOrder().setDeliveryStatus(DeliveryStatus.SHORT_OF_STORAGE.name());
		setOrder(getOrderDao().store(getOrder()));
		return "success";
	}

	public String quickPrepare() {
		for (DeliveryItem di : delivery.getItems()) {
			OrderItem oi = di.getOrderItem();
			di.setCount(oi.getCount() - oi.getPreparedCount());
		}
		return save();
	}

	public String save() {
		if (!checkZeroItem()) {
			return "failed";
		}
		delivery.prepare();
		setDelivery(getDeliveryService().save(getDelivery()));
		return "saved";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public DeliveryService getDeliveryService() {
		return deliveryService;
	}

	public void setDeliveryService(DeliveryService deliveryService) {
		this.deliveryService = deliveryService;
	}

}
