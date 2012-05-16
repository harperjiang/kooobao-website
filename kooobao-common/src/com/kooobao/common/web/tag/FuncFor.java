package com.kooobao.common.web.tag;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;

public class FuncFor extends UIComponentBase implements Constants {

	static final String FAMILY = FUNC_HEADER + "for";

	@Override
	public String getFamily() {
		return FAMILY;
	}

	public FuncFor() {
		super();
		setRendererType(FAMILY);
	}

	private int repeat = -1;

	public int getRepeat() {
		if (-1 != repeat)
			return repeat;
		ValueExpression ve = getValueExpression("repeat");
		if (ve != null) {
			return ((Integer) ve.getValue(getFacesContext().getELContext()))
					.intValue();
		}
		return -1;
	}

	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
}
