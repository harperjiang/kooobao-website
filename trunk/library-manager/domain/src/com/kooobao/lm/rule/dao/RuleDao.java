package com.kooobao.lm.rule.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.rule.entity.DueRule;
import com.kooobao.lm.rule.entity.PenaltyRule;

public interface RuleDao extends Dao<SimpleEntity> {

	PenaltyRule getPenaltyRule();

	DueRule getDueRule();

	
}
