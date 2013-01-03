package com.kooobao.common.web.tag;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import org.apache.myfaces.shared.renderkit.html.HTML;

public class HtmlMessagesDialogRenderer extends Renderer {

	@Override
	public void encodeBegin(FacesContext context, UIComponent component)
			throws IOException {
		super.encodeBegin(context, component);
	}

	@Override
	public void encodeEnd(FacesContext context, UIComponent component)
			throws IOException {
		List<FacesMessage> messages = context.getMessageList();
		if (null == messages || messages.size() == 0)
			return;
		String id = component.getId();
		// Write Data
		ResponseWriter writer = context.getResponseWriter();
		// Write Mask Div
		writer.startElement(HTML.DIV_ELEM, component);
		writer.writeAttribute("id", id + "_mask", null);
		writer.writeAttribute("class", "mask", null);
		writer.endElement(HTML.DIV_ELEM);

		writer.startElement(HTML.DIV_ELEM, component);
		writer.writeAttribute("id", id + "_dialog", null);
		writer.writeAttribute("class", "dialog", null);

		writer.startElement(HTML.DIV_ELEM, component);
		writer.writeAttribute("class", "dialog_title", null);
		// Only the summary of the first message will be displayed;
		writer.writeText(messages.get(0).getSummary(), null);
		writer.endElement(HTML.DIV_ELEM);

		writer.startElement(HTML.DIV_ELEM, component);
		writer.writeAttribute("class", "dialog_content", null);

		for (FacesMessage msg : messages) {
			writer.startElement(HTML.SPAN_ELEM, component);
			if (msg.getSeverity() == FacesMessage.SEVERITY_INFO)
				writer.writeAttribute("class", "info", null);
			else if (msg.getSeverity() == FacesMessage.SEVERITY_WARN)
				writer.writeAttribute("class", "warn", null);
			else
				writer.writeAttribute("class", "error", null);
			writer.writeText(msg.getDetail(), null);
			writer.endElement(HTML.SPAN_ELEM);
			writer.startElement(HTML.BR_ELEM, null);
			writer.endElement(HTML.BR_ELEM);
		}
		writer.endElement(HTML.DIV_ELEM);

		writer.startElement(HTML.BUTTON_ELEM, component);
		writer.writeAttribute("class", "dialog_btn", null);
		writer.writeAttribute("onclick", "hide_dialog('" + id + "')", null);
		writer.writeText("OK", null);
		writer.endElement(HTML.BUTTON_ELEM);

		writer.endElement(HTML.DIV_ELEM);

		writer.startElement(HTML.SCRIPT_ELEM, component);
		writer.writeAttribute(HTML.SCRIPT_LANGUAGE_ATTR,
				HTML.SCRIPT_LANGUAGE_JAVASCRIPT, null);

		writer.writeText(
				"var dialog = document.getElementById(\""
						+ id
						+ "_dialog\");"
						+ "dialog.style.left = (document.body.clientWidth - 400) / 2 + \"px\";"
						+ "dialog.style.top = (document.body.clientHeight - 200) / 2 + \"px\";"
						+ "function hide_dialog(name) {"
						+ "	document.getElementById(name + \"_mask\").style.display = \"none\";"
						+ "	document.getElementById(name + \"_dialog\").style.display = \"none\";"
						+ "}", null);
		writer.endElement(HTML.SCRIPT_ELEM);
	}

}
