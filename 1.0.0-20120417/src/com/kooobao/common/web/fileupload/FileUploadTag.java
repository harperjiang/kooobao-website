package com.kooobao.common.web.fileupload;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.faces.webapp.UIComponentELTag;

public class FileUploadTag extends UIComponentELTag {

	@Override
	public String getComponentType() {
		return UIFileUpload.COMPONENT_TYPE;
	}

	@Override
	public String getRendererType() {
		return UIFileUpload.COMPONENT_TYPE;
	}

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	protected void setProperties(UIComponent component) {
		super.setProperties(component);
		FacesContext context = getFacesContext();

		if (value != null) {
			ValueBinding vb = context.getApplication()
					.createValueBinding(value);
			component.setValueBinding("value", vb);
		}
	}
}
