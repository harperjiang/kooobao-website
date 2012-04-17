package com.kooobao.common.chart.d2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.kooobao.common.chart.AbstractDataSet;
import com.kooobao.common.chart.DefaultDataVector;

public class XYDataSet extends AbstractDataSet {

	static String[] COOR = { "X", "Y" };

	public String[] getCoordinates() {
		return COOR;
	}

	public void add(BigDecimal x, BigDecimal... y) {
		DefaultDataVector dv = new DefaultDataVector();
		List<BigDecimal> values = new ArrayList<BigDecimal>();
		values.add(x);
		for (BigDecimal yi : y)
			values.add(yi);
		BigDecimal[] data = new BigDecimal[values.size()];
		values.toArray(data);
		dv.setValue(data);
		getData().add(dv);
	}

}
