package com.kooobao.lm.profile;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.PersonalInfo;
import com.kooobao.lm.profile.entity.Visitor;

public interface ProfileService {

	Visitor getVisitor(String id);

	Visitor saveVisitor(Visitor visitor);

	void redeem(Visitor visitor, BigDecimal amount);

	PersonalInfo getPersonalInfo(Visitor visitor);

	PersonalInfo savePersonalInfo(Visitor visitor, PersonalInfo personalInfo);

	boolean activateUser(String activateId);

	void register(String email, String password);

	void clearInactivateVisitors();
}
