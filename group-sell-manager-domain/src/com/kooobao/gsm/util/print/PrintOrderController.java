package com.kooobao.gsm.util.print;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.swing.SwingUtilities;

import com.kooobao.gsm.domain.entity.delivery.Delivery;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.util.print.PrintExpressOrderController;

public class PrintOrderController extends PrintExpressOrderController {

	@Override
	public void load() {
		try {
			final Delivery delivery = getEm()
					.createQuery(
							"select d from Delivery d where d.order.refNumber = :num",
							Delivery.class)
					.setParameter("num", bean.getRefNumber()).getSingleResult();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					bean.setToName(delivery.getContact().getName());
					bean.setToAddress(delivery.getContact().getAddress());
					bean.setToMobile(delivery.getContact().getPhone());
					bean.setRemark("第" + bean.getRefNumber() + "号");
					manager.loadAll();
				}
			});

		} catch (Exception e) {
			try {
				final Order order = getEm()
						.createQuery(
								"select o from Order o where o.refNumber = :num",
								Order.class)
						.setParameter("num", bean.getRefNumber())
						.getSingleResult();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						bean.setToName(order.getContact().getName());
						bean.setToAddress(order.getContact().getAddress());
						bean.setToMobile(order.getContact().getPhone());
						bean.setRemark("第" + bean.getRefNumber() + "号");
						manager.loadAll();
					}
				});
			} catch (NoResultException ex) {

			}
		}

	}

	private EntityManager em;

	protected EntityManager getEm() {
		if (null == em)
			em = Persistence.createEntityManagerFactory(
					"GroupSellManagerDomain").createEntityManager();
		return em;
	}

	public static void main(String[] args) {
		new PrintOrderController();
	}
}
