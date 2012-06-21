package com.kooobao.lm.rule.entity;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.Visitor;

public class DiscountRule {

	public BigDecimal getDiscount(Visitor visitor) {
		return BigDecimal.ONE;
	}

	public BigDecimal getDiscount(Visitor visitor, BigDecimal totalPrice,
			BigDecimal deliveryFee) {
		return totalPrice.multiply(BigDecimal.ONE
				.subtract(getDiscount(visitor)));
	}

}
