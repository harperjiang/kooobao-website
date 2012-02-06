package com.kooobao.common.web.bean;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BeanInitializer implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6532863584930774568L;

	public void afterPhase(PhaseEvent phaseEvent) {
		FacesContext facesContext = phaseEvent.getFacesContext();
		String viewId = phaseEvent.getFacesContext().getViewRoot().getViewId();
		Log log = LogFactory.getLog(getClass());
		if (viewId.endsWith(".xhtml")) {
			String[] managedBeanName = getManagedBeanNameFromView(viewId);
			Object object = null;
			for (int i = 0; i < managedBeanName.length; i++) {
				object = facesContext.getApplication().evaluateExpressionGet(
						facesContext, "#{" + managedBeanName[i] + "}",
						Object.class);
				if (object != null)
					break;
			}
			if (object == null)
				log.debug("OnPageLoad cannot be executed, no such managed bean:"
						+ managedBeanName);
			else {
				if (object instanceof JSFLifecycleAware) {
					JSFLifecycleAware lifeCycleAwareBean = (JSFLifecycleAware) object;
					lifeCycleAwareBean.onPageLoad();
				} else {
					log.debug("Bean " + object.getClass().getSimpleName()
							+ " is not Lifecycle Aware");
				}
			}
		}
	}

	public void beforePhase(PhaseEvent phaseEvent) {

	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	protected String[] getManagedBeanNameFromView(String viewId) {
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
		return new String[]{sb.toString(),sb.toString()+"Bean"};
	}
}
