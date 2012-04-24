package com.kooobao.cws.service.customer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.persistence.NoResultException;

import org.springframework.mail.javamail.JavaMailSender;

import com.kooobao.authcenter.service.UserService;
import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.email.TemplateMailMessage;
import com.kooobao.cws.domain.customer.Contact;
import com.kooobao.cws.domain.customer.ContactType;
import com.kooobao.cws.domain.customer.Customer;
import com.kooobao.cws.domain.customer.CustomerDao;
import com.sun.mail.util.BASE64EncoderStream;

public class DefaultCustomerService implements CustomerService {

	@Override
	public boolean register(Customer customer) {
		customer.setStatus(Customer.Status.NEW.name());
		String regNo = UUID.randomUUID().toString();
		String initPass = Utilities.randomPass(9);
		customer.addContact(new Contact(ContactType.REGNO, regNo));
		try {
			getCustomerDao().findByEmail(customer.getEmail());
			return false;
		} catch (NoResultException e) {
			getCustomerDao().store(customer);
			// Send Mail
			sendRegMail(customer, initPass);
			return true;
		}
	}

	@Override
	public void confirm(String uuid,String initPass) {
		// TODO Auto-generated method stub
		Customer customer = getCustomerDao().findByRegId(uuid);
		customer.setStatus(Customer.Status.VALID.name());
		customer.removeContact(customer.getContact(ContactType.REGNO));
		getUserService().register("website", customer.getEmail(), initPass);
	}

	public void sendRegMail(Customer customer, String initPass) {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("name", customer.getContact(ContactType.PERSON).getValue());
		context.put("uuid", customer.getContact(ContactType.REGNO).getValue());
		context.put("website", "http://www.kooobao.cn/");
		context.put("initPass", initPass);
		context.put("base64initpass", new String(BASE64EncoderStream.encode(initPass.getBytes())));
		TemplateMailMessage message = new TemplateMailMessage(
				"/com/kooobao/cws/service/customer/reg_mail.vm", context);
		message.setSubject("酷宝原版少儿英语-注册确认邮件");
		message.setFrom("info@kooobao.cn");
		message.setTo(new String[] { customer.getContact(ContactType.EMAIL)
				.getValue() });

		getMailSender().send(message);
	}

	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	private JavaMailSender mailSender;

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
