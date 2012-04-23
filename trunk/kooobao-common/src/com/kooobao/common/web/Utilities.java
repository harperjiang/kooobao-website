package com.kooobao.common.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

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

}
