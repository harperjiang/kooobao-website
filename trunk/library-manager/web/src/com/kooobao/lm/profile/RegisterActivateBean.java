package com.kooobao.lm.profile;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import com.kooobao.common.web.bean.AbstractBean;

@ManagedBean(name = "registerActivateBean")
@SessionScoped
public class RegisterActivateBean extends AbstractBean {

	@Override
	public void onPageLoad() {
		String activateId = getParameter("activate_id");
		if (StringUtils.isEmpty(activateId))
			activated = false;
		activated = getProfileService().activateUser(activateId);
	}

	private boolean activated;

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
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
