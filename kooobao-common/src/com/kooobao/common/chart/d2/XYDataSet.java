package com.kooobao.common.chart.d2;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.common.chart.AbstractDataSet;
import com.kooobao.common.chart.DataVector;
import com.kooobao.common.chart.DefaultDataVector;

public class XYDataSet extends AbstractDataSet {

	static String[] COOR = { "X", "Y" };

	public XYDataSet() {
		super();
		setCoordinates(COOR);
	}

	public void add(BigDecimal x, BigDecimal y) {
		DefaultDataVector dv = new DefaultDataVector(x, y);
		getData().add(dv);
	}

	public void add(Date x, BigDecimal y) {
		DefaultDataVector dv = new DefaultDataVector(x, y);
		getData().add(dv);
	}

	public void add(String x, BigDecimal y) {
		DefaultDataVector dv = new DefaultDataVector(x, y);
		getData().add(dv);
	}

	public DataVector get(Object key) {
		for (DataVector dv : getData()) {
			if (dv.getValue()[0].equals(key))
				return dv;
		}
		return null;
	}
}
