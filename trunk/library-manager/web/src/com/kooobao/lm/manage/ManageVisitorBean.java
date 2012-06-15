package com.kooobao.lm.manage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.Validate;

import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.finance.FinanceOperationService;
import com.kooobao.lm.profile.ManageLoginBean;
import com.kooobao.lm.profile.ProfileService;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.profile.entity.VisitorType;

@ManagedBean(name = "manageVisitorBean")
@SessionScoped
public class ManageVisitorBean extends PageSearchBean {

	@Override
	public String search() {
		PageSearchResult<Visitor> result = getProfileService().searchVisitor(
				getVisitorId(), getVisitorStatus());
		setRecordCount(result.getCount());
		setVisitors(result.getResult());
		return "success";
	}

	public String modifyBalance() {
		Visitor v = getProfileService().getVisitor(getParameter("visitor_id"));
		getFinanceOperationService().changeVisitorDeposit(v, getBalance(),
				getReason(), getOperator().getId());
		getProfileService().saveVisitor(v);
		return search();
	}

	public String verify() {
		Visitor v = getProfileService().getVisitor(getParameter("visitor_id"));
		Validate.isTrue(VisitorStatus.NOT_VERIFIED.name().equals(v.getStatus())
				&& v.getType() == VisitorType.INSTITUTE);
		v.setStatus(VisitorStatus.ACTIVE);
		getProfileService().saveVisitor(v);
		addMessage(FacesMessage.SEVERITY_INFO, "用户信息已验证");
		return search();
	}

	private List<Visitor> visitors = new ArrayList<Visitor>();

	public List<Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

	protected Operator getOperator() {
		ManageLoginBean mlb = findBean("manageLoginBean");
		if (mlb.isLoggedIn()) {
			return getProfileService().getOperator(mlb.getUserId());
		}
		return null;
	}

	@ManagedProperty("#{profileService}")
	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	@ManagedProperty("#{financeOperationService}")
	private FinanceOperationService financeOperationService;

	public FinanceOperationService getFinanceOperationService() {
		return financeOperationService;
	}

	public void setFinanceOperationService(
			FinanceOperationService financeOperationService) {
		this.financeOperationService = financeOperationService;
	}

	private String visitorId;

	public String getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(String visitorId) {
		this.visitorId = visitorId;
	}

	private VisitorStatus visitorStatus;

	public VisitorStatus getVisitorStatus() {
		return visitorStatus;
	}

	public void setVisitorStatus(VisitorStatus visitorStatus) {
		this.visitorStatus = visitorStatus;
	}

	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private BigDecimal balance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
