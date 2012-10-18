package com.kooobao.ecom.order.product.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.order.product.entity.Product;
import com.kooobao.ecom.order.product.entity.ProductSet;
import com.kooobao.ecom.order.product.entity.ProductUnit;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaProductDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaProductDao productDao;
	
	@Test
	public void testStore() {
		Product product = new Product();
		product.setName("Good");
		product.setDescription("This is some description");
		productDao.store(product);
		
		ProductSet product2 = new ProductSet();
		product2.setName("Good Set");
		product2.setDescription("This is another description");
		product2.add(new ProductUnit(product,1));
		productDao.store(product2);
	}

}
