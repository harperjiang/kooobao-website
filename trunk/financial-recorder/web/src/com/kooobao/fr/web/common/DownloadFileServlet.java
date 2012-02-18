package com.kooobao.fr.web.common;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.fr.domain.entity.Attachment;
import com.kooobao.fr.service.AttachmentService;

/**
 * Servlet implementation class DownloadFileServlet
 */
public class DownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadFileServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String attachmentOid = request.getParameter("file");
		AttachmentService attachmentService = ApplicationContextHolder
				.getInstance().getApplicationContext()
				.getBean(AttachmentService.class);
		Attachment attachment = attachmentService.getAttachment(attachmentOid);
		if (null == attachment || null == attachment.getFile()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		} else {
			FileInputStream fis = new FileInputStream(attachment.getFile()
					.getPath());
			response.setContentType(attachment.getFile().getContentType());
			response.setHeader("Content-Disposition", "attachment;filename="
					+ java.net.URLEncoder.encode(attachment.getName(), "utf8"));
			IOUtils.copy(fis, response.getOutputStream());
			fis.close();
		}
	}
}
