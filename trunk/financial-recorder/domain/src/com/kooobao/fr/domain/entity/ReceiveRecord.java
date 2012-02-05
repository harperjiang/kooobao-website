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
	
	public void confirm() {
		
	}
}
