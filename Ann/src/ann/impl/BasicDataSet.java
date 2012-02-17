package ann.impl;

import java.math.BigDecimal;

import ann.DataSet;

public class BasicDataSet implements DataSet {

	private BigDecimal[] data;

	public BasicDataSet(BigDecimal[] data) {
		this.data = data;
	}

	@Override
	public BigDecimal[] getData() {
		return data;
	}

}
