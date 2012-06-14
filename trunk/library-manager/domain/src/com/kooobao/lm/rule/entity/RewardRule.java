package com.kooobao.lm.rule.entity;

import java.math.BigDecimal;

public class RewardRule {

	static BigDecimal TWENTY = new BigDecimal("20");

	public BigDecimal getRegisterReward() {
		return TWENTY;
	}

	public BigDecimal getRecommendReward() {
		return TWENTY;
	}
	
	public BigDecimal getCommentReward() {
		return new BigDecimal("5");
	}
}
