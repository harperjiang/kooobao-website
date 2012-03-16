package com.kooobao.fr.service;

import java.math.BigDecimal;
import java.util.Date;

public interface ReportService {

	public static class ReportResult {
		private BigDecimal income;

		private BigDecimal expense;

		private BigDecimal profit;

		public BigDecimal getIncome() {
			return income;
		}

		public void setIncome(BigDecimal income) {
			this.income = income;
		}

		public BigDecimal getExpense() {
			return expense;
		}

		public void setExpense(BigDecimal expense) {
			this.expense = expense;
		}

		public BigDecimal getProfit() {
			return profit;
		}

		public void setProfit(BigDecimal profit) {
			this.profit = profit;
		}
	}

	public ReportResult getReport(Date begin, Date end);
}
