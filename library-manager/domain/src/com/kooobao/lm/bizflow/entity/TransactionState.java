package com.kooobao.lm.bizflow.entity;

public enum TransactionState {

	BORROW_REQUESTED, 
	BORROW_APPROVED, 
	BORROW_SENT, 
	RETURN_WAIT, 
	RETURN_SENT, 
	RETURN_EXPIRED, 
	RETURN_RECEIVED, 
	CANCELLED, INTERRUPTED
}
