package com.kooobao.authcenter.web.bean;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.LogFactory;

import com.kooobao.authcenter.Constants;
import com.kooobao.authcenter.service.AuthenticateService;
import com.kooobao.authcenter.service.Token;
import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean
@SessionScoped
public class LoginBean extends AbstractBean {

	private String userId;

	private String plainPass;

	@ManagedProperty("#{authenticateService}")
	private AuthenticateService authService;

	public String login() {
		Token token = validateLogin();
		if (token == null) {
			addMessage(FacesMessage.SEVERITY_ERROR, "登录失败");
			return "false";
		}
		putTokenInSession(token);
		jumpUrl();

		return "true";
	}

	private void jumpUrl() {
		javax.servlet.http.HttpServletResponse response = (HttpServletResponse) javax.faces.context.FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		Object previousUrl = getSession().getAttribute(Constants.JUMP_URL);
		// Jump to previous URL
		if (null != previousUrl)
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
		return new Token();
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

}
