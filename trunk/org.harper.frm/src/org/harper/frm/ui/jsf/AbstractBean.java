package org.harper.frm.ui.jsf;

import java.text.MessageFormat;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class AbstractBean {
	protected void addMessage(String messageKey, Object param) {
		FacesContext context = FacesContext.getCurrentInstance();

		String msgPattern = messageKey;

		String msg = msgPattern;

		if (param != null) {
			Object[] params = { param };
			msg = MessageFormat.format(msgPattern, params);
		}

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				msg, msg);
		context.addMessage(null, facesMsg);
	}
}
