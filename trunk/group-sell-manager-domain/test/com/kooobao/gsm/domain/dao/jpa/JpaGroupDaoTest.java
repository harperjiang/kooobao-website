package com.kooobao.gsm.domain.dao.jpa;

import org.junit.Before;
import org.junit.Test;

import com.kooobao.common.domain.dao.JpaDaoTest;

public class JpaGroupDaoTest extends JpaDaoTest {

	@Before
	public void before() {
		setPuName("GroupSellManagerDomain");
		super.before();
	}

	@Test
	public void testGetActiveGroup() {
		JpaGroupDao jdd = new JpaGroupDao();
		jdd.setTemplate(getTemplate());

		jdd.getActiveGroup();
	}

}
