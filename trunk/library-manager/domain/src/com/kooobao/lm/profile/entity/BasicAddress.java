package com.kooobao.lm.profile.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class BasicAddress {

	@Column(name = "addr_name")
	private String name;

	@Column(name = "addr_loc")
	private String location;

	@Column(name = "addr_phone")
	private String phone;

	public BasicAddress() {

	}

	public BasicAddress(Address addr) {
		super();
		setName(addr.getName());
		setLocation(addr.getLocation());
		setPhone(addr.getPhone());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
