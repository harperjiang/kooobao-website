package com.kooobao.lm.profile;

import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class DummyMailSender implements JavaMailSender {

	public void send(SimpleMailMessage simpleMessage) throws MailException {
		// TODO Auto-generated method stub

	}

	public void send(SimpleMailMessage[] simpleMessages) throws MailException {
		// TODO Auto-generated method stub

	}

	public MimeMessage createMimeMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	public MimeMessage createMimeMessage(InputStream contentStream)
			throws MailException {
		// TODO Auto-generated method stub
		return null;
	}

	public void send(MimeMessage mimeMessage) throws MailException {
		// TODO Auto-generated method stub

	}

	public void send(MimeMessage[] mimeMessages) throws MailException {
		// TODO Auto-generated method stub

	}

	public void send(MimeMessagePreparator mimeMessagePreparator)
			throws MailException {
		
	}

	public void send(MimeMessagePreparator[] mimeMessagePreparators)
			throws MailException {
		// TODO Auto-generated method stub

	}

}
