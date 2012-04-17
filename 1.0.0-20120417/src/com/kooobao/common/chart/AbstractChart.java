package com.kooobao.common.chart;

import java.awt.Dimension;

public abstract class AbstractChart implements Chart {

	private DataSet dataSet;

	private Dimension size;

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet dataSet) {
		this.dataSet = dataSet;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

}
