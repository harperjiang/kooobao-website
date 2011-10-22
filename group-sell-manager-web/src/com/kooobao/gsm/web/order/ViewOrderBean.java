package com.kooobao.gsm.web.order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.web.delivery.PrepareDeliveryBean;

@ManagedBean
@SessionScoped
public class ViewOrderBean extends AbstractBean {

	private Order order;

	@ManagedProperty("#{orderDao}")
	private OrderDao orderDao;

	private long orderId;

	private boolean internal;

	@Override
	public void onPageLoad() {
		if (orderId != 0) {
			setOrder(getOrderDao().find(getOrderId()));
			orderId = 0;
		}
		super.onPageLoad();
	}

	public String modify() {
		MaintainOrderBean mob = findBean("maintainOrderBean");
		mob.setOrderId(order.getOid());
		return "success";
	}

	public String prepare() {
		PrepareDeliveryBean mob = findBean("prepareDeliveryBean");
		// mob.setOrderId(getOrderId());
		mob.setOrderId(getOrder().getOid());
		mob.setDeliveryId(0);
		return "success";
	}

	public String rollback() {
		order = getOrderDao().rollback(order);
		return "success";
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

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean internal) {
		this.internal = internal;
	}

}
