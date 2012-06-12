package com.kooobao.common.web.tag;

import java.io.IOException;
import java.text.MessageFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.myfaces.shared.renderkit.html.HTML;

public class HtmlProgressRenderer extends Renderer {

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		UIProgress progress = (UIProgress) component;

		int value = progress.getValue();
		int width = progress.getWidth();
		int height = progress.getHeight();
		String bg = progress.getBackgroundColor();
		String fg = progress.getColor();
		String sc = progress.getStyleClass();

		ResponseWriter writer = context.getResponseWriter();
		writer.startElement(HTML.DIV_ELEM, component);
		writer.writeAttribute("style", MessageFormat.format(
				"width:{0}px;height:{1}px;{2}", width, height, sc), null);

		writer.startElement(HTML.DIV_ELEM, component);
		writer.writeAttribute("style", MessageFormat.format(
				"float:left;width:{0}%;height:{1}px;background-color:{2}",
				value, height, fg), null);
		writer.endElement(HTML.DIV_ELEM);

		writer.startElement(HTML.DIV_ELEM, component);
		writer.writeAttribute("style", MessageFormat.format(
				"float:left;width:{0}%;height:{1}px;background-color:{2}",
				100 - value, height, bg), null);
		writer.endElement(HTML.DIV_ELEM);

		writer.endElement(HTML.DIV_ELEM);

		super.encodeEnd(context, component);
	}
}
