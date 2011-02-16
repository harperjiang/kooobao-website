package org.harper.ourwebsite.service.profile;

import java.security.MessageDigest;

import org.harper.frm.core.tools.bean.Base64Encoding;
import org.harper.frm.dao.CommonDao;
import org.harper.frm.service.Service;
import org.harper.frm.util.mail.PasswordGenerator;
import org.harper.ourwebsite.domain.entity.profile.User;
import org.springframework.mail.javamail.JavaMailSender;


public class UserServiceImpl extends Service implements UserService {

	public void createUser(User user) {
		startTransaction();
		try {
			// // Assign User ID
			user.setId(getCommonDao().getNumber(CommonDao.NUMBER_TYPE_USER));
			// Generate Arbitary Password
			String password = PasswordGenerator.genPassword(10);
			String digest = new String(Base64Encoding.encode(MessageDigest
					.getInstance("MD5").digest(password.getBytes())));
			user.setPassword(password);
			user.setPasswordMD5(digest);
			
			user.setStatus(User.Status.NEW.ordinal());
			user.setRegcode(PasswordGenerator.genPassword(128));
			// Save User
			user = getCommonDao().store(user);
			commitTransaction();
		} catch (Exception e) {
			releaseTransaction();
		}
		// Send Registration Email to user mailbox
		getMailSender().send(
				new RegistrationMail(getMailSender().createMimeMessage(), user)
						.getMimeMessage());
	}

	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	private CommonDao commonDao;

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public void activeUser(User user) {
		// TODO Auto-generated method stub
		
	}
}
