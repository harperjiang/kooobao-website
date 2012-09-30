package com.kooobao.crm.customer.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.common.domain.dao.Cursor;
import com.kooobao.crm.customer.entity.Customer;
import com.kooobao.crm.customer.entity.CustomerStatus;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaCustomerDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	CustomerDao customerDao;

	@Before
	public void prepare() {
		Customer cust = new Customer();
		cust.setOwnBy("a");
		cust.setStatus(CustomerStatus.OCCUPIED);
		cust.setUpdateTime(DateUtils.addDays(new Date(), -3));
		customerDao.store(cust);

		cust = new Customer();
		cust.setOwnBy("a");
		cust.setStatus(CustomerStatus.OCCUPIED);
		cust.setUpdateTime(DateUtils.addDays(new Date(), -5));
		customerDao.store(cust);

		cust = new Customer();
		cust.setOwnBy("b");
		cust.setStatus(CustomerStatus.OCCUPIED);
		cust.setUpdateTime(DateUtils.addDays(new Date(), -6));
		customerDao.store(cust);

		cust = new Customer();
		cust.setStatus(CustomerStatus.FREE);
		customerDao.store(cust);

		cust = new Customer();
		cust.setStatus(CustomerStatus.FREE);
		customerDao.store(cust);
	}

	@Test
	public void testGetCustomersByOwner() {
		assertEquals(2, customerDao.getCustomersByOwner("a").size());
		assertEquals(1, customerDao.getCustomersByOwner("b").size());
	}

	@Test
	public void testGetFreeCustomers() {
		assertEquals(2, customerDao.getFreeCustomers(5).size());
		assertEquals(1, customerDao.getFreeCustomers(1).size());
	}

	@Test
	public void testGetOvertimeCustomers() {
		Cursor<Customer> hints = customerDao.getOvertimeCustomers(4);
		int count = 0;
		while (hints.hasNext()) {
			hints.next();
			count++;
		}
		assertEquals(2, count);
	}

}