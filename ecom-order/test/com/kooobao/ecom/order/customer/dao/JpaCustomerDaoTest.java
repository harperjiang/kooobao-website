package com.kooobao.ecom.order.customer.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.order.customer.entity.Customer;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaCustomerDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private JpaCustomerDao customerDao;

	@Test
	public void testStore() {
		Customer customer = new Customer();
		customer.setName("Good");
		customer.setId("cndebbie");
		customer.setSource("taobao");

		customerDao.store(customer);
	}

}
