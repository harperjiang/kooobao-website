package com.kooobao.fr.web.record;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIData;

import com.kooobao.authcenter.web.bean.LoginBean;
import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;
import com.kooobao.fr.domain.entity.ReceiveRecord;
import com.kooobao.fr.domain.entity.RecordStatus;
import com.kooobao.fr.service.FinancialRecordService;
import com.kooobao.fr.web.actor.ActorInfoBean;

public class FinancialInfoBean extends AbstractBean {

	private FinancialRecordService financialRecordService;

	public List<FinancialRecordBean> translate(List<FinancialRecord> input) {
		List<FinancialRecordBean> result = new ArrayList<FinancialRecordBean>();
		for (FinancialRecord record : input) {
			FinancialRecordBean bean = new FinancialRecordBean();
			bean.from(record);
			result.add(bean);
		}
		return result;
	}

	public int getMyFollowupCount() {
		return getFinancialRecordService().getMyFollowupCount(
				LoginBean.getCurrentUser());
	}

	public int getMyRecordCount() {
		return getFinancialRecordService().getMyRecordCount(
				LoginBean.getCurrentUser());
	}

	public List<FinancialRecordBean> getMyFollowups() {
		return translate(getFinancialRecordService().getMyFollowups(
				LoginBean.getCurrentUser(), 8));
	}

	public List<FinancialRecordBean> getMyRecords() {
		return translate(getFinancialRecordService().getMyRecords(
				LoginBean.getCurrentUser(), 8));
	}

	public String view() {
		UIData dataTable = null;
		ActorInfoBean actorInfoBean = findBean("actorInfoBean");
		if (actorInfoBean.isOperator()) {
			dataTable = (UIData) getComponent("myOperationDataTable");
		} else {
			dataTable = (UIData) getComponent("myFollowupDataTable");
		}
		FinancialRecordBean select = (FinancialRecordBean) dataTable
				.getRowData();
		ViewRecordBean viewRecordBean = findBean("viewRecordBean");
		// .setIssueOid(select.getOid());
		viewRecordBean.setRecord(select.getRecord());
		return "success";
	}

	public String requestPayment() {
		CreateRecordBean createRecordBean = findBean("createRecordBean");
		createRecordBean.setRecord(new PaymentRecord());
		return "success";
	}

	public String recordReceive() {
		CreateRecordBean viewRecordBean = findBean("createRecordBean");
		viewRecordBean.setRecord(new ReceiveRecord());
		return "success";
	}

	public String searchRecords() {
		SearchRecordBean searchRecordBean = findBean("searchRecordBean");
		searchRecordBean.setStatus(RecordStatus.PAYMENT_SUBMIT.name());
		return "success";
	}

	public FinancialRecordService getFinancialRecordService() {
		return financialRecordService;
	}

	public void setFinancialRecordService(
			FinancialRecordService financialRecordService) {
		this.financialRecordService = financialRecordService;
	}

}
