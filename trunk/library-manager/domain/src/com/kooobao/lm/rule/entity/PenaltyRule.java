package com.kooobao.lm.rule.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.lm.profile.entity.Visitor;

public class PenaltyRule {

	public BigDecimal getPenalty(Visitor visitor, Date dueDate, Date date) {
		// $1 a day, 10% less for each level
		int days = (int) ((date.getTime() - dueDate.getTime()) / 86400000l);
		double discount = Math.pow(0.9d, visitor.getLevel());
		return new BigDecimal(days).multiply(new BigDecimal(discount))
				.setScale(2, BigDecimal.ROUND_HALF_UP);
	}

}
