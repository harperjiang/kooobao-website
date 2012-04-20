package com.kooobao.cws.domain.customer;

public class Contact {

	private ContactType type;

	private String value;

	public ContactType getType() {
		return type;
	}

	public void setType(ContactType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
