package com.kooobao.wsm.domain.entity.issue;

import java.util.ResourceBundle;

public enum Severity {

	URGENT, HIGH, NORMAL, LOW;

	public String text() {
		ResourceBundle rb = ResourceBundle.getBundle(getClass().getName());
		return rb.getString(name());
	}
}
