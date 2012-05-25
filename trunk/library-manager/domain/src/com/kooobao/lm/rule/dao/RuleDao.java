package com.kooobao.lm.rule.dao;

import com.kooobao.common.domain.dao.Dao;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.rule.entity.DeliveryDayRule;
import com.kooobao.lm.rule.entity.DeliveryFeeRule;
import com.kooobao.lm.rule.entity.DueRule;
import com.kooobao.lm.rule.entity.PenaltyRule;
import com.kooobao.lm.rule.entity.VisitorLevelRule;

public interface RuleDao extends Dao<SimpleEntity> {

	PenaltyRule getPenaltyRule();

	DueRule getDueRule();

	VisitorLevelRule getVisitorLevelRule();

	DeliveryFeeRule getDeliveryFeeRule();

	DeliveryDayRule getDeliveryDayRule();

	
}
