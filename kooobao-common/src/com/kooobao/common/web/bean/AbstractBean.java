package com.kooobao.common.web.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

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

	public String getParameter(String paramName) {
		return FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get(paramName);
	}

	public Cookie getCookie(String name) {
		if (StringUtils.isEmpty(name))
			return null;
		Cookie[] cookies = ((HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest())
				.getCookies();
		if (null == cookies)
			return null;
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName()))
				return cookie;
		}
		return null;
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

	protected void addMessage(Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severity, summary, detail));
	}

	protected void addDialogMessage(String id, Severity severity,
			String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(id,
				new FacesMessage(severity, summary, detail));

	}

	protected void navigate(String to) {
		FacesContext.getCurrentInstance().getApplication()
				.getNavigationHandler()
				.handleNavigation(FacesContext.getCurrentInstance(), null, to);
	}

	protected List<FileBean> fileBeans;

	public AbstractBean() {
		super();
	}
}
