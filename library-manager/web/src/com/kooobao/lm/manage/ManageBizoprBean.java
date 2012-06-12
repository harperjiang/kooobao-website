package com.kooobao.lm.manage;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.bizflow.BusinessService;

@ManagedBean(name = "manageBizoprBean")
@SessionScoped
public class ManageBizoprBean extends AbstractBean {

	public String updateBookRating() {
		getBusinessService().updateBookRating(
				Utilities.offset(Utilities.dayBegin(new Date()), -180));
		addMessage(FacesMessage.SEVERITY_INFO,"操作已成功");
		return "success";
	}

	@ManagedProperty("#{businessService}")
	private BusinessService businessService;

	public BusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = businessService;
	}

}
