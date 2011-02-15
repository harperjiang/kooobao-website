package org.harper.frm.util.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.mail.javamail.MimeMailMessage;

public abstract class TemplateMailMessage extends MimeMailMessage {

	public TemplateMailMessage(MimeMessage mimeMessage) {
		super(mimeMessage);
	}

	private String templateName;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String loadTemplate() {
		InputStream is = TemplateMailMessage.class
				.getResourceAsStream(getTemplateName());
		StringWriter sw = new StringWriter();
		try {
			IOUtils.copy(is, sw);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

//		FacesContext faces = FacesContext.getCurrentInstance();
//		ViewHandler view = faces.getApplication().getViewHandler();
//		
//
//		UIViewRoot root = view.createView(faces, getTemplateName());
//		try {
//			view.renderView(faces, root);
//		} catch (FacesException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		view.
		
		return sw.toString();
	}
}
