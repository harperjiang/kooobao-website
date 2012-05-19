package com.kooobao.lm.profile.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.profile.entity.Visitor;

public interface VisitorDao extends Dao<Visitor> {

	public Visitor find(String id);
}
