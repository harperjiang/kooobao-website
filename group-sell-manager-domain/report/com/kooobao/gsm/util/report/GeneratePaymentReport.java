package com.kooobao.gsm.util.report;

import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
						"select o from Order o where o.deliveryStatus in ('DELIVERED','PARTIALLY_DELIVERED')",
						Order.class).getResultList();
		XmlSupportDao supportDao = new XmlSupportDao();
		int count = 0;

		Row titleRow = sheet.createRow(count++);
		titleRow.createCell(0).setCellValue("参考编号");
		titleRow.createCell(1).setCellValue("已收款项");
		titleRow.createCell(2).setCellValue("实发款项");
		titleRow.createCell(3).setCellValue("应补/退款项");

		BigDecimal sum = new BigDecimal("0");

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

			Cell c0 = row.createCell(0);
			c0.setCellType(Cell.CELL_TYPE_STRING);
			c0.setCellValue(order.getRefNumber());

			Cell c1 = row.createCell(1);
			c1.setCellType(Cell.CELL_TYPE_NUMERIC);
			c1.setCellValue(order.getPaidAmount().doubleValue());

			Cell c2 = row.createCell(2);
			c2.setCellType(Cell.CELL_TYPE_NUMERIC);
			c2.setCellValue(copy.getTotalAmount().doubleValue());

			Cell c3 = row.createCell(3);
			c3.setCellType(Cell.CELL_TYPE_STRING);
			int result = order.getPaidAmount().compareTo(copy.getTotalAmount());
			BigDecimal subtract = order.getPaidAmount()
					.subtract(copy.getTotalAmount())
					.setScale(2, BigDecimal.ROUND_HALF_UP).abs();
			if (result > 0) {
				c3.setCellValue("退" + subtract.doubleValue());
			}
			if (result < 0)
				c3.setCellValue("补" + subtract.doubleValue());
		}

		FileOutputStream fos = new FileOutputStream("Payment Report");
		workbook.write(fos);
		fos.close();
	}
}
