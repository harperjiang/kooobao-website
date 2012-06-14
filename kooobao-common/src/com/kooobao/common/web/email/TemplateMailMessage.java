package com.kooobao.common.web.email;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class TemplateMailMessage implements MimeMessagePreparator {

	private Map<String, Object> values;

	private String templateName;

	private String subject;

	private String from;

	private String fromName;

	private String[] to;

	public TemplateMailMessage(String templateName, Map<String, Object> values) {
		this.values = values;
		this.templateName = templateName;
	}

	public void prepare(MimeMessage mimeMessage) throws Exception {
		mimeMessage.setContent(composeContent(), "text/html;charset=utf8");
		mimeMessage.setSubject(MimeUtility
				.encodeText(getSubject(), "utf8", "B"));
		mimeMessage.setFrom(new InternetAddress(getFrom(), getFromName()));
		for (String to : getTo())
			mimeMessage.addRecipient(RecipientType.TO, new InternetAddress(to));
	}

	protected String composeContent() {
		StringWriter sw = new StringWriter();
		Reader sr = loadTemplate();
		Velocity.evaluate(new VelocityContext(values), sw, "Email "
				+ this.templateName, sr);
		return sw.getBuffer().toString();
	}

	private Reader loadTemplate() {
		Validate.isTrue(!StringUtils.isEmpty(templateName));
		return new InputStreamReader(
				TemplateMailMessage.class.getResourceAsStream(templateName));
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

}
