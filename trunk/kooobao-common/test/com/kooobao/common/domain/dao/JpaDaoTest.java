package com.kooobao.common.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;
import org.springframework.orm.jpa.JpaTemplate;

public class JpaDaoTest {

	private String puName;

	private EntityManager em;

	private JpaTemplate template;

	@Before
	public void before() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(puName);
		em = emf.createEntityManager();

		template = new JpaTemplate();
		template.setEntityManager(em);

		prepareData();
	}

	public String getPuName() {
		return puName;
	}

	public void setPuName(String puName) {
		this.puName = puName;
	}

	public EntityManager getEm() {
		return em;
	}

	public JpaTemplate getTemplate() {
		return template;
	}

	protected void prepareData() {

	}
}
