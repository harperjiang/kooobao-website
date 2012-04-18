package com.kooobao.cws.web.common;

import com.kooobao.common.web.bean.AbstractBean;

public class ContactInfoBean extends AbstractBean {

	private String address;
	
	private String contactPerson;
	
	private String phone;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
