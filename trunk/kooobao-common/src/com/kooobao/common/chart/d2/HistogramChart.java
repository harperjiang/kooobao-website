package com.kooobao.common.chart.d2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

import com.kooobao.common.chart.DataSet;
import com.kooobao.common.chart.DataVector;

public class HistogramChart extends XYCoordinateChart {

	Color[] BLOCK_COLOR = new Color[] { Color.RED, Color.GREEN, Color.BLUE };

	@Override
	public void draw(Graphics2D canvas) {

		DataRange range = getDataRange();
		int buffer = 10;

		int typeCount = getDataSet().length;

		// Determine the width of histogram;
		int typeWidth = (getSize().width - 2 * getPadding())
				/ range.getStepX().intValue();
		int blockWidth = (typeWidth - 2 * buffer) / typeCount;

		AffineTransform transform = getTransform();

		int counter = 0;
		for (DataSet d : getDataSet()) {
			XYDataSet ds = (XYDataSet) d;
			for (DataVector dv : ds.getData()) {
				double x;
				if (dv.getValue()[0] instanceof BigDecimal) {
					x = ((BigDecimal) dv.getValue()[0]).doubleValue();
				} else {
					x = getIndex(dv.getValue()[0]);
				}
				double y = ((BigDecimal) dv.getValue()[1]).doubleValue();
				Point2D transed = transform.transform(new Point2D.Double(x, y),
						new Point2D.Double());
				canvas.setColor(BLOCK_COLOR[counter % BLOCK_COLOR.length]);
				int blockStart = (int) transed.getX() - typeWidth / 2 + buffer
						+ blockWidth * (counter);
				canvas.fillRect(blockStart, (int) transed.getY(), blockWidth,
						getSize().height - getPadding() - (int) transed.getY());

			}
			counter++;
		}
	}
}
