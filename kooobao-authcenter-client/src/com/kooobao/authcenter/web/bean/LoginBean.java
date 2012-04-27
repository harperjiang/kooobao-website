package com.kooobao.authcenter.web.bean;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;

import com.kooobao.authcenter.Constants;
import com.kooobao.authcenter.listener.LoginAuthorizer;
import com.kooobao.authcenter.service.AuthenticateService;
import com.kooobao.authcenter.service.Token;
import com.kooobao.common.util.ConfigLoader;
import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean
@SessionScoped
public class LoginBean extends AbstractBean {

	private String userId;

	private String plainPass;

	private boolean loggedIn;

	private Date loginDate;

	@ManagedProperty("#{authenticateService}")
	private AuthenticateService authService;

	public String login() {
		Token token = validateLogin();
		if (token == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "登录失败");
			return "false";
		}
		loggedIn = true;
		loginDate = new Date();
		putTokenInSession(token);
		jumpUrl();
		return "true";
	}

	public String logout() {
		getAuthService().logout(getTokenFromSession());
		loggedIn = false;
		loginDate = null;
		return "true";
	}

	private void jumpUrl() {
		javax.servlet.http.HttpServletResponse response = (HttpServletResponse) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		Object previousUrl = getSession().getAttribute(Constants.JUMP_URL);

		// Jump to previous URL, index.htm if null
		String indexPage = ConfigLoader.getInstance().load("auth_list",
				"index_page");
		if (null == indexPage)
			indexPage = "index.xhtml";
		previousUrl = null == previousUrl ? indexPage
				: parseUrlFromViewId(String.valueOf(previousUrl));
		try {
			response.sendRedirect(formatUrl(previousUrl));
		} catch (IOException e) {
			LogFactory.getLog(getClass()).error("Failed to redirect page");
		}
	}

	private String formatUrl(Object previousUrl) {
		return String.valueOf(previousUrl).replaceFirst("xhtml$", "html");
	}

	protected Object parseUrlFromViewId(String viewId) {
		if (viewId.startsWith("/")) {
			return viewId.substring(1);
		}
		return viewId;
	}

	protected static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	private void putTokenInSession(Token token) {
		LoginAuthorizer.setToken(getSession(), system, token);
	}

	private Token getTokenFromSession() {
		return (Token) LoginAuthorizer.getToken(getSession(), system);
	}

	private String system = getSystem();

	protected String getSystem() {
		String val = ConfigLoader.getInstance().load("auth_list", "default");
		if (StringUtils.isEmpty(val))
			val = ConfigLoader.getInstance().load("auth_list", "system");
		return val;
	}

	private Token validateLogin() {
		// Trying to match system based on jump url
		if (StringUtils.isEmpty(system)) {
			LogFactory.getLog(getClass()).warn(
					"System name Not Found, cannot validate login");
			return null;
		}
		Token token = getAuthService().login(system, getUserId(),
				getPlainPass());
		return token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlainPass() {
		return plainPass;
	}

	public void setPlainPass(String plainPass) {
		this.plainPass = plainPass;
	}

	public AuthenticateService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthenticateService authService) {
		this.authService = authService;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	public static String getCurrentUser() {
		LoginBean loginBean = findBean("loginBean");
		if (null == loginBean)
			return null;
		return loginBean.getUserId();
	}
}
