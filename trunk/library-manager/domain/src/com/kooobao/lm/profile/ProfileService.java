package com.kooobao.lm.profile;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorType;

public interface ProfileService {

	Visitor getVisitor(String id);

	Visitor saveVisitor(Visitor visitor);

	void redeem(Visitor visitor, BigDecimal amount);

	Visitor activateUser(String activateId);

	void register(String email, String password, VisitorType type, String recommendBy);
	
	Operator getOperator(String id);
}
