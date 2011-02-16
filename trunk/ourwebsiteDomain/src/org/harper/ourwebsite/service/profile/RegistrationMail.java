package org.harper.ourwebsite.service.profile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.harper.frm.core.logging.LogManager;
import org.harper.frm.util.mail.TemplateMailMessage;
import org.harper.ourwebsite.domain.entity.profile.User;
import org.springframework.mail.javamail.MimeMessageHelper;


public class RegistrationMail extends TemplateMailMessage {

	public RegistrationMail(MimeMessage msg, User usr) {
		super(msg);

		setTemplateName("/org/harper/ourwebsite/service/profile/registration_mail.xhtml");
		MimeMessageHelper messageHelper = new MimeMessageHelper(msg, "UTF-8");

		//
		try {
			messageHelper.setTo(usr.getEmail());
			messageHelper.setFrom("webmaster@kooobao.cn");
			messageHelper.setSubject("Registration Mail");
			messageHelper.setText(loadTemplate(), true);
		} catch (MessagingException e) {
			LogManager.getInstance().getLogger(getClass())
					.error("Exception", e);
		}

	}

}
