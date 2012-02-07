package com.kooobao.fr.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AccountInfo {

	@Column(name="name",columnDefinition="varchar(20)")
	private String name;

	@Column(name="account",columnDefinition="varchar(200)")
	private String account;

	@Column(name="company",columnDefinition="varchar(100)")
	private String company;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void fromCustomer(Customer customer) {
		this.setAccount(customer.getAccount());
		this.setCompany(customer.getCompany());
		this.setName(customer.getName());
	}
	
	
}
