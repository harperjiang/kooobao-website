package com.kooobao.common.web.tag;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.webapp.UIComponentELTag;

public class FuncForTag extends UIComponentELTag {

	@Override
	public String getComponentType() {
		return FuncFor.FAMILY;
	}

	@Override
	public String getRendererType() {
		return FuncFor.FAMILY;
	}

	@Override
	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		if (null != repeat) {
			component.setValueExpression("repeat", repeat);
		}
	}

	@Override
	public void release() {
		super.release();
		repeat = null;
	}

	private ValueExpression repeat;

	public ValueExpression getRepeat() {
		return repeat;
	}

	public void setRepeat(ValueExpression repeat) {
		this.repeat = repeat;
	}

}
