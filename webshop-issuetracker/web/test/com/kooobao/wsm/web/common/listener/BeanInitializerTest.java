package com.kooobao.wsm.web.common.listener;

import static org.junit.Assert.*;

import org.junit.Test;

import com.kooobao.wsm.web.common.listener.BeanInitializer;

public class BeanInitializerTest {

	@Test
	public void testGetManagedBeanNameFromView() {
		assertEquals("",new BeanInitializer().getManagedBeanNameFromView("view_issue_list.htm"));
	}

}
