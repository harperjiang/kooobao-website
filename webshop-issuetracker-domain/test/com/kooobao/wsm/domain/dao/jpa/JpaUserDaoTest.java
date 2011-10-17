package com.kooobao.wsm.domain.dao.jpa;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.orm.jpa.JpaTemplate;

public class JpaUserDaoTest {

	private static EntityManager em;

	private static JpaUserDao userDao;

	@BeforeClass
	public static void beforeClass() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("WebshopManagerPU");
		em = emf.createEntityManager();

		JpaTemplate template = new JpaTemplate();
		template.setEntityManager(em);

		userDao = new JpaUserDao();
		userDao.setTemplate(template);
	}

	@Test
	public void testFindUser() {
		assertNotNull(userDao.findUser("cndebbie"));
	}

}
