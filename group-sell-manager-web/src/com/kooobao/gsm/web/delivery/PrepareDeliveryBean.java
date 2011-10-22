package com.kooobao.gsm.web.delivery;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
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

	private long orderId;

	private Order order;

	private long deliveryId;

	private Delivery delivery;

	@ManagedProperty("#{orderDao}")
	private OrderDao orderDao;

	@ManagedProperty("#{deliveryDao}")
	private DeliveryDao deliveryDao;

	@ManagedProperty("#{supportDao}")
	private SupportDao supportDao;

	// private long orderId;

	@Override
	public void onPageLoad() {
		if (0 != orderId) {
			setOrder(getOrderDao().find(orderId));
			orderId = 0;
			deliveryId = 0;
			delivery = null;
		} else if (0 != deliveryId) {
			setDelivery(getDeliveryDao().find(deliveryId));
			setOrder(delivery.getOrder());
			deliveryId = 0;
		}
		if (null == delivery) {
			Delivery delivery = new Delivery();
			for (OrderItem item : order.getItems()) {
				DeliveryItem ditem = new DeliveryItem();
				ditem.setOrderItem(item);
				ditem.setCount(item.getCount() - item.getPreparedCount());
				if (ditem.getCount() > 0)
					delivery.addItem(ditem);
			}
			delivery.getContact().setAddress(order.getContact().getAddress());
			delivery.getContact().setLocation(order.getContact().getLocation());
			delivery.getContact().setName(order.getContact().getName());
			delivery.getContact().setPhone(order.getContact().getPhone());
			setDelivery(delivery);
		}
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

	public String quickSend() {
		if (StringUtils.isEmpty(getDelivery().getNumber())) {
			addMessage(FacesMessage.SEVERITY_WARN, "请输入运单号");
			return "false";
		}
		if (!checkItem()) {
			return "failed";
		}
		delivery.prepare();
		delivery.deliver();
		setDelivery(getDeliveryDao().store(delivery));
		return "saved";
	}

	public String save() {
		if (!checkItem()) {
			return "failed";
		}
		delivery.prepare();
		setDelivery(getDeliveryDao().store(getDelivery()));
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

	private void setOrder(Order order) {
		this.order = order;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	private void setDelivery(Delivery delivery) {
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(long deliveryId) {
		this.deliveryId = deliveryId;
	}

}
