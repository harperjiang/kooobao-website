package com.kooobao.gsm.domain.entity.rule;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public abstract class DeliveryAmountRule {

	private String deliveryMethod;

	private int priority;

	private String area;

	private BigDecimal weightFloor;

	private BigDecimal weightCeiling;

	private BigDecimal sumFloor;

	private BigDecimal sumCeiling;

	public boolean match(DeliveryTarget target) {
		Validate.notNull(target.getAmount());
		Validate.notNull(target.getWeight());

		if ((StringUtils.isEmpty(area) || target.getAddress().contains(area))
				&& (weightFloor.compareTo(target.getWeight()) < 0)
				&& (BigDecimal.ZERO.equals(weightCeiling) || weightCeiling
						.compareTo(target.getWeight()) >= 0)
				&& (sumFloor.compareTo(target.getAmount()) < 0)
				&& (BigDecimal.ZERO.equals(sumCeiling) || sumCeiling
						.compareTo(target.getAmount()) >= 0)) {
			return true;
		}
		return false;
	}

	public abstract BigDecimal calculate(DeliveryTarget target);

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public void setDeliveryMethod(String deliveryMethod) {
		this.deliveryMethod = deliveryMethod;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public BigDecimal getWeightFloor() {
		return weightFloor;
	}

	public void setWeightFloor(BigDecimal weightFloor) {
		this.weightFloor = weightFloor;
	}

	public BigDecimal getWeightCeiling() {
		return weightCeiling;
	}

	public void setWeightCeiling(BigDecimal weightCeiling) {
		this.weightCeiling = weightCeiling;
	}

	public BigDecimal getSumFloor() {
		return sumFloor;
	}

	public void setSumFloor(BigDecimal sumFloor) {
		this.sumFloor = sumFloor;
	}

	public BigDecimal getSumCeiling() {
		return sumCeiling;
	}

	public void setSumCeiling(BigDecimal sumCeiling) {
		this.sumCeiling = sumCeiling;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
