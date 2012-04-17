package com.kooobao.common.domain.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Before;

public class JpaDaoTest {

	private String puName;

	private EntityManager em;

	@Before
	public void before() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(puName);
		em = emf.createEntityManager();

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

	protected void prepareData() {

	}
}
