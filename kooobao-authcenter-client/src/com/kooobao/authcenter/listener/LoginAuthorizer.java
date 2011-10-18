package com.kooobao.authcenter.listener;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Pattern;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.kooobao.authcenter.Constants;
import com.kooobao.authcenter.service.AuthenticateService;
import com.kooobao.authcenter.service.Token;
import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.common.util.ConfigLoader;

public class LoginAuthorizer implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3841769738586236647L;

	public LoginAuthorizer() {

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
				ssn.setAttribute(Constants.JUMP_URL, parseUrlFromViewId(viewId));
				// Validate Failed, require login
				event.getFacesContext()
						.getApplication()
						.getNavigationHandler()
						.handleNavigation(event.getFacesContext(), null,
								"require_login");
			}
		}
	}

	protected Object parseUrlFromViewId(String viewId) {
		if (viewId.startsWith("/")) {
			return viewId.substring(1);
		}
		return viewId;
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	protected boolean needValidate(String viewId) {
		String includePageStr = ConfigLoader.getInstance().load("auth_list",
				"include");
		String excludePageStr = ConfigLoader.getInstance().load("auth_list",
				"exclude");
		boolean include = false;
		boolean exclude = false;
		if (!StringUtils.isEmpty(includePageStr)) {
			String[] includes = includePageStr.split(",");
			for (String includePage : includes)
				if (Pattern.matches(includePage, viewId)) {
					include = true;
					break;
				}
		}
		if (!StringUtils.isEmpty(excludePageStr)) {
			String[] excludes = excludePageStr.split(",");
			for (String excludePage : excludes)
				if (Pattern.matches(excludePage, viewId)) {
					exclude = true;
					break;
				}
		}
		return include && !exclude;
	}

	private AuthenticateService authService;

	public AuthenticateService getAuthService() {
		ReadWriteLock lock = new ReentrantReadWriteLock();
		lock.readLock().lock();
		if (null == authService) {
			lock.readLock().unlock();
			lock.writeLock().lock();
			setAuthService(ApplicationContextHolder.getInstance()
					.getApplicationContext().getBean(AuthenticateService.class));
			lock.writeLock().unlock();
			lock.readLock().lock();
		}
		lock.readLock().unlock();
		return authService;
	}

	public void setAuthService(AuthenticateService authService) {
		this.authService = authService;
	}

}
