package com.kooobao.wechat.msg;

import java.awt.geom.Point2D;

public class LocationIncoming extends IncomingMessage {

	private Point2D.Double location;
	private int scale;
	private String label;

	public Point2D.Double getLocation() {
		return location;
	}

	public void setLocation(Point2D.Double location) {
		this.location = location;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
