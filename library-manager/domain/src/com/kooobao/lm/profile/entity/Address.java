package com.kooobao.lm.profile.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {

	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

	@Column(name = "phone")
	private String phone;

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name).append(" ").append(phone).append(" ").append(location);
		return sb.toString();
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
