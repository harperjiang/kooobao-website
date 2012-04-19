package com.kooobao.common.chart.d2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.Validate;

import com.kooobao.common.chart.AbstractChart;
import com.kooobao.common.chart.DataSet;
import com.kooobao.common.chart.DataVector;

public abstract class XYCoordinateChart extends AbstractChart {

	private int padding = 80;

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	/*
	 * Draw Coordinates
	 */
	public void generate(Graphics2D canvas) {
		Validate.isTrue(isDataSet(XYDataSet.class));
		XYDataSet xyds = (XYDataSet) getDataSet()[0];
		Dimension size = getSize();

		// Save State
		Stroke oldStroke = canvas.getStroke();
		Color oldColor = canvas.getColor();

		// Paint Background;
		Color bgColor = Color.WHITE;
		canvas.setColor(bgColor);
		canvas.fillRect(0, 0, (int) size.getWidth(), (int) size.getHeight());

		canvas.setColor(new Color(233, 233, 233));
		canvas.fillRect(padding / 2, padding / 2, size.width - padding,
				size.height - padding);

		canvas.setColor(Color.DARK_GRAY);
		canvas.setStroke(new BasicStroke(3f));

		// Draw Plot X,Y
		DataRange range = getDataRange();
		int padding = getPadding();
		// Plot X
		canvas.drawLine(padding, size.height - padding, size.width - padding,
				size.height - padding);
		// Caption
		canvas.drawString(xyds.getCoordinates()[0], size.width / 2, size.height
				- (padding) / 2 + 10);
		// Draw Coordinates X
		int coorLen = 10;
		int stepSize = (size.width - 2 * padding) / range.getStepX().intValue();
		int stepCount = range.getStepX().intValue();
		BigDecimal dataStep = range.getStepLenX();
		for (int i = 1; i <= stepCount; i++) {
			canvas.drawLine(padding + stepSize * i, size.height - padding,
					padding + stepSize * i, size.height - padding - coorLen);
			BigDecimal data = dataStep.multiply(new BigDecimal(i)).setScale(0,
					BigDecimal.ROUND_HALF_UP);
			canvas.rotate(Math.PI / 2);
			canvas.drawString(data.toString(), size.height - padding + 5,
					-padding - stepSize * i);
			canvas.rotate(-Math.PI / 2);
		}
		// Plot Y
		canvas.drawLine(padding, padding, padding, size.height - padding);
		// Caption
		// Rotate Clockwise
		canvas.rotate(-Math.PI / 2, size.width / 2, size.height / 2);
		canvas.drawString(xyds.getCoordinates()[1], size.height / 2,
				padding / 2);
		// Rotate back
		canvas.rotate(Math.PI / 2, size.width / 2, size.height / 2);
		// Draw Coordinates Y
		stepSize = (size.height - 2 * padding) / range.getStepY().intValue();
		stepCount = range.getStepY().intValue();
		dataStep = range.getStepLenY();
		for (int i = 1; i <= stepCount; i++) {
			BigDecimal data = dataStep.multiply(new BigDecimal(i)).setScale(0,
					BigDecimal.ROUND_HALF_UP);
			canvas.drawString(data.toString(), padding - 20, size.height
					- padding - i * stepSize);
			canvas.drawLine(padding, size.height - padding - i * stepSize,
					padding + coorLen, size.height - padding - i * stepSize);
		}
		draw(canvas);

		// Restore State
		canvas.setStroke(oldStroke);
		canvas.setColor(oldColor);
		return;
	}

	public abstract void draw(Graphics2D canvas);

	protected void reset() {
		dataRange = null;
		transform = null;
	}

	private DataRange dataRange;

