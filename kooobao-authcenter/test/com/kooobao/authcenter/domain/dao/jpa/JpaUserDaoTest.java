package com.kooobao.authcenter.domain.dao.jpa;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.authcenter.domain.dao.UserDao;
import com.kooobao.authcenter.domain.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaUserDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Before
	public void prepareData() {
		User user = new User();
		user.setId("debbie");
		Map<String,String> systemMap = new HashMap<String,String>();
		systemMap.put("gsm", "gsm");
		systemMap.put("kbk", "kbk");
		user.setSystems(systemMap);
		user.setLastLoginTime(new Date());
		
		userDao.store(user);
	}
	
	@Test
	public void testFindUser() {
		assertNotNull(getUserDao().findUser("gsm", "debbie"));
	}

	@Test
	public void testStore() {

	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
