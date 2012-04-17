package com.kooobao.common.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.kooobao.common.web.fileupload.FileBean;

public abstract class AbstractBean implements JSFLifecycleAware {

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findBean(String beanName) {
		FacesContext context = FacesContext.getCurrentInstance();
		return (T) context.getApplication().evaluateExpressionGet(context,
				"#{" + beanName + "}", Object.class);
	}

	public UIComponent getComponent(String id) {
		if (null == id || "".equals(id))
			return null;
		UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();
		List<UIComponent> components = new ArrayList<UIComponent>();
		components.addAll(root.getChildren());

		for (int index = 0; index < components.size(); index++) {
			if (id.equals(components.get(index).getId()))
				return components.get(index);
			components.addAll(components.get(index).getChildren());
		}
		return null;
	}

	public void onPageLoad() {
	}

	protected void addMessage(Severity severity, String message) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, message, message));

	}
	
	protected List<FileBean> fileBeans;


	public AbstractBean() {
		super();
	}
}
