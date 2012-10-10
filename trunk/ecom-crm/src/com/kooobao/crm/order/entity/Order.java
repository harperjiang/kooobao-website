package com.kooobao.crm.order.entity;

import java.math.BigDecimal;

import com.kooobao.crm.customer.entity.Customer;

public class Order {

	private String status;

	private String externalOid;

	private String ownBy;

	private Customer customer;

	private BigDecimal amount;

	private int quality;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExternalOid() {
		return externalOid;
	}

	public void setExternalOid(String externalOid) {
		this.externalOid = externalOid;
	}

	public String getOwnBy() {
		return ownBy;
	}

	public void setOwnBy(String ownBy) {
		this.ownBy = ownBy;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

}
