package com.kooobao.lm.profile.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Visitor;

public interface VisitorDao extends Dao<Visitor> {

	Visitor find(String id);

	void store(ActivationRecord actRecord);

	ActivationRecord getActivationRecord(String uuid);

	void removeActivationRecord(ActivationRecord actr);
}
