package com.kooobao.common.chart.d2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kooobao.common.chart.DataSet;
import com.kooobao.common.chart.DataVector;
import com.kooobao.common.chart.DefaultDataVector;

public class XYCoordinateChartTest {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(1000, 620);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		final XYCoordinateChart chart = new HistogramChart();
		XYDataSet ds = new XYDataSet();
		List<DataVector> dvs = new ArrayList<DataVector>();
		dvs.add(new DefaultDataVector(new BigDecimal(10), new BigDecimal(10),
				new BigDecimal(20)));
		dvs.add(new DefaultDataVector(new BigDecimal(20), new BigDecimal(20),
				new BigDecimal(30)));
		dvs.add(new DefaultDataVector(new BigDecimal(30), new BigDecimal(10),
				new BigDecimal(50)));
		dvs.add(new DefaultDataVector(new BigDecimal(40), new BigDecimal(20),
				new BigDecimal(50)));
		dvs.add(new DefaultDataVector(new BigDecimal(50), new BigDecimal(40),
				new BigDecimal(10)));
		dvs.add(new DefaultDataVector(new BigDecimal(60), new BigDecimal(70),
				new BigDecimal(70)));
		dvs.add(new DefaultDataVector(new BigDecimal(70), new BigDecimal(10),
				new BigDecimal(10)));
		dvs.add(new DefaultDataVector(new BigDecimal(80), new BigDecimal(90),
				new BigDecimal(70)));
		ds.setData(dvs);
		chart.setDataSet(new DataSet[]{ds});
		chart.setSize(new Dimension(980, 580));

		JPanel panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				chart.generate((Graphics2D) g);
			}
		};
		frame.add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
