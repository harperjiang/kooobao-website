package com.kooobao.gsm.domain.entity.rule;

import java.math.BigDecimal;

public interface GrossWeightRule {

	public BigDecimal getPackageWeight(DeliveryTarget target);
}
