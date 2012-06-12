package com.kooobao.common.web.tag;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;

import org.apache.commons.lang.Validate;

public class UIProgress extends UIComponentBase {

	public static String FAMILY = Constants.UI_HEADER + "Progress";

	@Override
	public String getFamily() {
		return FAMILY;
	}

	private int value = -1;

	public int getValue() {
		if (-1 != value)
			return value;
		ValueExpression ve = getValueExpression("value");
		if (ve != null) {
			int result = ((Integer) ve.getValue(getFacesContext()
					.getELContext())).intValue();
			if (result > 100)
				result = 100;
			if (result < 0)
				result = 0;
			value = result;
		}
		return value;
	}

	public void setValue(int value) {
		Validate.isTrue(value <= 100 && value >= 0);
		this.value = value;
	}

	private int width;

	private int height;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	private String backgroundColor;

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	private String styleClass;

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

}
