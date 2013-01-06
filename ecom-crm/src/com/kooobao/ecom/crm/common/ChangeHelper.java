package com.kooobao.ecom.crm.common;

import java.text.MessageFormat;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.Validate;

import com.kooobao.common.domain.entity.SimpleEntity;

public class ChangeHelper {

	public static void checkInvalidChange(Object oldInst, Object newInst,
			String[] attributes) {
		for (int i = 0; i < attributes.length; i += 2) {
			String attribute = attributes[i];
			String errorCode = attributes[i + 1];
			try {
				Object oldval = BeanUtils.getProperty(oldInst, attribute);
				Object newval = BeanUtils.getProperty(newInst, attribute);
				Validate.isTrue(SimpleEntity.equals(oldval, newval), errorCode);
			} catch (IllegalArgumentException e) {
				throw e;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	public static String generateChange(Object oldInst, Object newInst,
			String[] attributes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < attributes.length; i += 2) {
			String attribute = attributes[i];
			String pattern = attributes[i + 1];
			try {
				Object oldval = BeanUtils.getProperty(oldInst, attribute);
				Object newval = BeanUtils.getProperty(newInst, attribute);
				if (!SimpleEntity.equals(oldval, newval)) {
					sb.append(MessageFormat.format(pattern, oldval, newval));
					sb.append("\n");
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		if (sb.length() != 0)
			return sb.toString();
		return null;
	}
}
