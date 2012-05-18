package com.kooobao.lm.profile;

import com.kooobao.lm.profile.entity.PersonalInfo;
import com.kooobao.lm.profile.entity.Visitor;

public interface ProfileService {

	Visitor getVisitor(String id);

	Visitor saveVisitor(Visitor visitor);

	PersonalInfo getPersonalInfo(Visitor visitor);

	PersonalInfo savePersonalInfo(PersonalInfo personalInfo);

}
