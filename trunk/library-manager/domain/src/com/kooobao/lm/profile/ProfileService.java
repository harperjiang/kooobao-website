package com.kooobao.lm.profile;

public interface ProfileService {

	Visitor getVisitor(String id);

	Visitor saveVisitor(Visitor visitor);

	PersonalInfo getPersonalInfo(Visitor visitor);

	PersonalInfo savePersonalInfo(PersonalInfo personalInfo);

}
