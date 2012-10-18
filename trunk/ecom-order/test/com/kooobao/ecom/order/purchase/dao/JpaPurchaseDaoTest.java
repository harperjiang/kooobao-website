package com.kooobao.ecom.order.purchase.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.order.purchase.entity.DisplayItem;
import com.kooobao.ecom.order.purchase.entity.Purchase;
import com.kooobao.ecom.order.purchase.entity.PurchaseItem;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaPurchaseDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaPurchaseDao purchaseDao;
	
	@Test
	public void testStore() {
		Purchase purchase = new Purchase();
		purchase.setNumber("ADFASF");
		
		PurchaseItem pItem = new PurchaseItem();
		purchase.addItem(pItem);
		
		DisplayItem dItem = new DisplayItem();
		purchase.addDispItem(dItem);
		
		purchaseDao.store(purchase);
	}

}
