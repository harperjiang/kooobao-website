package com.kooobao.gsm.domain.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

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

		public SearchBean(String groupName, String customer, String contact,
				String[] status, String[] deliveryStatus) {
			this.groupName = groupName;
			this.customer = customer;
			this.contact = contact;
			this.status = status;
			this.deliveryStatus = deliveryStatus;
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

		public void validate() {
			Validate.isTrue(!StringUtils.isEmpty(getCustomer())
					|| !StringUtils.isEmpty(getContact())
					|| (status != null && status.length > 1)
					|| (deliveryStatus != null && deliveryStatus.length > 1));
		}
	}

	public List<Order> searchOrders(SearchBean search);
}
