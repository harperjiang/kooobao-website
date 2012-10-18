package com.kooobao.ecom.order.returnreq.dao;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.kooobao.ecom.order.returnreq.entity.RequestLog;
import com.kooobao.ecom.order.returnreq.entity.RequestStatus;
import com.kooobao.ecom.order.returnreq.entity.ReturnItem;
import com.kooobao.ecom.order.returnreq.entity.ReturnRequest;

@ContextConfiguration(locations = { "/application-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class JpaReturnRequestDaoTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Resource
	JpaReturnRequestDao returnRequestDao;

	@Test
	public void testStore() {
		ReturnRequest req = new ReturnRequest();
		req.setExpressNumber("335032");

		ReturnItem item = new ReturnItem();
		req.addItem(item);
		RequestLog log = new RequestLog();
		log.setFromStatus(RequestStatus.FINISH);
		log.setToStatus(RequestStatus.ACCEPT);

		req.addLog(log);

		returnRequestDao.store(req);
	}

}
