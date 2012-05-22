package com.kooobao.lm.rule.entity;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.kooobao.lm.profile.entity.Visitor;

public class DueRuleTest {

	@Test
	public void testGetDueDate() {
		DueRule dr = new DueRule();
		Visitor v = new Visitor();
		v.setLevel(0);
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date sampleDate = sdf.parse("20120101",
				new ParsePosition(0));
		assertEquals(sdf.parse("20120115",
				new ParsePosition(0)),dr.getDueDate(v, null, sampleDate));
		v.setLevel(1);
		assertEquals(sdf.parse("20120117",
				new ParsePosition(0)),dr.getDueDate(v, null, sampleDate));
		v.setLevel(2);
		assertEquals(sdf.parse("20120119",
				new ParsePosition(0)),dr.getDueDate(v, null, sampleDate));
	}
}
