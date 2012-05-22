package com.kooobao.lm.rule.entity;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.kooobao.lm.profile.entity.Visitor;

public class PenaltyRuleTest extends PenaltyRule {

	@Test
	public void testGetPenalty() {
		PenaltyRule pr = new PenaltyRule();
		Visitor v = new Visitor();
		v.setLevel(0);
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date sampleDate = sdf.parse("20120101", new ParsePosition(0));
		assertEquals(
				new BigDecimal("0.00"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120101", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("1.00"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120102", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("2.00"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120103", new ParsePosition(0))));
		v.setLevel(1);
		assertEquals(
				new BigDecimal("0.00"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120101", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("0.90"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120102", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("1.80"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120103", new ParsePosition(0))));
		v.setLevel(2);
		assertEquals(
				new BigDecimal("0.00"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120101", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("0.81"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120102", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("1.62"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120103", new ParsePosition(0))));
		v.setLevel(3);
		assertEquals(
				new BigDecimal("0.00"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120101", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("0.73"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120102", new ParsePosition(0))));
		assertEquals(
				new BigDecimal("1.46"),
				pr.getPenalty(v, sampleDate,
						sdf.parse("20120103", new ParsePosition(0))));
	}

}
