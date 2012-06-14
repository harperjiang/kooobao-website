package com.kooobao.lm.finance;

import java.math.BigDecimal;

import com.kooobao.lm.profile.entity.Visitor;

public interface FinanceOperationService {

	void changeVisitorDeposit(Visitor v, BigDecimal change, String comment,
			String operatorId);
}
