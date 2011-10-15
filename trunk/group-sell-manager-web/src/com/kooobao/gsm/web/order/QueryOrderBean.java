package com.kooobao.gsm.web.order;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.dao.OrderDao.SearchBean;
import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.Order;

@ManagedBean
@SessionScoped
public class QueryOrderBean extends AbstractBean {

	private String groupName;

	private String customer;

	private String contactName;

	private List<Order> orders;

	private boolean internal;

	@ManagedProperty(value = "#{orderDao}")
	private OrderDao orderDao;

	public String search() {
		if (StringUtils.isEmpty(getCustomer())
				&& StringUtils.isEmpty(getContactName())) {
			addMessage(FacesMessage.SEVERITY_WARN, "至少输入一项查询条件");
			return "failed";
		}
		setOrders(getOrderDao().searchOrders(
				new SearchBean(getGroupName(), getCustomer(), getContactName(),
						null, null)));
		internal = false;
		return "success";
	}

	public String searchUnprepared() {
		setOrders(getOrderDao().searchOrders(
				new SearchBean(getGroupName(), getCustomer(), getContactName(),
						null, new String[] {
								DeliveryStatus.NOT_PREPARED.name(),
								DeliveryStatus.PARTIALLY_PREPARED.name(),
								DeliveryStatus.PARTIALLY_DELIVERED.name() })));
		internal = true;
		return "success";
	}

	public String view() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Order select = (Order) dataTable.getRowData();
		ViewOrderBean viewOrderBean = findBean("viewOrderBean");
		viewOrderBean.setOrderId(select.getOid());
		viewOrderBean.setInternal(internal);
		return "success";
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public OrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean internal) {
		this.internal = internal;
	}
}
