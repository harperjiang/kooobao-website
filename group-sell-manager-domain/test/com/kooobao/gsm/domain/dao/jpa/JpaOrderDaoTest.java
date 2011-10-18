package com.kooobao.gsm.domain.dao.jpa;

import org.junit.Before;
import org.junit.Test;

import com.kooobao.common.domain.dao.JpaDaoTest;
import com.kooobao.gsm.domain.dao.OrderDao.SearchBean;
import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
import com.kooobao.gsm.domain.entity.order.OrderStatus;

public class JpaOrderDaoTest extends JpaDaoTest {

	@Before
	public void before() {
		setPuName("GroupSellManagerDomain");
		super.before();
	}

	@Test
	public void testSearchOrders() {
		JpaOrderDao orderDao = new JpaOrderDao();
		orderDao.setEntityManager(getEm());

		orderDao.searchOrders(new SearchBean("Group A", "Customer A",
				"Contact A", new String[] { OrderStatus.CONFIRMED.name() },
				new String[] { DeliveryStatus.NOT_PREPARED.name() }, "32",
				false));
	}

	@Test
	public void testSearchBetween() {
		JpaOrderDao orderDao = new JpaOrderDao();
		orderDao.setEntityManager(getEm());

		orderDao.searchOrders(new SearchBean("Group A", "Customer A",
				"Contact A", new String[] { OrderStatus.CONFIRMED.name() },
				new String[] { DeliveryStatus.NOT_PREPARED.name() }, "32-424",
				false));

		orderDao.searchOrders(new SearchBean("Group A", "Customer A",
				"Contact A", new String[] { OrderStatus.CONFIRMED.name() },
				new String[] { DeliveryStatus.NOT_PREPARED.name() }, ">424",
				false));

	}

	@Test
	public void testRollback() {
		JpaOrderDao orderDao = new JpaOrderDao();
		orderDao.setEntityManager(getEm());
		getEm().getTransaction().begin();
		orderDao.rollback(orderDao.find(309l));
		getEm().getTransaction().commit();
	}

}
