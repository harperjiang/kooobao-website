package com.kooobao.common.chart;

import java.awt.Dimension;

import com.kooobao.common.chart.d2.XYDataSet;

public abstract class AbstractChart implements Chart {

	private DataSet[] dataSet;

	private Dimension size;

	public DataSet[] getDataSet() {
		return dataSet;
	}

	public void setDataSet(DataSet[] dataSet) {
		this.dataSet = dataSet;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	protected boolean isDataSet(Class<XYDataSet> class1) {
		for(DataSet ds : getDataSet()) {
			if(!class1.isInstance(ds))
				return false;
		}
		return true;
	}
}
