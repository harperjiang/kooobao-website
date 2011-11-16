package com.kooobao.gsm.domain.entity.rule;

import java.math.BigDecimal;

public class DefaultGrossWeightRule implements GrossWeightRule {

	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	public DefaultGrossWeightRule(BigDecimal amount) {
		setAmount(amount);
	}

	public BigDecimal getPackageWeight(DeliveryTarget target) {
		return amount;
	}

}
