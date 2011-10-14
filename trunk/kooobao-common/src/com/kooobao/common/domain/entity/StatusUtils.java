package com.kooobao.common.domain.entity;

import java.util.ResourceBundle;

public class StatusUtils {
	public static <E extends Enum> String text(E anums) {
		ResourceBundle rb = ResourceBundle
				.getBundle(anums.getClass().getName());
		return rb.getString(anums.name());
	}
}
