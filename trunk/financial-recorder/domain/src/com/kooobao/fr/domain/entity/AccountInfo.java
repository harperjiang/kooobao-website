package com.kooobao.fr.domain.entity;

import javax.persistence.Embeddable;

@Embeddable
public class AccountInfo {

	private String name;
	
	private String account;
	
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
	
	
}
