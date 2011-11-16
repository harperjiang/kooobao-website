package com.kooobao.gsm.domain.dao;

import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;

public interface SupportDao {

	public DeliveryAmountRule getAmountRule(DeliveryTarget target);

	public GrossWeightRule getWeightRule(DeliveryTarget target);
}
