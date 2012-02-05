package com.kooobao.fr.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("PAYMENT")
public class PaymentRecord extends FinancialRecord {

	public PaymentRecord() {
		super();
		setStatus(RecordStatus.PAYMENT_SUBMIT);
	}
	
	public void approve() {
		
	}
	
	public void pay() {
		
	}
}
