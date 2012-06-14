package com.kooobao.lm.finance.dao;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.Visitor;

public interface FinanceOperationDao {

	public void changeVisitorDeposit(Visitor v, BigDecimal deposit,
			String reason, String operatorId);
}
