package com.kooobao.authcenter.listener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kooobao.authcenter.Constants;
import com.kooobao.authcenter.service.AuthenticateService;
import com.kooobao.authcenter.service.Token;

public class LoginAuthorizer implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3841769738586236647L;

	public LoginAuthorizer() {
		setAuthService(new ClassPathXmlApplicationContext("authContext.xml")
				.getBean("authService", AuthenticateService.class));
	}

	public void beforePhase(PhaseEvent event) {

	}


	public void afterPhase(PhaseEvent event) {
		String viewId = event.getFacesContext().getViewRoot().getViewId();

		if (needValidate(viewId)) {
			HttpSession ssn = (HttpSession) event.getFacesContext()
					.getExternalContext().getSession(true);
			Object token = ssn.getAttribute(Constants.TOKEN);
			if (!(token instanceof Token)
					|| !getAuthService().validate((Token) token)) {
				// Log URL
				ssn.setAttribute(Constants.JUMP_URL, viewId);
				// Validate Failed, require login
				event.getFacesContext()
						.getApplication()
						.getNavigationHandler()
						.handleNavigation(event.getFacesContext(), null,
								"require_login");
			}
		}
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	protected boolean needValidate(String viewId) {
		return false;
//		return !"/login.xhtml".equals(viewId);
	}

	private AuthenticateService authService;

	public AuthenticateService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthenticateService authService) {
		this.authService = authService;
	}

}
