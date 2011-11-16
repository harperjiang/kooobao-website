package com.kooobao.gsm.domain.entity.rule.da;

import java.math.BigDecimal;

import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;

public class FixedAmountRule extends DeliveryAmountRule {

	private BigDecimal price;

	@Override
	public BigDecimal calculate(DeliveryTarget target) {
		return getPrice();
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
