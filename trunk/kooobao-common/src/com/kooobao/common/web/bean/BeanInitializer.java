package com.kooobao.common.web.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kooobao.common.util.ConfigLoader;

public class BeanInitializer implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6532863584930774568L;

	public void afterPhase(PhaseEvent phaseEvent) {

	}

	public void beforePhase(PhaseEvent phaseEvent) {
		process(phaseEvent);
	}

	protected void process(PhaseEvent phaseEvent) {
		FacesContext facesContext = phaseEvent.getFacesContext();
		String viewId = phaseEvent.getFacesContext().getViewRoot().getViewId();
		if (StringUtils.isEmpty(viewId))
			return;
		Logger log = LoggerFactory.getLogger(getClass());
		if (viewId.endsWith(".xhtml") || viewId.endsWith(".html")
				|| viewId.endsWith(".htm")) {
			String[] managedBeanName = getManagedBeanNameFromView(viewId);
			Object object = null;
			for (int i = 0; i < managedBeanName.length; i++) {
				object = facesContext.getApplication().evaluateExpressionGet(
						facesContext, "#{" + managedBeanName[i] + "}",
						Object.class);
				if (object != null)
					break;
			}
			if (object == null) {
				if (log.isDebugEnabled())
					log.debug(
							"OnPageLoad cannot be executed, no managed bean for {}",
							viewId);
			} else {
				if (object instanceof JSFLifecycleAware) {
					JSFLifecycleAware lifeCycleAwareBean = (JSFLifecycleAware) object;
					lifeCycleAwareBean.onPageLoad();
					if (log.isDebugEnabled()) {
						log.debug("OnPageLoad is executed on {}",
								object.getClass());
					}
				} else {
					if (log.isDebugEnabled())
						log.debug("Bean {} is not Lifecycle Aware", object
								.getClass().getSimpleName());
				}
			}
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	protected synchronized String[] getManagedBeanNameFromView(String viewId) {
		String key = convertViewId(viewId);
		if (beanNames.containsKey(key))
			return beanNames.get(key);
		String value = ConfigLoader.getInstance().load("page_load", key);
		if (StringUtils.isEmpty(value)) {
			value = key + "Bean";
		}
		beanNames.put(key, new String[] { key, value });
		return beanNames.get(key);
	}

	Map<String, String[]> beanNames = new HashMap<String, String[]>();

	private String convertViewId(String viewId) {
		StringBuilder sb = new StringBuilder();
		sb.append(viewId);
		// Delete the first slash
		sb.deleteCharAt(0);
		sb.delete(viewId.length() - 7, viewId.length());
		for (int index = 0; index < sb.length(); index++) {
			if ('_' == sb.charAt(index)) {
				sb.deleteCharAt(index);
				sb.setCharAt(index, (char) (sb.charAt(index) - ('a' - 'A')));
			}
		}
		String key = sb.toString();
		return key;
	}
}
