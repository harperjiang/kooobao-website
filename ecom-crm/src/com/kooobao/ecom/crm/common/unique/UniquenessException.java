package com.kooobao.ecom.crm.common.unique;

public class UniquenessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6419907590289875319L;

	public static final int DUPLICATE = 0;

	public static final int SUSPECT = 1;

	private int code;

	public UniquenessException(int code) {
		super();
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
