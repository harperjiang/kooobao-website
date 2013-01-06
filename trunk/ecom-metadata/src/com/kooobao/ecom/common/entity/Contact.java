package com.kooobao.ecom.common.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Contact {

	@Column(name = "contact_name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

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

	public void copy(Contact src) {
		setName(src.getName());
		setAddress(src.getAddress());
		setEmail(src.getEmail());
		setPhone(src.getPhone());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIm() {
		return im;
	}

	public void setIm(String im) {
		this.im = im;
	}
}
