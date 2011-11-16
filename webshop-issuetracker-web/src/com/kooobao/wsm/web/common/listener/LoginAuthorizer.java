package com.kooobao.wsm.web.common.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import com.kooobao.wsm.web.login.LoginBean;

public class LoginAuthorizer implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3841769738586236647L;

	public void beforePhase(PhaseEvent event) {

	}

	public void afterPhase(PhaseEvent event) {
		String viewId = event.getFacesContext().getViewRoot().getViewId();
		if ("/login.xhtml".equals(viewId))
			return;
		LoginBean login = event
				.getFacesContext()
				.getApplication()
				.evaluateExpressionGet(event.getFacesContext(), "#{login}",
						LoginBean.class);
		if (!login.isAuthorized()) {
			event.getFacesContext().getApplication().getNavigationHandler()
					.handleNavigation(event.getFacesContext(), null, "logout");
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
