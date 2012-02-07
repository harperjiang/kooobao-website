package com.kooobao.common.web.fileupload;

import javax.el.ValueExpression;
import javax.faces.component.UIComponentBase;

public class UIFileUpload extends UIComponentBase {

	public static final String COMPONENT_TYPE = "com.kooobao.fileupload";

	public static final String COMPONENT_FAMILY = "com.kooobao.fileupload";

	public UIFileUpload() {
		super();
		setRendererType(COMPONENT_TYPE);
	}

	public String getFamily() {
		return (COMPONENT_FAMILY);
	}

	private Object value = null;

	public Object getValue() {
		if (value != null) {
			return value;
		}
		ValueExpression vb = getValueExpression("value");
		if (vb != null) {
			return (vb.getValue(getFacesContext().getELContext()));
		} else {
			return null;
		}
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public void setValueExpression(String name, ValueExpression binding) {
		if ("value".equals(name)) {

		}
		super.setValueExpression(name, binding);
	}

}
