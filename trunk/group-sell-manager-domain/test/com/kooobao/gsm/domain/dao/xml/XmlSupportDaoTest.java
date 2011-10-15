package com.kooobao.gsm.domain.dao.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;

public class XmlSupportDaoTest {

	@Test
	public void testGetAmountRule() {

		DeliveryTarget dt = new DeliveryTarget() {
			public String getDeliveryMethod() {
				return "EXPRESS";
			}

			public String getAddress() {
				return "上海市浦东新区";
			}

			public BigDecimal getAmount() {
				return new BigDecimal(5);
			}

			public BigDecimal getWeight() {
				return new BigDecimal(10);
			}
		};
		DeliveryAmountRule rule = new XmlSupportDao().getAmountRule(dt);
		assertNotNull(rule);
		assertEquals(new BigDecimal("14"), rule.calculate(dt));
	}

}
