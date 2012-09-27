package com.kooobao.dm;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;

import com.kooobao.common.spring.ApplicationContextHolder;
import com.kooobao.common.util.ConfigLoader;
import com.kooobao.common.util.Extension;
import com.kooobao.common.util.MimeType;

/**
 * Servlet implementation class DownloadServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Check From Site
		String acceptSite = ConfigLoader.getInstance().load("config",
				"ACCEPT_SITE");
		if (null == acceptSite
				|| !ArrayUtils.contains(acceptSite.split(";"),
						request.getRemoteHost())) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
		// Get File Id
		try {
			Long fileId = Long.parseLong(request.getParameter("file_id"));
			DownloadService ds = ApplicationContextHolder.getInstance()
					.getApplicationContext().getBean(DownloadService.class);
			// TODO Handle Restoring Download
			FileItem fileItem = ds.getFile(fileId);
			InputStream fis = ds.getFileContent(fileId, 0);

			MimeType contentType = MimeType.text_plain;
			try {
				String extension = fileItem.getDisplayName().substring(
						fileItem.getDisplayName().lastIndexOf('.'));
				contentType = Extension.match(extension).getType();
			} catch (Exception e) {
//				contentType = MimeType.application_octet_stream;
			}
			response.setContentType(contentType.fullName());

			response.setHeader(
					"Content-Disposition",
					"attachment;filename="
							+ java.net.URLEncoder.encode(
									fileItem.getDisplayName(), "utf8"));
			IOUtils.copy(fis, response.getOutputStream());
			fis.close();
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

	}
}
