package com.kooobao.ecom.order.delivery.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.order.delivery.entity.DeliverySite;


@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaDeliverySiteDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaDeliverySiteDao deliverySiteDao;
	
	@Test
	public void testStore() {
		DeliverySite ds = new DeliverySite();
		ds.setName("Delivery Site");
		ds.setDefaultSite(true);
		deliverySiteDao.store(ds);
		
		ds = new DeliverySite();
		ds.setName("Delivery Site 2");
		ds.setDefaultSite(false);
		deliverySiteDao.store(ds);
	}

}
