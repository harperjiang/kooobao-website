package com.kooobao.crm.setting.entity;

public class CustomerSetting {

	private int hintLimit;

	private int customerLimit;

	/**
	 * Times of exchange operation
	 */
	private int exchangeTimes;

	private int hintRetainTime;

	private int customerRetainTime;

	public int getHintLimit() {
		return hintLimit;
	}

	public void setHintLimit(int hintLimit) {
		this.hintLimit = hintLimit;
	}

	public int getCustomerLimit() {
		return customerLimit;
	}

	public void setCustomerLimit(int customerLimit) {
		this.customerLimit = customerLimit;
	}

	public int getExchangeTimes() {
		return exchangeTimes;
	}

	public void setExchangeTimes(int exchangeTimes) {
		this.exchangeTimes = exchangeTimes;
	}

	public int getHintRetainTime() {
		return hintRetainTime;
	}

	public void setHintRetainTime(int hintRetainTime) {
		this.hintRetainTime = hintRetainTime;
	}

	public int getCustomerRetainTime() {
		return customerRetainTime;
	}

	public void setCustomerRetainTime(int customerRetainTime) {
		this.customerRetainTime = customerRetainTime;
	}

}
