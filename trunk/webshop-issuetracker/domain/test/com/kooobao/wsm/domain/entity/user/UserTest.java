package com.kooobao.wsm.domain.entity.user;

import java.security.MessageDigest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class UserTest {

	@Test
	public void testInsertUser() throws Exception {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("WebshopManagerPU");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		User user = new User();
		user.setId("丽丽");
		String plainPass = "lili";
		byte[] digested = MessageDigest.getInstance("md5").digest(
				plainPass.getBytes());
		String encPass = new String(Base64.encodeBase64(digested), "iso-8859-1");
		user.setEncryptedPass(encPass);

		user.setDescription("");
		
		em.persist(user);
		
		tx.commit();
	}
}
