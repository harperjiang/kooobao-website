package com.kooobao.ecom.crm.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.kooobao.common.domain.service.Context;
import com.kooobao.ecom.crm.ErrorCode;
import com.kooobao.ecom.crm.customer.dao.CustomerDao;
import com.kooobao.ecom.crm.customer.entity.ContactMethod;
import com.kooobao.ecom.crm.customer.entity.Customer;
import com.kooobao.ecom.crm.customer.entity.CustomerFollowup;

@ContextConfiguration(locations = { "/application-context.xml" })
public class DefaultCustomerServiceTest extends
		AbstractJUnit4SpringContextTests {

	@Resource
	private CustomerService customerService;

	@Resource(name = "memoryCustomerDao")
	private CustomerDao customerDao;

	@Before
	public void prepare() {
		Customer c = new Customer();
		c.setId("A");
		c.setOid(100);
		c.setName("WangErxiao");
		c.getContact().setName("DaTou");
		c.getContact().setPhone("12331331313");
		c.getContact().setEmail("234@asdf.com");
		c.getContact().setAddress("KDAFDADAF");
		c.setOwnBy("111");

		customerDao.store(c);

		c = new Customer();
		c.setOid(101);
		c.setOwnBy("123");
		customerDao.store(c);
	}

	@Test
	public void testUpdate() {
		Customer c = new Customer();
		c.setId("A");
		c.setOid(100);
		c.setName("LiXiaosan");
		c.getContact().setName("GuLu");
		c.getContact().setPhone("333sss323");
		c.getContact().setEmail("234@asdf.edu");
		c.getContact().setAddress("Kasdfasdf");
		c.setOwnBy("111");

		Context context = new Context();
		context.setOperatorId("111");
		c = customerService.update(context, c);

		CustomerFollowup cfu = c.getFollowups()
				.get(c.getFollowups().size() - 1);
		assertEquals(c, cfu.getCustomer());
		assertEquals(
				"Name: WangErxiao -> LiXiaosan\nContact Name: DaTou -> GuLu\nContact Email: 234@asdf.com -> 234@asdf.edu\nContact Phone: 12331331313 -> 333sss323\nContact Address: KDAFDADAF -> Kasdfasdf\n",
				cfu.getReference());
		assertEquals("111", cfu.getOwnBy());
	}

	@Test
	public void testUpdateException() {
		Customer c = new Customer();
		c.setId("B");
		c.setOid(100);
		c.setName("LiXiaosan");
		c.getContact().setName("GuLu");
		c.getContact().setPhone("333sss323");
		c.getContact().setEmail("234@asdf.edu");
		c.getContact().setAddress("Kasdfasdf");
		c.setOwnBy("111");

		Context context = new Context();
		context.setOperatorId("111");
		try {
			c = customerService.update(context, c);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(ErrorCode.CUST_UPDATE_CHANGE_ID_FORBID.name(),
					e.getMessage());
		}
	}

	@Test
	public void testRecordFollowup() {
		CustomerFollowup cfu = new CustomerFollowup();
		Context context = new Context();

		try {
			customerService.recordFollowup(context, cfu);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(ErrorCode.COMMON_NO_OPERATOR.name(), e.getMessage());
		}
		context.setOperatorId("1234");
		try {
			customerService.recordFollowup(context, cfu);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(ErrorCode.CUST_FOLLOWUP_NO_CUST.name(), e.getMessage());
		}

		Customer c = new Customer();
		c.setOid(101);
		cfu.setCustomer(c);
		try {
			customerService.recordFollowup(context, cfu);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(ErrorCode.CUST_FOLLOWUP_NO_METHOD.name(),
					e.getMessage());
		}

		cfu.setMethod(ContactMethod.OTHER);
		try {
			customerService.recordFollowup(context, cfu);
			fail();
		} catch (IllegalArgumentException e) {

			assertEquals(ErrorCode.CUST_FOLLOWUP_NO_REF.name(), e.getMessage());
		}
		cfu.setReference("ABC");

		try {
			customerService.recordFollowup(context, cfu);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals(ErrorCode.CUST_UPDATE_NOT_OWN.name(), e.getMessage());
		}

		context.setOperatorId("123");

		Customer stored = customerService.recordFollowup(context, cfu);
		CustomerFollowup followup = stored.getFollowups().get(
				stored.getFollowups().size() - 1);
		assertEquals("123", followup.getOwnBy());
		assertEquals("ABC", followup.getReference());
		assertEquals(stored, followup.getCustomer());
	}

	@Test
	public void testRequest() {
		fail("Not yet implemented");
	}

	@Test
	public void testExchange() {
		fail("Not yet implemented");
	}

	@Test
	public void testFreeCustomers() {
		fail("Not yet implemented");
	}

	@Test
	public void testFree() {
		fail("Not yet implemented");
	}

}
