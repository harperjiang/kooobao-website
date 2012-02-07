package com.kooobao.fr.domain.dao.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.fr.domain.dao.ActorDao;
import com.kooobao.fr.domain.entity.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaActorDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {
	@Resource(name="actorDao")
	private ActorDao actorDao;
	
	@Test
	public void testGetActors() {
		actorDao.getActors(Role.OPERATOR);
	}
	
	@Test
	public void testGetActorById() {
		actorDao.getActor("big");
	}

}
