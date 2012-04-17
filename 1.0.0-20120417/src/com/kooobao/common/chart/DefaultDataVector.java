package com.kooobao.common.chart;

import java.math.BigDecimal;

public class DefaultDataVector implements DataVector {

	private BigDecimal[] value;

	public DefaultDataVector(BigDecimal... values) {
		this.value = values;
	}

	public BigDecimal[] getValue() {
		return value;
	}

	public void setValue(BigDecimal[] value) {
		this.value = value;
	}

}
