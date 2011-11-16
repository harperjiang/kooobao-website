package com.kooobao.gsm.domain.dao.jpa;

import org.junit.Before;
import org.junit.Test;

import com.kooobao.common.domain.dao.JpaDaoTest;
import com.kooobao.gsm.domain.dao.DeliveryDao.SearchBean;

public class JpaDeliveryDaoTest extends JpaDaoTest {

	@Before
	public void before() {
		setPuName("GroupSellManagerDomain");
		super.before();
	}

	@Test
	public void testSearch() {
		JpaDeliveryDao jdd = new JpaDeliveryDao();
		jdd.setEntityManager(getEm());
		jdd.search(new SearchBean("Group A", null, "", "custA", "contactA"));
	}
	
	@Test
	public void testInsert() {
		
	}

}
