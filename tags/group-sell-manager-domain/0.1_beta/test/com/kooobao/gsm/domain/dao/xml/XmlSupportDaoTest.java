package com.kooobao.gsm.domain.dao.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;

import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryTarget;

public class XmlSupportDaoTest {

	@Test
	public void testExpressRule() {
		DeliveryTarget dt = new DeliveryTarget() {
			public String getDeliveryMethod() {
				return "EXPRESS";
			}

			public String getAddress() {
				return "广东省佛山市";
			}

			public BigDecimal getAmount() {
				return new BigDecimal(5);
			}

			public BigDecimal getWeight() {
				return new BigDecimal(9.9);
			}
		};
		DeliveryAmountRule rule = new XmlSupportDao().getAmountRule(dt);
		assertNotNull(rule);
		assertEquals(new BigDecimal("53"), rule.calculate(dt));
	}

	@Test
	public void testPostRule() {
		DeliveryTarget d2t = new DeliveryTarget() {
			public String getDeliveryMethod() {
				return "POST";
			}

			public String getAddress() {
				return "上海市浦东新区";
			}

			public BigDecimal getAmount() {
				return new BigDecimal(129);
			}

			public BigDecimal getWeight() {
				return new BigDecimal(10);
			}
		};
		DeliveryAmountRule rule = new XmlSupportDao().getAmountRule(d2t);
		assertNotNull(rule);
		assertEquals(new BigDecimal("15"), rule.calculate(d2t));
	}

}
