package com.kooobao.gsm.web.order;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.web.SupportDataBean;

public class QueryOrderBeanTest {

	@Test
	public void testSearch() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"application-context.xml");

		QueryOrderBean qob = new QueryOrderBean() {
			protected void addMessage(
					javax.faces.application.FacesMessage.Severity severity,
					String message) {
			};
		};
		qob.setSupportDataBean(new SupportDataBean());
		qob.setOrderDao(appContext.getBean(OrderDao.class));

		qob.setGroupName("Group A");
		for (int i = 1; i < 10; i++) {
			qob.setStatus(i);
			assertEquals("success", qob.search());
		}
	}
}
