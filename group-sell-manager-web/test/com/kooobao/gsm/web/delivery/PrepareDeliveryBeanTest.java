package com.kooobao.gsm.web.delivery;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kooobao.gsm.domain.dao.DeliveryDao;
import com.kooobao.gsm.domain.dao.OrderDao;
import com.kooobao.gsm.domain.dao.SupportDao;

public class PrepareDeliveryBeanTest {

	@Test
	public void testSave() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"application-context.xml");

		PrepareDeliveryBean pdb = new PrepareDeliveryBean();
		pdb.setDeliveryDao(appContext.getBean("deliveryDao", DeliveryDao.class));
		pdb.setOrderDao(appContext.getBean("orderDao", OrderDao.class));
		pdb.setSupportDao(appContext.getBean("supportDao", SupportDao.class));

		// pdb.setOrderId(51L);
		pdb.setOrder(pdb.getOrderDao().find(51l));

		pdb.onPageLoad();

		pdb.getDelivery().getItems().get(1).setCount(1);
		pdb.getDelivery().getItems().get(2).setCount(1);

		pdb.save();
	}

}
