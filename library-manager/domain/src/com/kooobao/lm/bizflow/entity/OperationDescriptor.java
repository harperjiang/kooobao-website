package com.kooobao.lm.bizflow.entity;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class OperationDescriptor {

	static Properties prop;
	static {
		try {
			prop = new Properties();
			prop.load(OperationDescriptor.class
					.getResourceAsStream("/com/kooobao/lm/bizflow/entity/OperationDescriptor.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String describe(Operation operation) {
		String fromState = operation.getFromState();
		String toState = operation.getToState();
		String key = String.valueOf(fromState) + "+" + String.valueOf(toState);
		if (!StringUtils.isEmpty(operation.getComment())) {
			key += ".comment";
		}
		String value = prop.getProperty(key);
		if (null == value)
			value = prop.getProperty(toState);
		value = MessageFormat.format(value, operation.getComment() == null ? ""
				: operation.getComment());
		return value;
	}

}
