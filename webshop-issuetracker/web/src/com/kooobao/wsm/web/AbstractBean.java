package com.kooobao.wsm.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.wsm.web.common.listener.JSFLifecycleAware;

public abstract class AbstractBean implements JSFLifecycleAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2271163629134210455L;

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

	public String getCurrentUserId() {
		LoginBean loginBean = findBean("login");
		if (null != loginBean)
			return loginBean.getUserId();
		return null;
	}

	public void onPageLoad() {
	}

	public AbstractBean() {
		super();
	}
}
