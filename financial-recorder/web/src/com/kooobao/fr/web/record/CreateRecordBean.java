package com.kooobao.fr.web.record;

import javax.faces.component.UIData;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.fr.domain.entity.Customer;
import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;
import com.kooobao.fr.service.FinancialRecordService;

public class CreateRecordBean extends AbstractBean {

	private FinancialRecord record;

	public boolean isPayment() {
		return getRecord() instanceof PaymentRecord;
	}

	public FinancialRecord getRecord() {
		if (null == record)
			record = new PaymentRecord();
		return record;
	}

	public void setRecord(FinancialRecord record) {
		this.record = record;
	}

	private FinancialRecordService financialRecordService;

	public FinancialRecordService getFinancialRecordService() {
		return financialRecordService;
	}

	public void setFinancialRecordService(
			FinancialRecordService financialRecordService) {
		this.financialRecordService = financialRecordService;
	}

	public String selectCustomer() {
		UIData dataTable = (UIData) getComponent("customerTable");
		Customer select = (Customer) dataTable.getRowData();
		getRecord().getWith().fromCustomer(select);
		return "success";
	}

	public String save() {
		setRecord(getFinancialRecordService().save(getRecord()));
		return "success";
	}
}
