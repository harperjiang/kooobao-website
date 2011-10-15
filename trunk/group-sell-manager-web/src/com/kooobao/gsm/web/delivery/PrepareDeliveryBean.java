package com.kooobao.gsm.web.delivery;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.Validate;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.dao.SupportDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;
import com.kooobao.gsm.domain.entity.delivery.DeliveryItem;
import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;
import com.kooobao.gsm.service.OrderService;

@ManagedBean
@SessionScoped
public class PrepareDeliveryBean extends AbstractBean {

	private Order order;

	private Delivery delivery;

	@ManagedProperty("#{orderDao}")
	private OrderDao orderDao;

	@ManagedProperty("#{deliveryDao}")
	private DeliveryDao deliveryDao;

	@ManagedProperty("#{supportDao}")
	private SupportDao supportDao;

	private long orderId;

	@Override
	public void onPageLoad() {
		if (0 == getOrderId() || getDelivery() != null) {
			if (null == getDelivery())
				delivery = new Delivery();
			return;
		}

		setOrder(getOrderDao().find(getOrderId()));
		Delivery delivery = new Delivery();
		for (OrderItem item : order.getItems()) {
			DeliveryItem ditem = new DeliveryItem();
			ditem.setOrderItem(item);
			ditem.setCount(item.getCount() - item.getPreparedCount());
			delivery.addItem(ditem);
		}
		delivery.getContact().setAddress(order.getContact().getAddress());
		delivery.getContact().setLocation(order.getContact().getLocation());
		delivery.getContact().setName(order.getContact().getName());
		delivery.getContact().setPhone(order.getContact().getPhone());
		setDelivery(delivery);
		super.onPageLoad();
	}

	protected boolean checkItem() {
		List<DeliveryItem> toRemove = new ArrayList<DeliveryItem>();
		for (DeliveryItem di : delivery.getItems()) {
			if (0 == di.getCount()) {
				di.setHeader(null);
				toRemove.add(di);
			}
			if (di.getCount() > di.getOrderItem().getCount()
					- di.getOrderItem().getPreparedCount()) {
				addMessage(FacesMessage.SEVERITY_WARN, "备货数量超过未发货数量");
				return false;
			}
		}
		delivery.getItems().removeAll(toRemove);
		if (0 == delivery.getItems().size())
			return false;
		return true;
	}

	public String markShortage() {
		Validate.isTrue(DeliveryStatus.NOT_PREPARED.name().equals(
				getOrder().getDeliveryStatus()));
		getOrder().setDeliveryStatus(DeliveryStatus.SHORT_OF_STORAGE.name());
		updateOrder(getOrder());
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
		if (!checkItem()) {
			return "failed";
		}
		delivery.prepare();
		// delivery.setGrossWeight(getSupportDao().getWeightRule(delivery)
		// .getPackageWeight(delivery));
		updateOrder(delivery.getOrder());
		// setOrder(getOrderDao().store(delivery.getOrder()));
//		setDelivery(getDeliveryDao().store(getDelivery()));
//		getOrderDao().store(getOrder());
		getDeliveryDao().store(getDelivery());
		return "saved";
	}

	protected void updateOrder(Order order) {
		OrderService.updateOrderTotal(order);
		// Update Package Weight
		// Update Total Amount
		GrossWeightRule gwRule = getSupportDao().getWeightRule(order);
		DeliveryAmountRule rule = getSupportDao().getAmountRule(order);
		OrderService.updateOrderTotalAmount(order, rule, gwRule);
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

	public SupportDao getSupportDao() {
		return supportDao;
	}

	public void setSupportDao(SupportDao supportDao) {
		this.supportDao = supportDao;
	}

	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}

	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

}
