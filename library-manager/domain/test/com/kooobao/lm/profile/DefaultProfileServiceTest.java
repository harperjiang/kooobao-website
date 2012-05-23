package com.kooobao.lm.profile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.profile.dao.JpaVisitorDao;
import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class DefaultProfileServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private DefaultProfileService profileService;

	@Resource(name = "visitorDao")
	private JpaVisitorDao visitorDao;

	@Before
	public void prepareData() {
		// For Activate
		Visitor iv = new Visitor();
		iv.setId("harperjiang@msn.com");
		iv.setStatus(VisitorStatus.INACTIVE.name());
		visitorDao.store(iv);

		ActivationRecord ar = new ActivationRecord();
		ar.setActivationId("activate");
		ar.setVisitorId("harperjiang@msn.com");
		visitorDao.store(ar);
		
		// For Redeem
		Visitor v = new Visitor();
		v.setId("redeem");
		v.setStatus(VisitorStatus.ACTIVE);
		v.setDeposit(new BigDecimal("80"));
		v.setLevel(0);
		visitorDao.store(v);
	}

	@Test
	public void testRegister() {
		profileService.register("harperjiang@gmail.com", "123456");
		Visitor v = visitorDao.find("harperjiang@gmail.com");
		assertEquals(VisitorStatus.INACTIVE.name(), v.getStatus());
		visitorDao
				.getEntityManager()
				.createQuery(
						"select ar from ActivationRecord ar where ar.visitorId = :vid",
						ActivationRecord.class)
				.setParameter("vid", "harperjiang@gmail.com").getSingleResult();
	}

	@Test
	public void testActivateUser() {
		assertEquals(VisitorStatus.INACTIVE.name(),
				profileService.getVisitor("harperjiang@msn.com").getStatus());
		profileService.activateUser("activate");
		assertEquals(VisitorStatus.ACTIVE.name(),
				profileService.getVisitor("harperjiang@msn.com").getStatus());
		assertNull(visitorDao.getActivationRecord("activate"));
	}

	@Test
	public void testSendRegMail() {
		fail("Not yet implemented");
	}

	@Test
	public void testRedeem() {
		Visitor v = visitorDao.find("redeem");
		profileService.redeem(v, new BigDecimal("60"));
		assertEquals(1,visitorDao.find("redeem").getLevel());
	}

}
