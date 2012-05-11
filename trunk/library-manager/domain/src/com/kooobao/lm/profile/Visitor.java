package com.kooobao.lm.profile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Visitor {

	private String id;

	private String name;

	private String status;

	private String level;

	private BigDecimal deposit;

	private Address address;

	private List<Address> availableAddresses = new ArrayList<Address>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAvailableAddresses() {
		return availableAddresses;
	}

	public void setAvailableAddresses(List<Address> availableAddresses) {
		this.availableAddresses = availableAddresses;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public static enum Status {
		VALID, INVALID, LACK_BALANCE
	}
}