package com.kooobao.authcenter.domain.dao.jpa;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.kooobao.authcenter.domain.dao.UserDao;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/application-context.xml" })
public class JpaUserDaoTest/* extends AbstractJUnit4SpringContextTests*/ {

	@Resource(name = "userDao")
	private UserDao userDao;

	@Test
	public void testFindUser() {
		EntityManager em = Persistence.createEntityManagerFactory(
				"kooobao-authcenter").createEntityManager();

		JpaUserDao dao = new JpaUserDao();
		dao.setEntityManager(em);
		dao.findUser("gsm", "cndebbie");
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
