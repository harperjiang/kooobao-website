package com.kooobao.wechat;

import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.kooobao.wechat.msg.OutgoingMessage;
import com.kooobao.wechat.msg.TextOutgoing;

public class MessageSender {

	protected static final String LOGIN_URL = "https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";

	protected static final String SEND_MSG_URL = "https://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response";

	protected static final String SEND_REFERER_URL = "https://mp.weixin.qq.com/cgi-bin/singlemsgpage?token={0}&fromfakeid={1}&msgid=&source=&count=20&t=wxm-singlechat&lang=zh_CN";

	private Logger logger = LoggerFactory.getLogger(getClass());

	private HttpClient client;

	private LoginStatus status;

	public MessageSender() {
		status = new LoginStatus();
	}

	public void login(String username, String password) throws Exception {
		if (status.loggedIn && !StringUtils.isEmpty(status.token))
			return;
		HttpPost loginpost = new HttpPost(LOGIN_URL);
		loginpost.addHeader("Accept",
				"application/json, text/javascript, */*; q=0.01");
		loginpost.addHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		loginpost.addHeader("Origin", "https://mp.weixin.qq.com");
		loginpost
				.addHeader("Referer",
						"https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN");
		loginpost
				.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64)");
		loginpost.addHeader("X-Requested-With", "XMLHttpRequest");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("username", username));
		nvps.add(new BasicNameValuePair("pwd", md5(password)));
		nvps.add(new BasicNameValuePair("imgcode", ""));
		nvps.add(new BasicNameValuePair("f", "json"));

		loginpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

		HttpResponse response = getClient().execute(loginpost);

		@SuppressWarnings("unchecked")
		Map<String, Object> map = new Gson().fromJson(new InputStreamReader(
				response.getEntity().getContent()), HashMap.class);

		if ((Double) map.get("ErrCode") == 0) {
			String getToken = (String) map.get("ErrMsg");
			Matcher matcher = PATTERN.matcher(getToken);
			if (matcher.matches()) {
				status.loggedIn = true;
				status.token = matcher.group(1);
			} else {
				if (logger.isErrorEnabled()) {
					logger.error("Cannot find token in response:" + getToken);
				}
				throw new RuntimeException(
						"Login Failed as no token found in response");
			}
		}
	}

	public void send(OutgoingMessage outgoing) throws Exception {
		if (!status.loggedIn || StringUtils.isEmpty(status.token)) {
			throw new IllegalStateException("Not logged in");
		}

		String fakeid = "1999429661";

		HttpPost sendpost = new HttpPost(SEND_MSG_URL);
		sendpost.setHeader("Content-Type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		sendpost.setHeader("Host", "mp.weixin.qq.com");
		sendpost.setHeader("Origin", "https://mp.weixin.qq.com");
		sendpost.setHeader("Referer",
				MessageFormat.format(SEND_REFERER_URL, status.token, fakeid));
		sendpost.setHeader("X-Requested-With", "XMLHttpRequest");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("tofakeid", fakeid));
		nvps.add(new BasicNameValuePair("type", "1"));
		nvps.add(new BasicNameValuePair("token", status.token));
		nvps.add(new BasicNameValuePair("ajax", "1"));

		// TODO Support Text only
		if (outgoing instanceof TextOutgoing)
			nvps.add(new BasicNameValuePair("content",
					((TextOutgoing) outgoing).getContent()));
		else {
			throw new IllegalArgumentException("Support text message only");
		}

		sendpost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

		getClient().execute(sendpost);
		//
		// $send_snoopy = new Snoopy;
		// $post = array();
		// $post['tofakeid'] = $id;
		// $post['type'] = 1;
		// $post['token'] = $this->_token;
		// $post['content'] = $content;
		// $post['ajax'] = 1;
		// $send_snoopy->referer =
		// "https://mp.weixin.qq.com/cgi-bin/singlemsgpage?fromfakeid={$id}&msgid=&source=&count=20&t=wxm-singlechat&lang=zh_CN";
		// $send_snoopy->rawheaders['Cookie']= $this->cookie;
		// $submit =
		// "https://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response";
		// $send_snoopy->submit($submit,$post);
		// $this->log($send_snoopy->results);
		// return $send_snoopy->results;

	}

	private static Pattern PATTERN = Pattern
			.compile("[\t\n ]*/cgi\\-bin/indexpage\\?t=wxm\\-index&lang=zh_CN&token=([0-9]+)[\t\n ]*");

	private String md5(String password) throws Exception {
		password = password.substring(0, password.length() >= 16 ? 16
				: password.length());
		MessageDigest md5dig = MessageDigest.getInstance("md5");
		md5dig.update(password.getBytes("iso-8859-1"));

		byte[] bytes = md5dig.digest();
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	protected HttpClient getClient() {
		if (null == client)
			client = new DefaultHttpClient();
		return client;
	}

	protected static class LoginStatus {
		private boolean loggedIn;

		private String token;

		public boolean isLoggedIn() {
			return loggedIn;
		}

		public void setLoggedIn(boolean loggedIn) {
			this.loggedIn = loggedIn;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

	}
}
