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
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderStatus;
import com.kooobao.gsm.web.SupportDataBean;

@ManagedBean
@SessionScoped
public class QueryOrderBean extends AbstractBean {

	private String groupName;

	private String customer;

	private String contactName;

	private int status;

	private String refNumber;

	private List<Order> orders;

	private boolean findProblematic = true;

	@ManagedProperty(value = "#{orderDao}")
	private OrderDao orderDao;

	private SupportDataBean supportDataBean;

	@Override
	public void onPageLoad() {
		// reset();
		super.onPageLoad();
	}

	private void reset() {
		groupName = null;
		customer = null;
		contactName = null;
		status = 0;
		orders = null;
	}

	public String searchByCustomer() {
		if (StringUtils.isEmpty(getCustomer())
				&& StringUtils.isEmpty(getContactName())) {
			addMessage(FacesMessage.SEVERITY_WARN, "至少输入一项查询条件");
			return "failed";
		}
		setOrders(getOrderDao().searchOrders(
				new SearchBean(getGroupName(), getCustomer(), getContactName(),
						null, null, null, false)));
		return "success";
	}

	public String search() {
		String[] orderStatus = null;
		String[] deliveryStatus = null;
		String[] status = getSupportDataBean()
				.translateOrderStatus(getStatus());

		if (status != null && status[0].equals(OrderStatus.CANCELLED.name())) {
			orderStatus = status;
			deliveryStatus = null;
		} else if (null != status) {
			orderStatus = new String[] { OrderStatus.CONFIRMED.name() };
			deliveryStatus = status;
		}
		try {
			setOrders(getOrderDao().searchOrders(
					new SearchBean(getGroupName(), getCustomer(),
							getContactName(), orderStatus, deliveryStatus,
							getRefNumber(), isFindProblematic())));
			return "success";
		} catch (IllegalArgumentException e) {
			addMessage(FacesMessage.SEVERITY_ERROR, e.getMessage());
			return "failed";
		}
	}

	public String view() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Order select = (Order) dataTable.getRowData();
		ViewOrderBean viewOrderBean = findBean("viewOrderBean");
		viewOrderBean.setOrderId(select.getOid());
		reset();
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public SupportDataBean getSupportDataBean() {
		if (null == supportDataBean)
			supportDataBean = findBean("supportDataBean");
		return supportDataBean;
	}

	public void setSupportDataBean(SupportDataBean supportDataBean) {
		this.supportDataBean = supportDataBean;
	}

	public String getRefNumber() {
		return refNumber;
	}

	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}

	public boolean isFindProblematic() {
		return findProblematic;
	}

	public void setFindProblematic(boolean findProblematic) {
		this.findProblematic = findProblematic;
	}

}
