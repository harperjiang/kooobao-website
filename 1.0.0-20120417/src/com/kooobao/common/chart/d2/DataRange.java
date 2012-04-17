package com.kooobao.common.chart.d2;

import java.math.BigDecimal;

public class DataRange {

	private BigDecimal minX;

	private BigDecimal minY;

	private BigDecimal maxX;

	private BigDecimal maxY;

	private BigDecimal floorX;

	private BigDecimal ceilingX;

	private BigDecimal stepX;

	private BigDecimal floorY;

	private BigDecimal ceilingY;

	private BigDecimal stepY;

	public DataRange(BigDecimal minx, BigDecimal miny, BigDecimal maxx,
			BigDecimal maxy, BigDecimal floorx, BigDecimal ceilingx,
			BigDecimal stepx, BigDecimal floory, BigDecimal ceilingy,
			BigDecimal stepy) {
		this.minX = minx;
		this.minY = miny;
		this.maxX = maxx;
		this.maxY = maxy;
		this.floorX = floorx;
		this.ceilingX = ceilingx;
		this.stepX = stepx;
		this.floorY = floory;
		this.ceilingY = ceilingy;
		this.stepY = stepy;
	}

	public BigDecimal getMinX() {
		return minX;
	}

	public void setMinX(BigDecimal minX) {
		this.minX = minX;
	}

	public BigDecimal getMinY() {
		return minY;
	}

	public void setMinY(BigDecimal minY) {
		this.minY = minY;
	}

	public BigDecimal getMaxX() {
		return maxX;
	}

	public void setMaxX(BigDecimal maxX) {
		this.maxX = maxX;
	}

	public BigDecimal getMaxY() {
		return maxY;
	}

	public void setMaxY(BigDecimal maxY) {
		this.maxY = maxY;
	}

	public BigDecimal getFloorX() {
		return floorX;
	}

	public void setFloorX(BigDecimal floorX) {
		this.floorX = floorX;
	}

	public BigDecimal getCeilingX() {
		return ceilingX;
	}

	public void setCeilingX(BigDecimal ceilingX) {
		this.ceilingX = ceilingX;
	}

	public BigDecimal getStepX() {
		return stepX;
	}

	public void setStepX(BigDecimal stepX) {
		this.stepX = stepX;
	}

	public BigDecimal getFloorY() {
		return floorY;
	}

	public void setFloorY(BigDecimal floorY) {
		this.floorY = floorY;
	}

	public BigDecimal getCeilingY() {
		return ceilingY;
	}

	public void setCeilingY(BigDecimal ceilingY) {
		this.ceilingY = ceilingY;
	}

	public BigDecimal getStepY() {
		return stepY;
	}

	public void setStepY(BigDecimal stepY) {
		this.stepY = stepY;
	}

	public BigDecimal getStepLenX() {
		return getCeilingX().subtract(getFloorX()).divide(getStepX(), 2,
				BigDecimal.ROUND_HALF_UP);
	}

	public BigDecimal getStepLenY() {
		return getCeilingY().subtract(getFloorY()).divide(getStepY(), 2,
				BigDecimal.ROUND_HALF_UP);
	}
}
