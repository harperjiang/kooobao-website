package com.kooobao.gsm.domain.entity.rule.da;

import java.math.BigDecimal;

import org.apache.commons.lang.Validate;

import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;

public class ExpressRule extends DeliveryAmountRule {

	@Override
	public BigDecimal calculate(DeliveryTarget target) {
		Validate.notNull(start);
		Validate.notNull(next);
		
		BigDecimal startWeight = BigDecimal.ONE;
		
		BigDecimal weight = target.getWeight();
		if (null == weight || weight.compareTo(BigDecimal.ZERO) <= 0)
			return null;

		BigDecimal nextWeight = new BigDecimal(Math.ceil(weight.doubleValue()))
				.subtract(startWeight);
		return getStart().multiply(startWeight).add(nextWeight.multiply(getNext()));
	}

	private BigDecimal start;

	private BigDecimal next;

	public BigDecimal getStart() {
		return start;
	}

	public void setStart(BigDecimal start) {
		this.start = start;
	}

	public BigDecimal getNext() {
		return next;
	}

	public void setNext(BigDecimal next) {
		this.next = next;
	}

}
