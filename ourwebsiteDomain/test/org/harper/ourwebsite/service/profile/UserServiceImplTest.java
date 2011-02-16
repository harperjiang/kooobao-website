package org.harper.ourwebsite.service.profile;

import org.harper.ourwebsite.domain.dao.toplink.CommonDaoImpl;
import org.harper.ourwebsite.domain.entity.profile.User;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class UserServiceImplTest {

	@Test
	public void testCreateUser() {
		UserServiceImpl usi = new UserServiceImpl();
		usi.setCommonDao(new CommonDaoImpl());

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("mail4.myhostadmin.net");
		mailSender.getJavaMailProperties().put("mail.smtp.auth", "true");
		mailSender.setUsername("webmaster@kooobao.cn");
		mailSender.setPassword("jieninan");

		usi.setMailSender(mailSender);

		User usr = new User();
		usr.setName("张三");
		usr.setEmail("harperjiang@gmail.com");
		usr.setType("P");
		usi.createUser(usr);

	}

}
