package com.kooobao.fr.domain.entity;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PAYMENT")
public class PaymentRecord extends FinancialRecord {

	public PaymentRecord() {
		super();
		setStatus(RecordStatus.PAYMENT_SUBMIT);
	}

	public void approve(String operator, String reason) {
		transit(RecordStatus.PAYMENT_SUBMIT, RecordStatus.PAYMENT_APPROVED,
				operator, reason);
	}

	public void reject(String operator, String reason) {
		transit(RecordStatus.PAYMENT_SUBMIT, RecordStatus.PAYMENT_REJECT,
				operator, reason);
	}

	public void confirm(BigDecimal commission, String operator, String reason) {
		transit(RecordStatus.PAYMENT_APPROVED, RecordStatus.PAYMENT_PAID,
				operator, reason);
		setAdjustAmount(commission);
	}

	public void failToPay(String operator, String reason) {
		transit(RecordStatus.PAYMENT_APPROVED, RecordStatus.PAYMENT_FAILED,
				operator, reason);
	}

	public void cancel(String operator, String reason) {
		if (getStatus().equals(RecordStatus.PAYMENT_SUBMIT.name()))
			transit(RecordStatus.PAYMENT_SUBMIT, RecordStatus.PAYMENT_CANCEL,
					operator, reason);
		else if (getStatus().equals(RecordStatus.PAYMENT_APPROVED.name()))
			transit(RecordStatus.PAYMENT_APPROVED, RecordStatus.PAYMENT_CANCEL,
					operator, reason);
	}
}
