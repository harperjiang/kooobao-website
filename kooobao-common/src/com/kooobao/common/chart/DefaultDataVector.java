package com.kooobao.common.chart;


public class DefaultDataVector implements DataVector {

	private Object[] value;
	
	public DefaultDataVector(Object... val) {
		this.value = val;
	}
	
	public Object[] getValue() {
		return value;
	}
}
