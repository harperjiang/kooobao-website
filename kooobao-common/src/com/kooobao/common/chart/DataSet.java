package com.kooobao.common.chart;

import java.util.List;

public interface DataSet {

	public String[] getCoordinates();

	public List<DataVector> getData();
	
	public DataVector get(Object key);
}
