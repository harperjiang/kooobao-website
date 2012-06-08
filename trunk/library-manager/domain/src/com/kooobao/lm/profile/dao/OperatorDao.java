package com.kooobao.lm.profile.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.profile.entity.Operator;

public interface OperatorDao extends Dao<Operator> {

	Operator find(String id);
}
