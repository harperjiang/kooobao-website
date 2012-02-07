package com.kooobao.fr.web.record;

import com.kooobao.fr.domain.entity.FinancialRecord;
import com.kooobao.fr.domain.entity.PaymentRecord;

public class FinancialRecordBean {

	private String oid;

	private String type;

	private String amount;

	private String with;

	private String createBy;

	private String followup;

	public void from(FinancialRecord record) {
		setOid(String.valueOf(record.getOid()));
		if (record instanceof PaymentRecord)
			setType("付款");
		else
			setType("收款");
		setAmount(record.getAmount().toString());
		setWith(record.getWith().getName() + " "
				+ record.getWith().getCompany());
		setCreateBy(record.getCreateBy());
		setFollowup(record.getFollowup());
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getWith() {
		return with;
	}

	public void setWith(String with) {
		this.with = with;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getFollowup() {
		return followup;
	}

	public void setFollowup(String followup) {
		this.followup = followup;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

}
