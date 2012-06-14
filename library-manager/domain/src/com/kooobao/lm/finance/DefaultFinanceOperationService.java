package com.kooobao.lm.finance;

import java.math.BigDecimal;

import com.kooobao.lm.finance.dao.FinanceOperationDao;
import com.kooobao.lm.profile.entity.Visitor;

public class DefaultFinanceOperationService implements FinanceOperationService {

	public void changeVisitorDeposit(Visitor v, BigDecimal change,
			String comment, String operatorId) {
		getFinanceOperationDao().changeVisitorDeposit(v, change, comment,
				operatorId);
	}

	private FinanceOperationDao financeOperationDao;

	public FinanceOperationDao getFinanceOperationDao() {
		return financeOperationDao;
	}

	public void setFinanceOperationDao(FinanceOperationDao financeOperationDao) {
		this.financeOperationDao = financeOperationDao;
	}

}
