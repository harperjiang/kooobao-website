package com.kooobao.ecom.crm.customer;

public class InvalidChangeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2971385649672537785L;

	public InvalidChangeException(String msg) {
		super(msg);
	}

	public InvalidChangeException(Exception e) {
		super(e);
	}

	public InvalidChangeException(String msg, Exception e) {
		super(msg, e);
	}
}
