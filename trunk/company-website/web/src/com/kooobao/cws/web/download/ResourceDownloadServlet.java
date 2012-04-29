package com.kooobao.cws.web.download;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kooobao.authcenter.listener.LoginAuthorizer;
import com.kooobao.authcenter.service.AuthenticateService;
import com.kooobao.authcenter.service.Token;
import com.kooobao.common.spring.ApplicationContextHolder;

/**
 * Servlet implementation class ResourceDownloadServlet
 */
public class ResourceDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ResourceDownloadServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (verifyLogin(request)) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return;
		}
	}

	private boolean verifyLogin(HttpServletRequest request) {
		Token token = LoginAuthorizer.getToken(request.getSession(), "website");
		return !(token == null || !getAuthService().validate((Token) token));
	}

	protected AuthenticateService getAuthService() {
		return ApplicationContextHolder.getInstance().getApplicationContext()
				.getBean(AuthenticateService.class);

	}
}
