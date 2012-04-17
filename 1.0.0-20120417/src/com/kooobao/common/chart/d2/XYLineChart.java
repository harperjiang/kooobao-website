package com.kooobao.common.chart.d2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import com.kooobao.common.chart.DataVector;

public class XYLineChart extends XYCoordinateChart {

	private static Color[] POINT_COLOR = new Color[] { Color.RED, Color.ORANGE,
			Color.YELLOW, Color.GREEN, Color.BLUE };

	private static Color[] LINE_COLOR = new Color[] { Color.BLUE, Color.BLUE,
			Color.BLUE, Color.BLUE, Color.BLUE };

	@Override
	public void draw(Graphics2D canvas) {
		XYDataSet xyds = (XYDataSet) getDataSet();

		AffineTransform trans = getTransform();

		int pointRadius = 4;
		canvas.setColor(Color.BLACK);

		canvas.setStroke(new BasicStroke(2f));
		Point2D[] previous = null;
		for (DataVector dv : xyds.getData()) {
			if (null == previous)
				previous = new Point2D[dv.getValue().length - 1];
			int x = dv.getValue()[0].intValue();
			for (int index = 1; index < dv.getValue().length; index++) {
				int y = dv.getValue()[index].intValue();
				Point2D tp = trans.transform(new Point2D.Double(x, y),
						new Point2D.Double(0, 0));
				if (null != previous[index - 1]) {
					canvas.setColor(LINE_COLOR[(index - 1) % LINE_COLOR.length]);
					canvas.drawLine((int) previous[index - 1].getX(),
							(int) previous[index - 1].getY(), (int) tp.getX(),
							(int) tp.getY());
				}
				canvas.setColor(POINT_COLOR[(index - 1) % POINT_COLOR.length]);
				canvas.fillOval((int) tp.getX() - pointRadius, (int) tp.getY()
						- pointRadius, 2 * pointRadius, 2 * pointRadius);
				previous[index - 1] = tp;
			}
		}
	}
}
