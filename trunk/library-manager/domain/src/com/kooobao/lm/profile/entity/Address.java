package com.kooobao.lm.profile.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "lm_visitor_addr")
public class Address extends SimpleEntity {

	@OneToOne
	@JoinColumn(name = "visitor")
	private Visitor visitor;

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

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

}
