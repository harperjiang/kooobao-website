package com.kooobao.authcenter.web.bean;

import java.io.IOException;

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

	@ManagedProperty("#{authenticateService}")
	private AuthenticateService authService;

	public String login() {
		Token token = validateLogin();
		if (token == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "登录失败");
			return "false";
		}
		loggedIn = true;
		putTokenInSession(token);
		jumpUrl();
		return "true";
	}

	private void jumpUrl() {
		javax.servlet.http.HttpServletResponse response = (HttpServletResponse) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		Object previousUrl = getSession().getAttribute(Constants.JUMP_URL);

		// Jump to previous URL, index.htm if null
		previousUrl = null == previousUrl ? "/index.htm" : previousUrl;
		try {
			response.sendRedirect(String.valueOf(previousUrl));
		} catch (IOException e) {
			LogFactory.getLog(getClass()).error("Failed to redirect page");
		}
	}

	protected static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
	}

	private void putTokenInSession(Token token) {
		getSession().setAttribute(Constants.TOKEN, token);
	}

	private Token validateLogin() {
		String system = ConfigLoader.getInstance().load("auth_list", "system");
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

}
