package com.kooobao.gsm.domain.entity.delivery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.kooobao.gsm.domain.entity.order.Order;

public class DeliveryTest {

	@Test
	public void testPersistence() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("GroupSellManagerDomain");
		
		EntityManager em = emf.createEntityManager();
		
		Order order = em.find(Order.class, 51L);

		Delivery delivery = new Delivery();
		
		DeliveryItem di = new DeliveryItem();
		
		di.setOrderItem(order.getItems().get(0));
		di.setCount(di.getOrderItem().getCount());
		delivery.addItem(di);
		
		delivery.prepare();
		
		
		em.getTransaction().begin();
		
		em.persist(delivery);
		em.flush();
		
		em.getTransaction().commit();
	}
}
