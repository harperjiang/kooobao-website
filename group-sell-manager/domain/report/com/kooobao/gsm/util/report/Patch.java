package com.kooobao.gsm.util.report;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.kooobao.gsm.domain.dao.xml.XmlSupportDao;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;
import com.kooobao.gsm.service.OrderService;

public class Patch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManager em = Persistence.createEntityManagerFactory(
				"GroupSellManagerDomain").createEntityManager();
		em.getTransaction().begin();

		List<Order> orders = em
				.createQuery(
						"select o from Order o where o.refNumber in ('192','62','104','135','206')",
						Order.class).getResultList();
		XmlSupportDao supportDao = new XmlSupportDao();
		for (Order order : orders) {
			order.setAdjust(BigDecimal.ZERO);
			OrderService.updateOrderTotal(order);
			GrossWeightRule gwRule = supportDao.getWeightRule(order);
			DeliveryAmountRule rule = supportDao.getAmountRule(order);
			OrderService.updateOrderTotalAmount(order, rule, gwRule);
		}
		em.getTransaction().commit();
	}

}
