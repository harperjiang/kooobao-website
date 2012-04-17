package com.kooobao.common.chart.d2;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import com.kooobao.common.chart.DataVector;

public class HistogramChart extends XYCoordinateChart {

	Color[] BLOCK_COLOR = new Color[] { Color.RED, Color.GREEN, Color.BLUE };

	@Override
	public void draw(Graphics2D canvas) {
		XYDataSet ds = (XYDataSet) getDataSet();

		DataRange range = getDataRange();

		int typeCount = ds.getData().get(0).getValue().length - 1;
		int buffer = 10;
		// Determine the width of histogram;
		int typeWidth = (getSize().width - 2 * getPadding())
				/ range.getStepX().intValue();
		int blockWidth = (typeWidth - 2 * buffer) / typeCount;

		AffineTransform transform = getTransform();

		for (DataVector dv : ds.getData()) {
			double x = dv.getValue()[0].doubleValue();
			for (int index = 1; index < dv.getValue().length; index++) {
				double y = dv.getValue()[index].doubleValue();
				Point2D transed = transform.transform(new Point2D.Double(x, y),
						new Point2D.Double());
				canvas.setColor(BLOCK_COLOR[(index - 1) % BLOCK_COLOR.length]);
				int blockStart = (int) transed.getX() - typeWidth / 2 + buffer
						+ blockWidth * (index - 1);
				canvas.fillRect(blockStart, (int) transed.getY(), blockWidth,
						getSize().height - getPadding() - (int) transed.getY());
			}
		}
	}
}
