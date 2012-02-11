package com.kooobao.fr.web.record;

import java.math.BigDecimal;

import com.kooobao.common.web.bean.AbstractBean;
import com.kooobao.fr.domain.entity.Actor;
import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;
import com.kooobao.fr.service.FinancialRecordService;
import com.kooobao.fr.web.actor.ActorInfoBean;
import com.kooobao.fr.web.dummy.DummyFinancialRecordService;

public class ViewRecordBean extends AbstractBean {

	private FinancialRecord record;

	public boolean isPayment() {
		return getRecord() instanceof PaymentRecord;
	}

	public FinancialRecord getRecord() {
		if (null == record) {
			record = new DummyFinancialRecordService().createDummyRecord();
		}
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

	private String comment;

	private BigDecimal commission;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	protected Actor getActor() {
		ActorInfoBean actorInfoBean = findBean("actorInfoBean");
		if (null == actorInfoBean)
			return null;
		return actorInfoBean.getActor();
	}

	public String approvePayment() {
		setRecord(getFinancialRecordService().approvePayment(getRecord(),
				getActor(), comment));
		return "success";
	}

	public String rejectPayment() {
		setRecord(getFinancialRecordService().rejectPayment(getRecord(),
				getActor(), comment));
		return "success";
	}

	public String confirmPayment() {
		setRecord(getFinancialRecordService().payPayment(getRecord(),
				getActor(), commission, comment));
		return "success";
	}

	public String failedPayment() {
		setRecord(getFinancialRecordService().failPayment(getRecord(),
				getActor(), comment));
		return "success";
	}

	public String cancelPayment() {
		setRecord(getFinancialRecordService().cancelPayment(getRecord(),
				getActor(), comment));
		return "success";
	}

	public String confirmReceive() {
		setRecord(getFinancialRecordService().confirmReceive(getRecord(),
				getActor(), comment));
		return "success";
	}

	public String cancelReceive() {
		setRecord(getFinancialRecordService().cancelReceive(getRecord(),
				getActor(), comment));
		return "success";
	}

}