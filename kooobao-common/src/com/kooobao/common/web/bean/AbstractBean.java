package com.kooobao.common.web.bean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.apache.commons.io.IOUtils;

import com.kooobao.common.util.ConfigLoader;
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
