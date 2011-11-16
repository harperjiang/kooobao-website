package com.kooobao.gsm.domain.dao;

import java.util.List;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.domain.dao.ISearchBean;
import com.kooobao.gsm.domain.entity.order.Order;

public interface OrderDao extends Dao<Order> {
	public static class SearchBean implements ISearchBean {
		private String groupName;
		private String customer;
		private String contact;
		private String[] status;
		private String[] deliveryStatus;
		private String refNumber;
		private boolean findProblematic;

		public SearchBean(String groupName, String customer, String contact,
				String[] status, String[] deliveryStatus, String refNumber,
				boolean findProblematic) {
			this.groupName = groupName;
			this.customer = customer;
			this.contact = contact;
			this.status = status;
			this.deliveryStatus = deliveryStatus;
			this.refNumber = refNumber;
			this.findProblematic = findProblematic;
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

		public String getContact() {
			return contact;
		}

		public void setContact(String contact) {
			this.contact = contact;
		}

		public String[] getStatus() {
			return status;
		}

		public void setStatus(String[] status) {
			this.status = status;
		}

		public String[] getDeliveryStatus() {
			return deliveryStatus;
		}

		public void setDeliveryStatus(String[] deliveryStatus) {
			this.deliveryStatus = deliveryStatus;
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

		public void validate() {
			// Validate.isTrue(
			// !StringUtils.isEmpty(getCustomer())
			// || !StringUtils.isEmpty(getContact())
			// || !StringUtils.isEmpty(getRefNumber())
			// || (status != null && status.length > 0)
			// || (deliveryStatus != null && deliveryStatus.length > 0),
			// "至少输入一项查询条件");
		}
	}

	public List<Order> searchOrders(SearchBean search);
	
	public Order rollback(Order order);
}
