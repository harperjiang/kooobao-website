package com.kooobao.am.servlet;

import java.io.IOException;

import javax.persistence.NoResultException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kooobao.am.entity.Document;
import com.kooobao.am.facade.DocumentFacade;

/**
 * Servlet implementation class DocumentServlet
 */
public class DocumentServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6915609669055728421L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DocumentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doWork(request, response);
	}

	protected void doWork(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		DocumentRequestBean reqBean = parse(request.getPathTranslated());

		try {
			Document doc = getDocumentFacade().getDocument(reqBean.getDocId());
			
//			Redirect to Requested Doc Path
//			response.addHeader();
		} catch (NoResultException e) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private DocumentRequestBean parse(String pathTranslated) {
		return null;
	}

	protected DocumentFacade getDocumentFacade() {
		return null;
	}
}
