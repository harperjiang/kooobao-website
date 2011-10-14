package com.kooobao.gsm.web.delivery;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIData;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

@ManagedBean
@SessionScoped
public class SearchDeliveryBean extends AbstractBean {

	private String groupName;

	private String customer;

	private String contactName;

	private List<Delivery> deliveries;

	@ManagedProperty("#{deliveryDao}")
	private DeliveryDao deliveryDao;

	public String search() {
		if (StringUtils.isEmpty(customer) && StringUtils.isEmpty(contactName)) {
			addMessage(FacesMessage.SEVERITY_WARN, "至少输入一项查询条件");
			return "failed";
		}
		setDeliveries(getDeliveryDao().search(groupName, customer, contactName));
		return "success";
	}

	public String send() {
		UIData dataTable = (UIData) getComponent("dataTable");
		Delivery select = (Delivery) dataTable.getRowData();
		SendDeliveryBean sdb = findBean("sendDeliveryBean");
		sdb.setDelivery(select);
		return "success";
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public List<Delivery> getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public DeliveryDao getDeliveryDao() {
		return deliveryDao;
	}

	public void setDeliveryDao(DeliveryDao deliveryDao) {
		this.deliveryDao = deliveryDao;
	}

}
