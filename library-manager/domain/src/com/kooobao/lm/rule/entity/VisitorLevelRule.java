package com.kooobao.lm.rule.entity;

import java.math.BigDecimal;

public class VisitorLevelRule {

	static BigDecimal[] LEVELS = new BigDecimal[] { new BigDecimal("100"),
			new BigDecimal("200"), new BigDecimal("500"),
			new BigDecimal("1000"), new BigDecimal("2000"),
			new BigDecimal("5000"), };

	public int getLevel(BigDecimal resultAmount) {
		int count = 0;
		while (count < LEVELS.length
				&& resultAmount.compareTo(LEVELS[count]) >= 0) {
			count++;
		}
		return count;
	}

}
