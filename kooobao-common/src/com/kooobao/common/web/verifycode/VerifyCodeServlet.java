package com.kooobao.common.web.verifycode;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class VerifyCodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3457247207493780085L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		VerifyKey key = VerifyCodeManager.getInstance().createKey();
		ImageWriter writer = VerifyCodeManager.getInstance().generate(key);
		req.getSession(true).setAttribute(VerifyKey.KEY, key);
		resp.setContentType(writer.getContentType());
		IOUtils.copy(writer.getInputStream(), resp.getOutputStream());
		resp.getOutputStream().close();
	}
}
