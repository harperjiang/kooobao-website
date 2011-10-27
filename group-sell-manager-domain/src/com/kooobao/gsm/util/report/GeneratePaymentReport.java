package com.kooobao.gsm.util.report;

import java.io.FileOutputStream;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kooobao.gsm.domain.dao.xml.XmlSupportDao;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;
import com.kooobao.gsm.service.OrderService;

public class GeneratePaymentReport {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet 1");

		EntityManager em = Persistence.createEntityManagerFactory(
				"GroupSellManagerDomain").createEntityManager();

		List<Order> orders = em
				.createQuery(
						"select o from Order o where delivery_status in ('DELIVERED','PARTIALLY_DELIVERED')",
						Order.class).getResultList();
		XmlSupportDao supportDao = new XmlSupportDao();
		int count = 0;
		for (Order order : orders) {
			Row row = sheet.createRow(count++);
			Order copy = new Order();
			copy.getContact().setAddress(order.getAddress());
			for (OrderItem item : order.getItems()) {
				OrderItem copyItem = new OrderItem();
				copyItem.setCount(item.getPreparedCount());
				copyItem.setUnitPrice(item.getUnitPrice());
				copyItem.setProduct(item.getProduct());
				copy.addItem(copyItem);
			}
			OrderService.updateOrderTotal(copy);
			// Update Package Weight
			// Update Total Amount
			GrossWeightRule gwRule = supportDao.getWeightRule(copy);

			DeliveryAmountRule rule = supportDao.getAmountRule(copy);
			OrderService.updateOrderTotalAmount(copy, rule, gwRule);
		}

		FileOutputStream fos = new FileOutputStream("Payment Report");
		workbook.write(fos);
		fos.close();
	}
}
