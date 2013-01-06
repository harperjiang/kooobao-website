package com.kooobao.ecom.crm.customer.entity;

import java.text.MessageFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {

	@Column(name = "contact_name")
	private String name;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "im")
	private String im;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Contact) {
			Contact compare = (Contact) obj;
			return getName().equals(compare.getName())
					&& getPhone().equals(compare.getPhone())
					&& getEmail().equals(compare.getEmail())
					&& getAddress().equals(compare.getAddress())
					&& getIm().equals(compare.getIm());
		}
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return MessageFormat.format(
				"Contact Name: {0}, Phone: {1}, Email: {2}, IM: {3}, Address: {4}",
				getName(), getPhone(), getEmail(), getIm(), getAddress());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
