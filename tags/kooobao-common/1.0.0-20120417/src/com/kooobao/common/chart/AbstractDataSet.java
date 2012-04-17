package com.kooobao.common.chart;

import java.util.List;

public abstract class AbstractDataSet implements DataSet {

	private String[] coordinates;

	private List<DataVector> data;

	public String[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String[] coordinates) {
		this.coordinates = coordinates;
	}

	public List<DataVector> getData() {
		return data;
	}

	public void setData(List<DataVector> data) {
		this.data = data;
	}

}
