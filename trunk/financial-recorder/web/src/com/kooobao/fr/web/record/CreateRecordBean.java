package com.kooobao.fr.web.record;

import java.util.List;

import javax.faces.component.UIData;
import javax.faces.context.FacesContext;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.common.web.bean.RowIndexCounter;
import com.kooobao.common.web.fileupload.FileBean;
import com.kooobao.common.web.fileupload.MultipartRequestWrapper;
import com.kooobao.fr.domain.entity.Attachment;
import com.kooobao.fr.domain.entity.Customer;
import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;
import com.kooobao.fr.service.CustomerService;
import com.kooobao.fr.service.DefaultFinancialRecordService;
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

	private CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public List<Customer> getCustomers() {
		return getCustomerService().getRecentCustomer(5);
	}

	private RowIndexCounter counter = new RowIndexCounter();

	public RowIndexCounter getCounter() {
		return counter;
	}

	@Override
	public void onPageLoad() {
		counter.reset();
	}

	public String select() {
		System.out.println("Selected");
		return "true";
	}

	public String selectCustomer() {
		UIData dataTable = (UIData) getComponent("customerTable");
		Customer select = (Customer) dataTable.getRowData();
		getRecord().getWith().fromCustomer(select);
		return "success";
	}

	public String save() {
		MultipartRequestWrapper request = (MultipartRequestWrapper) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();

		FileBean fb = request.getFile("attachment");
		if (null != fb) {
			// Process Attachment
			Attachment attachment = DefaultFinancialRecordService
					.createAttachment(fb);
			getRecord().setAttachment(attachment);
		}
		getRecord()
				.setCreateBy(((LoginBean) findBean("loginBean")).getUserId());
		setRecord(getFinancialRecordService().create(getRecord()));
		return "success";
	}
}
