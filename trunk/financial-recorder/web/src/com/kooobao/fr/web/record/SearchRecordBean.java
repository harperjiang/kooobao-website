package com.kooobao.fr.web.record;

import java.util.Date;
import java.util.List;

import javax.faces.component.UIData;

import com.kooobao.common.web.Utilities;
import com.kooobao.common.web.bean.PageSearchBean;
import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.service.FinancialRecordService;

public class SearchRecordBean extends PageSearchBean {

	private Date fromDate = Utilities.offset(Utilities.dayBegin(), -7);

	private Date toDate = Utilities.dayEnd();

	private String status;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String search() {
		PageSearchResult<FinancialRecord> result = getFinancialRecordService()
				.search(Utilities.dayBegin(getFromDate()), Utilities.dayEnd(getToDate()),
						new String[] { getStatus() }, getRecordStart(),
						getRecordStop());
		setRecords(result.getResult());
		setRecordCount(result.getCount());
		return "success";
	}

	public String view() {
		UIData dataTable = (UIData) getComponent("resultDataTable");
		FinancialRecord select = (FinancialRecord) dataTable.getRowData();
		ViewRecordBean viewRecordBean = findBean("viewRecordBean");
		// .setIssueOid(select.getOid());
		viewRecordBean.setRecord(select);
		return "success";
	}

	private FinancialRecordService financialRecordService;

	public FinancialRecordService getFinancialRecordService() {
		return financialRecordService;
	}

	public void setFinancialRecordService(
			FinancialRecordService financialRecordService) {
		this.financialRecordService = financialRecordService;
	}

	private List<FinancialRecord> records;

	public List<FinancialRecord> getRecords() {
		return records;
	}

	public void setRecords(List<FinancialRecord> records) {
		this.records = records;
	}
}
