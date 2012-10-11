package com.kooobao.ecom.order.product.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.kooobao.common.domain.entity.SimpleEntity;

@Embeddable
public class ContactInfo {

	@Column(name = "contact_name")
	private String name;

	@Column(name = "contact_email")
	private String email;

	@Column(name = "contact_address")
	private String address;

	@Column(name = "contact_phone")
	private String phone;

	@Override
	public boolean equals(Object comp) {
		if (comp instanceof ContactInfo) {
			ContactInfo info = (ContactInfo) comp;
			return SimpleEntity.equals(name, info.getName())
					&& SimpleEntity.equals(email, info.getEmail())
					&& SimpleEntity.equals(address, info.getAddress())
					&& SimpleEntity.equals(phone, info.getPhone());
		}
		return super.equals(comp);
	}

	public void copy(ContactInfo src) {
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
}
