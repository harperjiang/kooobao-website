package com.kooobao.fr.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("RECEIVE")
public class ReceiveRecord extends FinancialRecord {

	public ReceiveRecord() {
		super();
		setStatus(RecordStatus.RECEIVE_SUBMIT);
	}

	public void confirm(String operator, String reason) {
		transit(RecordStatus.RECEIVE_SUBMIT, RecordStatus.RECEIVE_CONFIRMED,
				operator, reason);
	}

	public void cancel(String operator, String reason) {
		if (getStatus().equals(RecordStatus.RECEIVE_SUBMIT.name()))
			transit(RecordStatus.RECEIVE_SUBMIT, RecordStatus.RECEIVE_CANCEL,
					operator, reason);
	}
}
