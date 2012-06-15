package com.kooobao.lm.profile;

import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;

public class LoginBean extends com.kooobao.authcenter.web.bean.LoginBean {

	@Override
	protected boolean verify(String id) {
		Visitor v = getProfileService().getVisitor(getUserId());
		return v != null
				&& !VisitorStatus.INACTIVE.name().equals(v.getStatus());
	}

	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

}
