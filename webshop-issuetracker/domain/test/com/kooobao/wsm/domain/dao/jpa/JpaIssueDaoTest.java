package com.kooobao.wsm.domain.dao.jpa;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import com.kooobao.wsm.domain.dao.IssueDao.FindIssueBean;
import com.kooobao.wsm.domain.entity.issue.Issue;
import com.kooobao.wsm.domain.entity.issue.TroubleCase;

public class JpaIssueDaoTest {

	@Test
	public void testFindIssue() {
		assertEquals(Issue.class, new JpaIssueDao().getParamClass());
	}

	@Test
	public void testFindIssues() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("WebshopManagerPU");
		EntityManager em = emf.createEntityManager();

		JpaIssueDao dao = new JpaIssueDao();
		dao.setEntityManager(em);

		dao.findIssues(new FindIssueBean(TroubleCase.class, null, null,
				(String) null, 3));
		dao.findIssues(new FindIssueBean(TroubleCase.class, null, null,
				new String[] { "NEW", "WAIT_CUSTOMER", "WAIT_SUPPORT" }, 3));
	}
}
