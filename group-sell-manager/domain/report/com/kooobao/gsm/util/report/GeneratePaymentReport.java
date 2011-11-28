package com.kooobao.gsm.util.report;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kooobao.gsm.domain.dao.xml.XmlSupportDao;
import com.kooobao.gsm.domain.entity.order.DeliveryStatus;
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

		Map<String, BigDecimal> commissions = new HashMap<String, BigDecimal>();
		BufferedReader br = new BufferedReader(new InputStreamReader(
				GeneratePaymentReport.class.getResourceAsStream("commission")));
		String line = null;
		while ((line = br.readLine()) != null) {
			String[] result = line.split("\t");
			commissions.put(result[0].trim(), new BigDecimal(result[1]));
		}

		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet 1");

		EntityManager em = Persistence.createEntityManagerFactory(
				"GroupSellManagerDomain").createEntityManager();

		List<Order> orders = em
				.createQuery(
						"select o from Order o where o.status = 'CONFIRMED' and o.deliveryStatus in ('DELIVERED','PARTIALLY_DELIVERED')",
						Order.class).getResultList();
		XmlSupportDao supportDao = new XmlSupportDao();
		int count = 0;

		Row titleRow = sheet.createRow(count++);
		int cellCount = 0;
		titleRow.createCell(cellCount++).setCellValue("参考编号");
		titleRow.createCell(cellCount++).setCellValue("是否有缺书");
		titleRow.createCell(cellCount++).setCellValue("所缺书号");
		titleRow.createCell(cellCount++).setCellValue("订单总金额");
		titleRow.createCell(cellCount++).setCellValue("实付金额");
		titleRow.createCell(cellCount++).setCellValue("实发金额");
		titleRow.createCell(cellCount++).setCellValue("应补/退款项");
		titleRow.createCell(cellCount++).setCellValue("算术值");
		titleRow.createCell(cellCount++).setCellValue("提成");

		for (Order order : orders) {
			Row row = sheet.createRow(count++);
			Order copy = new Order();
			copy.setExpectDeliveryMethod(order.getExpectDeliveryMethod());
			copy.getContact().setAddress(order.getAddress());
			StringBuffer undeliveredBooks = new StringBuffer();
			BigDecimal commission = BigDecimal.ZERO;
			for (OrderItem item : order.getItems()) {
				if (item.getCount() > item.getSentCount()) {
					undeliveredBooks.append(item.getProduct().getCode())
							.append(";");
				}
				OrderItem copyItem = new OrderItem();
				copyItem.setCount(item.getPreparedCount());
				copyItem.setUnitPrice(item.getUnitPrice());
				copyItem.setProduct(item.getProduct());
				copy.addItem(copyItem);
				BigDecimal itemC = commissions.get(item.getProduct().getCode());
				commission = commission.add(itemC.multiply(new BigDecimal(item.getSentCount())));
			}
			OrderService.updateOrderTotal(copy);
			// Update Package Weight
			// Update Total Amount
			GrossWeightRule gwRule = supportDao.getWeightRule(copy);

			DeliveryAmountRule rule = supportDao.getAmountRule(copy);
			OrderService.updateOrderTotalAmount(copy, rule, gwRule);

			cellCount = 0;
			Cell cell = null;
			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(order.getRefNumber());

			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(order.getDeliveryStatus().equals(
					DeliveryStatus.PARTIALLY_DELIVERED.name()) ? "是" : "否");

			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(undeliveredBooks.toString());

			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(order.getTotalAmount().doubleValue());

			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(order.getPaidAmount().doubleValue());

			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(copy.getTotalAmount().doubleValue());

			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_STRING);
			int result = order.getPaidAmount().compareTo(copy.getTotalAmount());
			BigDecimal subtract = order.getPaidAmount()
					.subtract(copy.getTotalAmount())
					.setScale(2, BigDecimal.ROUND_HALF_UP).abs();
			if (result > 0) {
				cell.setCellValue("退" + subtract.doubleValue());
			}
			if (result < 0)
				cell.setCellValue("补" + subtract.doubleValue());

			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(order.getPaidAmount()
					.subtract(copy.getTotalAmount())
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			
			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(commission.doubleValue());
		}

		FileOutputStream fos = new FileOutputStream("Payment Report.xls");
		workbook.write(fos);
		fos.close();
	}
}
