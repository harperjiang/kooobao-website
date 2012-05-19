package com.kooobao.lm.profile;

import com.kooobao.lm.profile.dao.PersonalInfoDao;
import com.kooobao.lm.profile.dao.VisitorDao;
import com.kooobao.lm.profile.entity.PersonalInfo;
import com.kooobao.lm.profile.entity.Visitor;

public class DefaultProfileService implements ProfileService {

	public Visitor getVisitor(String id) {
		return getVisitorDao().find(id);
	}

	public Visitor saveVisitor(Visitor visitor) {
		return getVisitorDao().store(visitor);
	}

	public PersonalInfo getPersonalInfo(Visitor visitor) {
		return getPersonalInfoDao().findByVisitor(visitor);
	}

	public PersonalInfo savePersonalInfo(PersonalInfo personalInfo) {
		return getPersonalInfoDao().store(personalInfo);
	}

	private VisitorDao visitorDao;

	private PersonalInfoDao personalInfoDao;

	public VisitorDao getVisitorDao() {
		return visitorDao;
	}

	public void setVisitorDao(VisitorDao visitorDao) {
		this.visitorDao = visitorDao;
	}

	public PersonalInfoDao getPersonalInfoDao() {
		return personalInfoDao;
	}

	public void setPersonalInfoDao(PersonalInfoDao personalInfoDao) {
		this.personalInfoDao = personalInfoDao;
	}

}
