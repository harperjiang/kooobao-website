package com.kooobao.wechat;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kooobao.wechat.msg.IncomingMessage;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextOutgoing;

/**
 * Servlet implementation class VerifyServlet
 */
public class EntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntryServlet() {
		super();
	}

	final static String token = "kooobao";

	protected boolean verify(String sig, String timestamp, String nonce)
			throws Exception {
		if (null == sig || sig.isEmpty() || null == timestamp
				|| timestamp.isEmpty() || null == nonce || nonce.isEmpty())
			return false;
		StringBuilder sb = new StringBuilder();
		String[] result = new String[] { token, timestamp, nonce };
		Arrays.sort(result);
		sb.append(result[0]).append(result[1]).append(result[2]);

		MessageDigest md = MessageDigest.getInstance("sha1");
		byte[] digest = md.digest(sb.toString().getBytes("iso-8859-1"));
		sb.delete(0, sb.length());
		for (int i = 0; i < digest.length; i++) {
			sb.append(String.format("%02x", digest[i]));
		}
		return sig.equals(sb.toString());
	}

	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		try {
			if (signature.equals(verify(signature, timestamp, nonce))) {
				response.getOutputStream()
						.write(echostr.getBytes("iso-8859-1"));
				response.getOutputStream().flush();
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Cannot verify signature", e);
			}
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		IncomingMessage incoming = null;
		OutgoingMessage outgoing = null;
		try {
			incoming = IncomingMessage.parse(req.getInputStream());
			outgoing = MessageProcessor.process(incoming);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Error when processing messages", e);
			}
			if (incoming != null)
				outgoing = errormsg(incoming);
			else {
				resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				return;
			}
		}
		resp.getOutputStream().write(outgoing.toString().getBytes("utf8"));
		resp.getOutputStream().flush();
	}

	protected OutgoingMessage errormsg(IncomingMessage incoming) {
		return new TextOutgoing(incoming, "很抱歉无法处理这个消息");
	}
}
