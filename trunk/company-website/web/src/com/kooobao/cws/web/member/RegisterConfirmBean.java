package com.kooobao.cws.web.member;

import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.JSFLifecycleAware;
import com.kooobao.cws.service.customer.CustomerService;

public class RegisterConfirmBean extends AbstractBean implements
		JSFLifecycleAware {

	boolean success;

	@Override
	public void onPageLoad() {
		super.onPageLoad();
		String uuid = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("uuid");
		String initpass = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestParameterMap().get("initpass");
		try {
			getCustomerService().confirm(uuid,initpass);
			success = true;
		} catch (Exception e) {

		}
	}

	public String update() {
		return "success";
	}

	private CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

}
