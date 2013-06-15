package com.kooobao.ecom.customer.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kooobao.common.domain.entity.VersionEntity;
import com.kooobao.ecom.common.entity.Contact;

@Entity
@Table(name = "meta_customer")
public class Customer extends VersionEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "source")
	private String source;

	@Column(name = "id")
	private String id;

	@Embedded
	private Contact contact;

	@Column(name = "desc")
	private String description;

	public Customer() {
		this.contact = new Contact();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Contact getContact() {
		return contact;
	}
}
