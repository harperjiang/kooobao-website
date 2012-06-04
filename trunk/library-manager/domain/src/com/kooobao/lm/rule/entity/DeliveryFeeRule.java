package com.kooobao.lm.rule.entity;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.Address;

public class DeliveryFeeRule {

	public BigDecimal getDeliveryFee(Address address, BigDecimal netWeight) {
		return BigDecimal.TEN.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}
