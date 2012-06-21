package com.kooobao.lm.purchase;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.purchase.dao.PurchaseDao;
import com.kooobao.lm.purchase.entity.Purchase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class DefaultPurchaseServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private PurchaseService purchaseService;

	@Resource
	private PurchaseDao purchaseDao;

	@Before
	public void prepare() {
		Purchase purchase = new Purchase();
		purchase.setOid(1);
		purchase.setDeliveryFee(BigDecimal.TEN);
		purchaseDao.store(purchase);
	}

	@Test
	public void testCreate() {
		Purchase purchase = purchaseDao.find(1);
		assertTrue(BigDecimal.TEN.compareTo(purchase.getDeliveryFee()) == 0);
		purchaseService.create(purchase);
	}

}
