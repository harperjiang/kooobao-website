package com.kooobao.fr.web.report;

import java.math.BigDecimal;
import java.util.Date;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.fr.service.ReportService;
import com.kooobao.fr.service.ReportService.ReportResult;

public class ViewReportBean extends AbstractBean {

	private BigDecimal income;

	private BigDecimal expense;

	private BigDecimal profit;

	private Date beginDate = Utilities.offset(Utilities.dayBegin(), -7);

	private Date endDate = Utilities.dayEnd();

	public String report() {
		ReportResult result = getReportService().getReport(
				Utilities.dayBegin(getBeginDate()),
				Utilities.dayEnd(getEndDate()));
		setIncome(result.getIncome());
		setExpense(result.getExpense());
		setProfit(result.getProfit());
		return "success";
	}

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

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	private ReportService reportService;

	public ReportService getReportService() {
		return reportService;
	}

	public void setReportService(ReportService reportService) {
		this.reportService = reportService;
	}

}
