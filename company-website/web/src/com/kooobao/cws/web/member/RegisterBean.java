package com.kooobao.cws.web.member;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFLifecycleAware;
import com.kooobao.cws.domain.customer.Contact;
import com.kooobao.cws.domain.customer.ContactType;
import com.kooobao.cws.domain.customer.Customer;
import com.kooobao.cws.service.customer.CustomerService;

public class RegisterBean extends AbstractBean implements JSFLifecycleAware {

	@Override
	public void onPageLoad() {

	}

	public String register() {
		Customer customer = new Customer();
		customer.setEmail(getEmail());
		customer.setCompanyUser("i".equals(memberType));
		customer.addContact(new Contact(ContactType.PERSON, getName()));
		customer.addContact(new Contact(ContactType.EMAIL, getEmail()));
		// Register Customer
		if (getCustomerService().register(customer))
			return "success";
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Duplicate Email Address", "Duplicate Email Address"));
		return "failed";
	}

	private String memberType;

	private String name;

	private String email;

	private boolean receiveUpdate = true;

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getReceiveUpdate() {
		return receiveUpdate;
	}

	public void setReceiveUpdate(boolean receiveUpdate) {
		this.receiveUpdate = receiveUpdate;
	}

	private CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