	protected DataRange getDataRange() {
		if (dataRange != null)
			return dataRange;
		// Calculate Amplify Ratio
		Map<Object, Object> distinctMap = new HashMap<Object, Object>();
		Class<?> keyClass = null;
		BigDecimal maxX = null;
		BigDecimal maxY = null;
		BigDecimal minX = null;
		BigDecimal minY = null;
		for (DataSet ds : getDataSet()) {
			XYDataSet xyds = (XYDataSet) ds;

			for (DataVector dv : xyds.getData()) {
				if (dv.getValue()[0] instanceof BigDecimal) {
					if (null != keyClass && keyClass != BigDecimal.class) {
						throw new IllegalArgumentException();
					}
					keyClass = BigDecimal.class;
					maxX = (maxX != null && maxX.compareTo((BigDecimal) dv
							.getValue()[0]) >= 0) ? maxX : (BigDecimal) dv
							.getValue()[0];
					minX = (minX != null && minX.compareTo((BigDecimal) dv
							.getValue()[0]) <= 0) ? minX : (BigDecimal) dv
							.getValue()[0];
				} else {
					if (null != keyClass
							&& keyClass != dv.getValue()[0].getClass()) {
						throw new IllegalArgumentException();
					}
					keyClass = dv.getValue()[0].getClass();
					distinctMap.put(dv.getValue()[0], dv.getValue()[0]);
				}
				maxY = (maxY != null && maxY.compareTo((BigDecimal) dv
						.getValue()[1]) >= 0) ? maxY : (BigDecimal) dv
						.getValue()[1];

				minY = (minY != null && minY.compareTo((BigDecimal) dv
						.getValue()[1]) <= 0) ? minY : (BigDecimal) dv
						.getValue()[1];
			}
		}

		if (keyClass != BigDecimal.class) {
			minX = BigDecimal.ZERO;
			maxX = new BigDecimal(distinctMap.size());
		}

		BigDecimal[] fcx = box(minX, maxX);
		BigDecimal[] fcy = box(minY, maxY);
		dataRange = new DataRange(minX, minY, maxX, maxY, fcx[0], fcx[1],
				fcx[2], fcy[0], fcy[1], fcy[2]);
		return dataRange;
	}

	private Map<Object, Integer> indexMap;

	private static class ComparableKey implements Comparable<ComparableKey> {

		private Object key;

		public ComparableKey(Object key) {
			this.key = key;
		}

		public int compareTo(ComparableKey o) {
			if (o.key == null || key == null)
				throw new IllegalArgumentException();
			if (o.key.getClass() != key.getClass())
				throw new IllegalArgumentException();
			if (key.getClass() == BigDecimal.class) {
				return ((BigDecimal) key).compareTo((BigDecimal) o.key);
			}
			if (key.getClass() == Date.class) {
				return ((Date) key).compareTo((Date) o.key);
			}
			if (key.getClass() == String.class) {
				return ((String) key).compareTo((String) o.key);
			}
			throw new IllegalArgumentException();
		}

	}

	public int getIndex(Object key) {
		if (null == indexMap) {
			indexMap = new HashMap<Object, Integer>();
			List<ComparableKey> data = new ArrayList<ComparableKey>();
			for (DataSet ds : getDataSet()) {
				for (DataVector dv : ds.getData()) {
					if (!indexMap.containsKey(dv.getValue()[0])) {
						indexMap.put(dv.getValue()[0], 0);
						data.add(new ComparableKey(dv.getValue()[0]));
					}
				}
			}
			// Sort
			Collections.sort(data);
		}
		return indexMap.get(key);
	}

	private BigDecimal[] box(BigDecimal min, BigDecimal max) {
		BigDecimal ceiling = max;
		BigDecimal floor = BigDecimal.ZERO;

		ceiling = new BigDecimal(Math.pow(10,
				Math.ceil(Math.log10(ceiling.doubleValue()))));
		BigDecimal step = ceiling.subtract(floor).divide(BigDecimal.TEN);
		return new BigDecimal[] { floor, ceiling, step, BigDecimal.TEN };
	}

	private AffineTransform transform;

	protected AffineTransform getTransform() {
		if (null != transform)
			return transform;
		// Calculate Amplify Ratio
		DataRange range = getDataRange();

		BigDecimal width = range.getCeilingX().subtract(range.getFloorX());
		BigDecimal height = range.getCeilingY().subtract(range.getFloorY());

		BigDecimal factor = new BigDecimal(1f);
		BigDecimal valueX = width.divide(factor, 5, BigDecimal.ROUND_HALF_UP);
		BigDecimal valueY = height.divide(factor, 5, BigDecimal.ROUND_HALF_UP);

		int remainX = (int) (getSize().getWidth() - 2 * getPadding());
		int remainY = (int) (getSize().getHeight() - 2 * getPadding());
		BigDecimal ratioX = valueX.divide(new BigDecimal(remainX), 2,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal ratioY = valueY.divide(new BigDecimal(remainY), 2,
				BigDecimal.ROUND_HALF_UP);

		transform = new AffineTransform(1 / ratioX.doubleValue(), 0, 0, -1
				/ ratioY.doubleValue(), getPadding(), getSize().getHeight()
				- getPadding());
		return transform;
	}
}
