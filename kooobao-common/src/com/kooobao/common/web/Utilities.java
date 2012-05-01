package com.kooobao.common.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.faces.model.SelectItem;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

public class Utilities {

	public static Date dayEnd(Date toDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(toDate);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}

	public static Date dayEnd() {
		return dayEnd(new Date());
	}

	public static Date dayBegin() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date offset(Date date, int offset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DAY_OF_YEAR, offset);
		return cal.getTime();
	}

	static final char[] PASSCHAR = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'A',
			'B', 'C', 'D', 'E', 'F', 'G', '1', '2', '3', '4', '5', '6', '7',
			'_', '!', '@', '#', '$', '%', '^', '&', '*' };

	public static String randomPass(int length) {
		Random random = new Random(System.currentTimeMillis());
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int result = random.nextInt(PASSCHAR.length);
			sb.append(PASSCHAR[result]);
		}
		return sb.toString();
	}

	public static <T> List<SelectItem> wrap(List<T> data, String attr) {
		if (CollectionUtils.isEmpty(data))
			return (List<SelectItem>) Collections.EMPTY_LIST;

		try {
			List<SelectItem> items = new ArrayList<SelectItem>();
			for (T element : data) {
				items.add(new SelectItem(element, (String) PropertyUtils
						.getProperty(element, attr)));
			}
			return items;
		} catch (Exception e1) {
			throw new RuntimeException(e1);
		}

	}

	public static String nvl(String... vals) {
		for(String val:vals)
			if(!StringUtils.isEmpty(val))
				return val;
		return null;
	}

}
