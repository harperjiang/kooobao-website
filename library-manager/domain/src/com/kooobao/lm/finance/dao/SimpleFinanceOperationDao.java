package com.kooobao.lm.finance.dao;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.lm.profile.entity.BalanceLog;
import com.kooobao.lm.profile.entity.Visitor;

public class SimpleFinanceOperationDao implements FinanceOperationDao {

	public void changeVisitorDeposit(Visitor v, BigDecimal deposit,
			String reason, String operatorId) {
		v.setDeposit(v.getDeposit().add(deposit));
		BalanceLog bl = new BalanceLog();
		bl.setVisitor(v);
		bl.setCreateTime(new Date());
		bl.setChange(deposit);
		bl.setReason(reason);
		bl.setOperatorId(operatorId);
		v.getBalanceLogs().add(bl);
	}

}
