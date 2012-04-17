package com.kooobao.common.web.fileupload;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

public class FileUploadRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {

		if (!component.isRendered()) {
			return;
		}

		if (((UIFileUpload) component).getValue() == null) {
			return;
		}

		ResponseWriter out = context.getResponseWriter();
		out.startElement("input", component);
		out.writeAttribute("id",component.getId(),"id");
		out.writeAttribute("type", "file", "type");
		out.endElement("input");
	}
}
