package com.kooobao.cws.domain.article;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

public class JpaArticleDaoTest {

	@Test
	public void testFindByKeyword() {
		JpaArticleDao jad = new JpaArticleDao();
		EntityManager em = Persistence.createEntityManagerFactory(
				"company-website-domain-local").createEntityManager();
		jad.setEntityManager(em);
		
		assertTrue(!CollectionUtils.isEmpty(jad.findByKeyword("三")));
	}

	@Test
	public void testGetLatest() {
		fail("Not yet implemented");
	}

}
