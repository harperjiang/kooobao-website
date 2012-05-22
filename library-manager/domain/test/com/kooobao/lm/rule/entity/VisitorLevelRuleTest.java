package com.kooobao.lm.rule.entity;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class VisitorLevelRuleTest extends VisitorLevelRule {

	@Test
	public void testGetLevel() {
		VisitorLevelRule vlr = new VisitorLevelRule();
		assertEquals(0, vlr.getLevel(new BigDecimal("50")));
		assertEquals(1, vlr.getLevel(new BigDecimal("100")));
		assertEquals(2, vlr.getLevel(new BigDecimal("200")));
	}

}
