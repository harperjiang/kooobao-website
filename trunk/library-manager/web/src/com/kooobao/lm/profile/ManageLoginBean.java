package com.kooobao.lm.profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.lm.profile.entity.Operator;

@ManagedBean(name = "manageLoginBean")
@SessionScoped
public class ManageLoginBean extends LoginBean {

	@Override
	protected String getSystem() {
		return "lm_manage";
	}

	@Override
	protected boolean verify(String userId) {
		Operator o = getProfileService().getOperator(userId);
		return null != o;
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
