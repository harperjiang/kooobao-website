package com.kooobao.lm.purchase.dao;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.common.web.bean.PageSearchResult;
import com.kooobao.lm.purchase.PurchaseSearchBean;
import com.kooobao.lm.purchase.entity.Purchase;
import com.kooobao.lm.purchase.entity.PurchaseState;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaPurchaseDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private PurchaseDao purchaseDao;
	
	@Before 
	public void prepare() {
		Purchase p = new Purchase();
		p.request(null);
		p.approve("ABC", null);
		purchaseDao.store(p);
	}
	
	@Test
	public void testSearch() {
		PurchaseSearchBean psb = new PurchaseSearchBean();
		psb.setFrom(0);
		psb.setTo(10);
		psb.setState(PurchaseState.APPROVE);
		
		PageSearchResult<Purchase> psr = purchaseDao.search(psb);
		assertEquals(1,psr.getCount());
		assertEquals(PurchaseState.APPROVE,psr.getResult().get(0).getState());
	}

}
