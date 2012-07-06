package com.kooobao.authcenter.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
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
import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.common.util.ConfigLoader;
import com.kooobao.common.web.bean.AbstractBean;

/**
 * Revision History: <br/>
 * version 1.1 2012-04-21 <br/>
 * Support multiple systems
 * 
 * @author jianghao
 * @version 1.1
 */
public class LoginAuthorizer implements PhaseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3841769738586236647L;

	public static Map<String, ValidatePattern> patterns;

	private static boolean multiSystem;

	static {
		patterns = new ConcurrentHashMap<String, ValidatePattern>();
		initPatterns(patterns);
	}

	public LoginAuthorizer() {
		init();
	}

	public void beforePhase(PhaseEvent event) {

	}

	public void afterPhase(PhaseEvent event) {
		String viewId = event.getFacesContext().getViewRoot().getViewId();
		ValidatePattern pattern = null;
		if (null != (pattern = needValidate(viewId))) {
			HttpSession ssn = (HttpSession) event.getFacesContext()
					.getExternalContext().getSession(true);

			Token token = getToken(ssn, pattern.getSystem());
			if (token == null || !getAuthService().validate((Token) token)) {
				// Validation Failed, need re-login
				// Log URL
				ssn.setAttribute(Constants.JUMP_URL, viewId);
				ssn.setAttribute(Constants.LOGIN_SYSTEM, pattern.getSystem());
				// Validate Failed, require login
				if (multiSystem)
					event.getFacesContext()
							.getApplication()
							.getNavigationHandler()
							.handleNavigation(event.getFacesContext(), null,
									"require_login_" + pattern.getSystem());
				else
					event.getFacesContext()
							.getApplication()
							.getNavigationHandler()
							.handleNavigation(event.getFacesContext(), null,
									"require_login");
			} else {
				// Validate the existence of Login Bean
				Token yesToken = (Token) token;
				// In case that the login bean had been discarded while token is
				// still alive
				LoginBean loginBean = getLoginBean(pattern.getSystem());
				if (!yesToken.getUserId().equals(loginBean.getUserId())) {
					loginBean.setUserId(yesToken.getUserId());
					loginBean.setLoggedIn(true);
				}
			}
		}
	}

	private LoginBean getLoginBean(String system) {
		String loginBeanName = ConfigLoader.getInstance().load("auth_list",
				system + ".loginBean");
		if (StringUtils.isEmpty(loginBeanName)) {
			loginBeanName = "loginBean";
		}
		return AbstractBean.findBean(loginBeanName);
	}

	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

	private void init() {
	}

	public static void initPatterns(Map<String, ValidatePattern> patterns) {
		String multiSys = ConfigLoader.getInstance().load("auth_list",
				"multi_system");
		if (multiSys != null && Boolean.valueOf(multiSys)) {
			multiSystem = true;
			String[] system = ConfigLoader.getInstance()
					.load("auth_list", "system").split(",");
			for (String sys : system) {
				patterns.put(
						sys,
						new ValidatePattern(sys, ConfigLoader.getInstance()
								.load("auth_list", sys + ".include"),
								ConfigLoader.getInstance().load("auth_list",
										sys + ".exclude")));
			}
		} else {
			String system = ConfigLoader.getInstance().load("auth_list",
					"system");
			patterns.put(system, new ValidatePattern(system, ConfigLoader
					.getInstance().load("auth_list", "include"), ConfigLoader
					.getInstance().load("auth_list", "exclude")));
		}
	}

	public static Token getToken(HttpSession ssn, String system) {
		@SuppressWarnings("unchecked")
		Map<String, Token> tokens = (Map<String, Token>) ssn
				.getAttribute(Constants.TOKEN);
		if (null == tokens) {
			tokens = new HashMap<String, Token>();
			ssn.setAttribute(Constants.TOKEN, tokens);
		}
		return tokens.get(system);
	}

	public static void setToken(HttpSession ssn, String system, Token token) {
		@SuppressWarnings("unchecked")
		Map<String, Token> tokens = (Map<String, Token>) ssn
				.getAttribute(Constants.TOKEN);
		if (null == tokens) {
			tokens = new HashMap<String, Token>();
			ssn.setAttribute(Constants.TOKEN, tokens);
		}
		tokens.put(system, token);
	}

	protected ValidatePattern needValidate(String viewId) {
		if (StringUtils.isEmpty(viewId))
			return null;
		for (Entry<String, ValidatePattern> pattern : patterns.entrySet()) {
			if (pattern.getValue().validate(viewId)) {
				return pattern.getValue();
			}
		}
		return null;
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

	public static class ValidatePattern {

		private String system;

		private String includeStr;

		private String excludeStr;

		private String[] includes;

		private String[] excludes;

		ValidatePattern(String system, String includeStr, String excludeStr) {
			this.system = system;
			this.includeStr = includeStr;
			this.excludeStr = excludeStr;
		}

		public boolean validate(String viewId) {
			boolean include = false;
			boolean exclude = false;
			if (!StringUtils.isEmpty(includeStr)) {
				if (null == includes)
					includes = includeStr.split(",");
				for (String includePage : includes)
					if (Pattern.matches(includePage, viewId)) {
						include = true;
						break;
					}
			}
			if (!StringUtils.isEmpty(excludeStr)) {
				if (null == excludes)
					excludes = excludeStr.split(",");
				for (String excludePage : excludes)
					if (Pattern.matches(excludePage, viewId)) {
						exclude = true;
						break;
					}
			}
			return include && !exclude;
		}

		public String getSystem() {
			return system;
		}

	}

}
