package com.kooobao.gsm.domain.entity.rule;

import java.math.BigDecimal;

public interface DeliveryTarget {

	public String getDeliveryMethod();
	
	public String getAddress();
	
	public BigDecimal getAmount();
	
	public BigDecimal getWeight();
}
