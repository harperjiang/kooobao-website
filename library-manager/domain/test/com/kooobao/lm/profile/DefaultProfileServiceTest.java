package com.kooobao.lm.profile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.apache.commons.lang.Validate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.lm.profile.dao.JpaOperatorDao;
import com.kooobao.lm.profile.dao.JpaVisitorDao;
import com.kooobao.lm.profile.entity.ActivationRecord;
import com.kooobao.lm.profile.entity.Operator;
import com.kooobao.lm.profile.entity.Visitor;
import com.kooobao.lm.profile.entity.VisitorStatus;
import com.kooobao.lm.profile.entity.VisitorType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(defaultRollback = true)
public class DefaultProfileServiceTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	private DefaultProfileService profileService;

	@Resource(name = "visitorDao")
	private JpaVisitorDao visitorDao;

	@Resource
	private JpaOperatorDao operatorDao;

	@Before
	public void prepareData() {
		// For Activate
		Visitor iv = new Visitor();
		iv.setType(VisitorType.INSTITUTE);
		iv.setId("harperjiang@msn.com");
		iv.setStatus(VisitorStatus.INACTIVE.name());
		iv.setRecommendBy("good@abc.com");
		visitorDao.store(iv);

		Visitor iv1 = new Visitor();
		iv1.setType(VisitorType.INSTITUTE);
		iv1.setId("harperjiang1@msn.com");
		iv1.setStatus(VisitorStatus.INACTIVE.name());
		visitorDao.store(iv1);

		Visitor rec = new Visitor();
		rec.setType(VisitorType.PERSON);
		rec.setId("good@abc.com");
		rec.setStatus(VisitorStatus.ACTIVE);
		rec.setDeposit(BigDecimal.TEN);
		visitorDao.store(rec);

		ActivationRecord ar = new ActivationRecord();
		ar.setActivationId("activate");
		ar.setVisitorId("harperjiang@msn.com");
		visitorDao.store(ar);

		ar = new ActivationRecord();
		ar.setActivationId("activate1");
		ar.setVisitorId("harperjiang1@msn.com");
		visitorDao.store(ar);

		// For Redeem
		Visitor v = new Visitor();
		v.setId("redeem");
		v.setType(VisitorType.INSTITUTE);
		v.setStatus(VisitorStatus.ACTIVE);
		v.setDeposit(new BigDecimal("80"));
		v.setLevel(0);
		visitorDao.store(v);

		Operator opr = new Operator();
		opr.setId("opr");
		operatorDao.store(opr);
	}

	@Test
	public void testRegister() {
		profileService.register("harperjiang@gmail.com", "123456",
				VisitorType.INSTITUTE, "good@abc.com");
		Visitor v = visitorDao.find("harperjiang@gmail.com");
		assertEquals(VisitorStatus.INACTIVE.name(), v.getStatus());
		// Validate Not null
		visitorDao
				.getEntityManager()
				.createQuery(
						"select ar from ActivationRecord ar where ar.visitorId = :vid",
						ActivationRecord.class)
				.setParameter("vid", "harperjiang@gmail.com").getSingleResult();
		assertEquals("good@abc.com", v.getRecommendBy());

	}

	@Test
	public void testActivateUser() {
		assertEquals(VisitorStatus.INACTIVE.name(),
				profileService.getVisitor("harperjiang@msn.com").getStatus());
		profileService.activateUser("activate");
		assertEquals(VisitorStatus.ACTIVE.name(),
				profileService.getVisitor("harperjiang@msn.com").getStatus());
		assertNull(visitorDao.getActivationRecord("activate"));

		Visitor rec = profileService.getVisitor("good@abc.com");
		assertTrue(new BigDecimal("30").compareTo(rec.getDeposit()) == 0);
	}

	@Test
	public void testActivateUser2() {
		assertEquals(VisitorStatus.INACTIVE.name(),
				profileService.getVisitor("harperjiang1@msn.com").getStatus());
		profileService.activateUser("activate1");
		assertEquals(VisitorStatus.NOT_VERIFIED.name(), profileService
				.getVisitor("harperjiang1@msn.com").getStatus());
		assertNull(visitorDao.getActivationRecord("activate1"));
	}

	@Test
	public void testSendRegMail() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOperator() {
		Validate.notNull(profileService.getOperator("opr"));
	}

	@Test
	public void testRedeem() {
		Visitor v = visitorDao.find("redeem");
		profileService.redeem(v, new BigDecimal("60"));
		assertEquals(1, visitorDao.find("redeem").getLevel());
	}

}
