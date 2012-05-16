package com.kooobao.common.web.tag;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.render.Renderer;

public class FuncForRender extends Renderer {

	@Override
	public boolean getRendersChildren() {
		return true;
	}

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		super.encodeBegin(context, component);
	}

	@Override
	public void encodeChildren(FacesContext context, UIComponent component)
			throws IOException {
		FuncFor funcFor = (FuncFor) component;
		for (int i = 0; i < funcFor.getRepeat(); i++)
			super.encodeChildren(context, component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		super.encodeEnd(context, component);
	}
}
