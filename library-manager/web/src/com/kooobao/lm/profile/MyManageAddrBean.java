package com.kooobao.lm.profile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean(name = "myManageAddrBean")
@SessionScoped
public class MyManageAddrBean extends AbstractBean {

	private Visitor visitor;

	private Address newAddr = new Address();

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public void onPageLoad() {
		MyIndexBean myIndexBean = findBean("myIndexBean");
		visitor = myIndexBean.getVisitor();
	}

	public String delete() {
		try {
			long addrId = Long.parseLong(getParameter("addr_id"));
			if (addrId == getVisitor().getAddress().getOid()) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("不能删除默认地址"));
				return "success";
			}
			for (Address addr : getVisitor().getAvailableAddresses()) {
				if (addr.getOid() == addrId) {
					getVisitor().getAvailableAddresses().remove(addr);
					break;
				}
			}
		} catch (Exception e) {
			return "failed";
		}
		visitor = getProfileService().saveVisitor(visitor);
		return "success";
	}

	public String setAsDefault() {
		try {
			long addrId = Long.parseLong(getParameter("addr_id"));
			if (addrId == getVisitor().getAddress().getOid()) {
				return "success";
			}
			for (Address addr : getVisitor().getAvailableAddresses()) {
				if (addr.getOid() == addrId) {
					getVisitor().setAddress(addr);
					break;
				}
			}
		} catch (Exception e) {
			return "failed";
		}
		visitor = getProfileService().saveVisitor(visitor);
		return "success";
	}

	public String save() {
		getVisitor().getAvailableAddresses().add(newAddr);
		visitor = getProfileService().saveVisitor(visitor);
		newAddr = new Address();
		return "success";
	}

	public Address getNewAddr() {
		return newAddr;
	}

	public void setNewAddr(Address newAddr) {
		this.newAddr = newAddr;
	}

	@ManagedProperty("#{profileService}")
	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

}
