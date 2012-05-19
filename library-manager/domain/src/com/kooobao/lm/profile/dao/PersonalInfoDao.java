package com.kooobao.lm.profile.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.profile.entity.PersonalInfo;
import com.kooobao.lm.profile.entity.Visitor;

public interface PersonalInfoDao extends Dao<PersonalInfo> {

	PersonalInfo findByVisitor(Visitor v);
}
