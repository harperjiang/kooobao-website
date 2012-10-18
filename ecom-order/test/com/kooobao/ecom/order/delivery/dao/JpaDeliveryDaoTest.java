package com.kooobao.ecom.order.delivery.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.order.delivery.entity.Delivery;
import com.kooobao.ecom.order.delivery.entity.DeliveryItem;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaDeliveryDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaDeliveryDao deliveryDao;
	
	@Test
	public void testStore() {
		Delivery delivery = new Delivery();
		
		DeliveryItem di = new DeliveryItem();
		delivery.addItem(di);
		
		deliveryDao.store(delivery);
	}

}
