package com.kooobao.gsm.web.order;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import org.apache.commons.lang.Validate;
import org.springframework.dao.EmptyResultDataAccessException;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.dao.ProductDao;
import com.kooobao.gsm.domain.dao.SupportDao;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.order.OrderStatus;
import com.kooobao.gsm.domain.entity.product.Product;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;
import com.kooobao.gsm.service.OrderService;

@ManagedBean
@SessionScoped
public class MaintainOrderBean extends AbstractBean {

	private Order order;

	private String productId;

	private Map<String, Integer> index;

	private long orderId;

	public MaintainOrderBean() {
		order = new Order();
		index = new HashMap<String, Integer>();
	}

	public void onPageLoad() {
		if (null == order)
			order = new Order();
		index.clear();
		int count = 0;
		for (OrderItem item : order.getItems()) {
			index.put(item.getProduct().getCode(), count++);
		}
	}

	@ManagedProperty(value = "#{productDao}")
	private ProductDao productDao;

	@ManagedProperty(value = "#{orderDao}")
	private OrderDao orderDao;

	@ManagedProperty(value = "#{supportDao}")
	private SupportDao supportDao;

	public String addProduct() {
		if (null == productId)
			return "success";

		Product product = null;
		try {
			product = getProductDao().findProductById(getProductId());
		} catch (EmptyResultDataAccessException e) {
			addMessage(FacesMessage.SEVERITY_WARN, "未找到产品:" + getProductId());
			return "failed";
		}
		if (!index.containsKey(productId)) {
			OrderItem item = new OrderItem();
			item.setCount(1);
			item.setProduct(product);
			item.setUnitPrice(product.getRefUnitPrice());
			order.addItem(item);
			index.put(productId, order.getItems().size() - 1);
		} else {
			OrderItem item = order.getItems().get(index.get(productId));
			item.setCount(item.getCount() + 1);
		}

		refresh();
		return "success";
	}

	public String refresh() {
		OrderService.updateOrderTotal(order);
		// Update Package Weight
		// Update Total Amount
		GrossWeightRule gwRule = getSupportDao().getWeightRule(order);

		DeliveryAmountRule rule = getSupportDao().getAmountRule(order);
		OrderService.updateOrderTotalAmount(order, rule, gwRule);
		return "success";
	}

	public String increase() {
		UIData dataTable = (UIData) getComponent("dataTable");
		OrderItem select = (OrderItem) dataTable.getRowData();
		select.setCount(select.getCount() + 1);
		return refresh();
	}

	public String decrease() {
		UIData dataTable = (UIData) getComponent("dataTable");
		OrderItem select = (OrderItem) dataTable.getRowData();
		if (select.getCount() > 0) {
			select.setCount(select.getCount() - 1);
			refresh();
		}

		return "success";

	}

	public String save() {
		// order.setDeliveryStatus(DeliveryStatus.NOT_PREPARED.name());
		Validate.isTrue(OrderStatus.CONFIRMED.name().equals(order.getStatus()));
		// Validate.isTrue(DeliveryStatus.)
		// TODO Unmodifiable Status
		if (BigDecimal.ZERO.equals(order.getTotalAmount())) {
			addMessage(FacesMessage.SEVERITY_WARN, "不能保存总金额为0的订单");
			return "failed";
		}
		for (OrderItem item : order.getItems()) {
			if (item.getCount() < item.getPreparedCount()) {
				addMessage(FacesMessage.SEVERITY_WARN, "订单数量不得修改为小于已备货数量");
				return "failed";
			}
		}
		refresh();
		Order neworder = getOrderDao().store(order);
		setOrderId(neworder.getOid());

		((ViewOrderBean) findBean("viewOrderBean")).setInternal(true);
		order = null;
		return "success";
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
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

	public SupportDao getSupportDao() {
		return supportDao;
	}

	public void setSupportDao(SupportDao supportDao) {
		this.supportDao = supportDao;
	}

}
