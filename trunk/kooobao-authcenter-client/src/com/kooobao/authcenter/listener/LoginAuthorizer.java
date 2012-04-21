package com.kooobao.authcenter.listener;

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
			} else {
				// Validate the existence of Login Bean
				Token yesToken = (Token) token;
				LoginBean loginBean = AbstractBean.findBean("loginBean");
				if (!yesToken.getUserId().equals(loginBean.getUserId())) {
					loginBean.setUserId(yesToken.getUserId());
					loginBean.setLoggedIn(true);
				}
			}
		}
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
			String[] system = ConfigLoader.getInstance()
					.load("auth_list", "system").split(",");
			for (String sys : system) {
				patterns.put(
						sys,
						new ValidatePattern(ConfigLoader.getInstance().load(
								"auth_list", sys + ".include"), ConfigLoader
								.getInstance().load("auth_list",
										sys + ".exclude")));
			}
		} else {
			String system = ConfigLoader.getInstance().load("auth_list",
					"system");
			patterns.put(system, new ValidatePattern(ConfigLoader.getInstance()
					.load("auth_list", "include"), ConfigLoader.getInstance()
					.load("auth_list", "exclude")));
		}
	}

	protected boolean needValidate(String viewId) {
		if (StringUtils.isEmpty(viewId))
			return false;
		for (Entry<String, ValidatePattern> pattern : patterns.entrySet()) {
			if (pattern.getValue().validate(viewId)) {
				return true;
			}
		}
		return false;
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

		private String includeStr;

		private String excludeStr;

		ValidatePattern(String includeStr, String excludeStr) {
			this.includeStr = includeStr;
			this.excludeStr = excludeStr;
		}

		public boolean validate(String viewId) {
			boolean include = false;
			boolean exclude = false;
			if (!StringUtils.isEmpty(includeStr)) {
				String[] includes = includeStr.split(",");
				for (String includePage : includes)
					if (Pattern.matches(includePage, viewId)) {
						include = true;
						break;
					}
			}
			if (!StringUtils.isEmpty(excludeStr)) {
				String[] excludes = excludeStr.split(",");
				for (String excludePage : excludes)
					if (Pattern.matches(excludePage, viewId)) {
						exclude = true;
						break;
					}
			}
			return include && !exclude;
		}

	}

}
