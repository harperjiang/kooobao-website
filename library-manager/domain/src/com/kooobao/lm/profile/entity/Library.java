package com.kooobao.lm.profile.entity;

public class Library {

	private String name;

	private String status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static enum Status {
		VALID, CLOSED;
	}
}
