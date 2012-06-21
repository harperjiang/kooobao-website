package com.kooobao.lm.rule;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.lm.profile.entity.Address;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.rule.dao.RuleDao;

public class DefaultRuleService implements RuleService {

	public Date getExpirePeriod(Visitor visitor) {
		return getRuleDao().getDueRule().getDueDate(visitor, null, new Date());
	}

	public BigDecimal getPenalty(Visitor visitor) {
		return getRuleDao().getPenaltyRule().getPenalty(visitor, new Date(),
				new Date(System.currentTimeMillis() + 86410000));
	}

	private RuleDao ruleDao;

	public RuleDao getRuleDao() {
		return ruleDao;
	}

	public void setRuleDao(RuleDao ruleDao) {
		this.ruleDao = ruleDao;
	}

	public BigDecimal getDeliveryFee(Address address, BigDecimal netWeight) {
		if (null == address) {
			return BigDecimal.ZERO;
		}
		return getRuleDao().getDeliveryFeeRule().getDeliveryFee(address,
				netWeight);
	}

	public BigDecimal getDiscount(Visitor visitor, BigDecimal totalPrice,
			BigDecimal deliveryFee) {
		return getRuleDao().getDiscountRule().getDiscount(visitor, totalPrice,
				deliveryFee);
	}

	public BigDecimal getVisitorDiscount(Visitor visitor) {
		return getRuleDao().getDiscountRule().getDiscount(visitor);
	}
}
