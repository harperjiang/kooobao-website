package com.kooobao.crm.customer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Convert;

import com.kooobao.common.domain.entity.SimpleEntity;

@Entity
@Table(name = "crm_customer_followup")
public class CustomerFollowup extends SimpleEntity {

	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "obj_id")
	Customer customer;

	@Column(name = "comment")
	String comment;

	@Column(name = "method")
	@Convert("enumConverter")
	ContactMethod method;

	@Column(name = "reference")
	String reference;

	@Column(name = "own_by")
	String ownBy;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ContactMethod getMethod() {
		return method;
	}

	public void setMethod(ContactMethod method) {
		this.method = method;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(String ownBy) {
		this.ownBy = ownBy;
	}

}
