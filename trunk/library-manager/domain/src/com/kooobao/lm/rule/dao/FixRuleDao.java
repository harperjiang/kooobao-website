package com.kooobao.lm.rule.dao;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.common.domain.entity.SimpleEntity;
import com.kooobao.lm.rule.entity.DueRule;
import com.kooobao.lm.rule.entity.PenaltyRule;
import com.kooobao.lm.rule.entity.VisitorLevelRule;

public class FixRuleDao implements RuleDao {

	public PenaltyRule getPenaltyRule() {
		return new PenaltyRule();
	}

	public DueRule getDueRule() {
		return new DueRule();
	}

	public SimpleEntity store(SimpleEntity entity) {
		throw new UnsupportedOperationException();
	}

	public SimpleEntity remove(SimpleEntity entity) {
		throw new UnsupportedOperationException();
	}

	public SimpleEntity find(long oid) {
		throw new UnsupportedOperationException();
	}

	public SimpleEntity find(SimpleEntity example) {
		throw new UnsupportedOperationException();
	}

	public Cursor<SimpleEntity> findAll() {
		throw new UnsupportedOperationException();
	}

	public VisitorLevelRule getVisitorLevelRule() {
		return new VisitorLevelRule();
	}
}
