package com.kooobao.lm.rule.entity;

import java.math.BigDecimal;

public class RegisterRule {

	static BigDecimal TWENTY = new BigDecimal("20");

	public BigDecimal getRegisterReward() {
		return TWENTY;
	}
}
