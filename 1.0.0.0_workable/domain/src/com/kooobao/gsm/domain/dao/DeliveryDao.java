package com.kooobao.gsm.domain.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.domain.dao.ISearchBean;
import com.kooobao.gsm.domain.entity.delivery.Delivery;

public interface DeliveryDao extends Dao<Delivery> {

	public static class SearchBean implements ISearchBean {

		private String groupName;

		private String status;

		private String refNumber;

		private String customer;

		private String contactName;

		public SearchBean(String groupName, String status, String refNumber,
				String customer, String contactName) {
			this.groupName = groupName;
			this.status = status;
			this.refNumber = refNumber;
			this.customer = customer;
			this.contactName = contactName;
		}

		public String getGroupName() {
			return groupName;
		}

		public String getStatus() {
			return status;
		}

		public String getCustomer() {
			return customer;
		}

		public String getContactName() {
			return contactName;
		}

		public String getRefNumber() {
			return refNumber;
		}

		public void setRefNumber(String refNumber) {
			this.refNumber = refNumber;
		}

		public void validate() {
			Validate.isTrue(!StringUtils.isEmpty(groupName));
			Validate.isTrue(!StringUtils.isEmpty(customer)
					|| !StringUtils.isEmpty(contactName)
					|| !StringUtils.isEmpty(refNumber));
		}
	}

	public List<Delivery> search(SearchBean search);
}
