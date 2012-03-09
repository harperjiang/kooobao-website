package com.kooobao.common.chart;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface Chart {

	public Dimension getSize();

	public DataSet getDataSet();

	public void generate(Graphics2D canvas);
}
