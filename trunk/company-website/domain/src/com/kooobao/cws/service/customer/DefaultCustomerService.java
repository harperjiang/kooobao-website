package com.kooobao.cws.service.customer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.mail.javamail.JavaMailSender;

import com.kooobao.common.web.email.TemplateMailMessage;
import com.kooobao.cws.domain.customer.Contact;
import com.kooobao.cws.domain.customer.ContactType;
import com.kooobao.cws.domain.customer.Customer;
import com.kooobao.cws.domain.customer.CustomerDao;

public class DefaultCustomerService implements CustomerService {

	@Override
	public void register(Customer customer) {
		String regNo = UUID.randomUUID().toString();
		customer.addContact(new Contact(ContactType.REGNO,regNo));
		getCustomerDao().store(customer);
		// Send Mail
		sendRegMail(customer);
	}

	@Override
	public void confirm(String uuid) {
		// TODO Auto-generated method stub

	}
	
	public void sendRegMail(Customer customer) {
		Map<String,Object> context = new HashMap<String,Object>();
		context.put("name", customer.getContact(ContactType.PERSON).getValue());
		context.put("uuid", customer.getContact(ContactType.REGNO).getValue());
		context.put("website", "http://www.kooobao.cn/");
		TemplateMailMessage message = new TemplateMailMessage("/com/kooobao/cws/service/customer/reg_mail.vm",context);
		message.setSubject("酷宝原版少儿英语-注册确认邮件");
		message.setFrom("info@kooobao.cn");
		message.setTo(new String[]{customer.getContact(ContactType.EMAIL).getValue()});
		
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

}
