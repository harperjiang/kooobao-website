package com.kooobao.gsm.web.order;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.dao.SupportDao;
import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;
import com.kooobao.gsm.service.OrderService;
import com.kooobao.gsm.web.delivery.PrepareDeliveryBean;

@ManagedBean
@SessionScoped
public class ViewOrderBean extends AbstractBean {

	private Order order;

	private transient Order copy;

	@ManagedProperty("#{orderDao}")
	private OrderDao orderDao;

	private long orderId;

	@ManagedProperty("#{supportDao}")
	private SupportDao supportDao;

	@Override
	public void onPageLoad() {
		if (orderId != 0) {
			setOrder(getOrderDao().find(getOrderId()));
			orderId = 0;
			copy();
		}
		super.onPageLoad();
	}

	public String modify() {
		MaintainOrderBean mob = findBean("maintainOrderBean");
		mob.setOrderId(order.getOid());
		mob.setOrder(order);
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

	public String cancel() {
		try {
			order.cancel();
		} catch (IllegalStateException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "请先取消或删除已生成的运单");
			return "false";
		} catch (IllegalArgumentException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, "订单已处于取消状态");
		}
		order = getOrderDao().store(order);
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

	private void copy() {
		copy = new Order();
		copy.getContact().setAddress(getOrder().getAddress());
		for (OrderItem item : getOrder().getItems()) {
			OrderItem copyItem = new OrderItem();
			copyItem.setCount(item.getPreparedCount());
			copyItem.setUnitPrice(item.getUnitPrice());
			copyItem.setProduct(item.getProduct());
			copy.addItem(copyItem);
		}
		OrderService.updateOrderTotal(copy);
		// Update Package Weight
		// Update Total Amount
		GrossWeightRule gwRule = getSupportDao().getWeightRule(copy);

		DeliveryAmountRule rule = getSupportDao().getAmountRule(copy);
		OrderService.updateOrderTotalAmount(copy, rule, gwRule);
	}

	public String getRefundStatus() {
		if (null == order)
			return "";
		if (DeliveryStatus.NOT_PREPARED.name()
				.equals(order.getDeliveryStatus()))
			return "";

		if (!(DeliveryStatus.PARTIALLY_DELIVERED.name().equals(
				order.getDeliveryStatus())
				|| DeliveryStatus.PARTIALLY_PREPARED.name().equals(
						order.getDeliveryStatus()) || !order.getPaidAmount()
				.equals(order.getTotalAmount()))) {
			return "无需退款";
		}

		int compare = order.getPaidAmount().compareTo(copy.getAmount());
		if (0 == compare) {
			return "无需退款";
		}
		if (compare > 0) {
			return "需退款" + order.getPaidAmount().subtract(copy.getAmount());
		}
		if (compare < 0) {
			return "需补款" + copy.getAmount().subtract(order.getPaidAmount());
		}
		return null;
	}

	public Order getCopy() {
		return copy;
	}

	public void setCopy(Order copy) {
		this.copy = copy;
	}

	public SupportDao getSupportDao() {
		return supportDao;
	}

	public void setSupportDao(SupportDao supportDao) {
		this.supportDao = supportDao;
	}

}
