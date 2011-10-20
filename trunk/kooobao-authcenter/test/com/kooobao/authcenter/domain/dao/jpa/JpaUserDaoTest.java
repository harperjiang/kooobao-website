package com.kooobao.authcenter.domain.dao.jpa;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kooobao.authcenter.domain.dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaUserDaoTest extends AbstractJUnit4SpringContextTests {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Test
	public void testFindUser() {
		getUserDao().findUser("gsm", "debbie");
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
