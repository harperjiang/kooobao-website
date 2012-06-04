package com.kooobao.lm.profile;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.lm.profile.entity.PersonalInfo;
import com.kooobao.lm.profile.entity.Visitor;

@ManagedBean(name = "myPersonBean")
@SessionScoped
public class MyPersonBean extends AbstractBean {

	public void onPageLoad() {
		MyIndexBean myInfoBean = findBean("myIndexBean");
		this.personalInfo = myInfoBean.getVisitor().getInfo();
		if (null == personalInfo)
			personalInfo = new PersonalInfo();
		for (int i = 0; i < 6; i++) {
			if ((personalInfo.getLike() ^ (1 << i)) > 0)
				likes.add(String.valueOf((char) ('A' + i)));
		}
	}

	public String save() {
		int like = 0;
		for (String likeStr : likes) {
			like |= likeStr.charAt(0) - 'A';
		}
		personalInfo.setLike(like);
		MyIndexBean myInfoBean = findBean("myIndexBean");
		Visitor v = myInfoBean.getVisitor();
		v.setInfo(personalInfo);
		personalInfo = getProfileService().saveVisitor(v).getInfo();
		addDialogMessage("defaultDialog", FacesMessage.SEVERITY_INFO, "提示",
				"您的信息已经保存!");
		return "success";
	}

	private List<String> likes = new ArrayList<String>();

	private PersonalInfo personalInfo = new PersonalInfo();

	public List<String> getLikes() {
		return likes;
	}

	public void setLikes(List<String> likes) {
		this.likes = likes;
	}

	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}

	@ManagedProperty("#{profileService}")
	private ProfileService profileService;

	public ProfileService getProfileService() {
		return profileService;
	}

	public void setProfileService(ProfileService profileService) {
		this.profileService = profileService;
	}

	private List<String> yearList;

	public List<String> getYearList() {
		return yearList;
	}

	{
		yearList = new ArrayList<String>();
		for (int i = 1950; i < 2012; i++)
			yearList.add(String.valueOf(i));
	}

}
