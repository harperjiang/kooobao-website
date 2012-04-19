package com.kooobao.common.chart.d2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

import com.kooobao.common.chart.DataSet;
import com.kooobao.common.chart.DataVector;

public class XYLineChart extends XYCoordinateChart {

	private static Color[] POINT_COLOR = new Color[] { Color.RED, Color.ORANGE,
			Color.YELLOW, Color.GREEN, Color.BLUE };

	private static Color[] LINE_COLOR = new Color[] { Color.BLUE, Color.BLUE,
			Color.BLUE, Color.BLUE, Color.BLUE };

	@Override
	public void draw(Graphics2D canvas) {
		int counter = 0;
		for (DataSet ds : getDataSet()) {

			XYDataSet xyds = (XYDataSet) ds;

			AffineTransform trans = getTransform();

			int pointRadius = 4;
			canvas.setColor(Color.BLACK);

			canvas.setStroke(new BasicStroke(2f));
			Point2D previous = null;
			for (DataVector dv : xyds.getData()) {
				double x;
				if (dv.getValue()[0] instanceof BigDecimal) {
					x = ((BigDecimal) dv.getValue()[0]).doubleValue();
				} else {
					x = getIndex(dv.getValue()[0]);
				}
				double y = ((BigDecimal) dv.getValue()[1]).doubleValue();
				Point2D tp = trans.transform(new Point2D.Double(x, y),
						new Point2D.Double(0, 0));
				if (null != previous) {
					canvas.setColor(LINE_COLOR[(counter) % LINE_COLOR.length]);
					canvas.drawLine((int) previous.getX(),
							(int) previous.getY(), (int) tp.getX(),
							(int) tp.getY());
				}
				canvas.setColor(POINT_COLOR[(counter) % POINT_COLOR.length]);
				canvas.fillOval((int) tp.getX() - pointRadius, (int) tp.getY()
						- pointRadius, 2 * pointRadius, 2 * pointRadius);
				previous = tp;
			}
			counter++;
		}
	}
}
