package com.kooobao.fr.service;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.fr.domain.dao.FinancialRecordDao;
import com.kooobao.fr.domain.entity.RecordStatus;

public class DefaultReportService implements ReportService {

	public ReportResult getReport(Date begin, Date end) {
		BigDecimal income = getFinancialRecordDao().getSumAmount(begin, end,
				RecordStatus.RECEIVE_CONFIRMED.name());
		BigDecimal expense = getFinancialRecordDao().getSumAmount(begin, end,
				RecordStatus.PAYMENT_PAID.name());
		ReportResult result = new ReportResult();
		income = null == income ? BigDecimal.ZERO : income;
		expense = null == expense ? BigDecimal.ZERO : expense;
		result.setIncome(income);
		result.setExpense(expense);

		result.setProfit(income.subtract(expense));
		return result;
	}

	private FinancialRecordDao financialRecordDao;

	public FinancialRecordDao getFinancialRecordDao() {
		return financialRecordDao;
	}

	public void setFinancialRecordDao(FinancialRecordDao financialRecordDao) {
		this.financialRecordDao = financialRecordDao;
	}

}
