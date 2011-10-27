package pay;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kooobao.gsm.domain.entity.delivery.Delivery;
import com.kooobao.gsm.domain.entity.delivery.DeliveryItem;
import com.kooobao.gsm.domain.entity.delivery.ExpressCompany;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;

public class ImportPay {

	static final String FULL = "全";

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Workbook wb = new HSSFWorkbook(
				ImportPay.class
						.getResourceAsStream("新69#订单 1-218 复核后的总表(含第四批发货单号).xls"));
		Sheet sheet = wb.getSheet("总表（61以后部分发货的）");

		EntityManager em = Persistence.createEntityManagerFactory(
				"GroupSellManagerDomain").createEntityManager();
		Log log = LogFactory.getLog(ImportPay.class);
		for (int count = sheet.getFirstRowNum(); count < sheet.getLastRowNum(); count++) {

			Row row = sheet.getRow(count);
			String refNumber = readString(row.getCell(0));
			String status = readString(row.getCell(1));
			String unsent = readString(row.getCell(2));
			String expressNum = readString(row.getCell(3));
			if (StringUtils.isEmpty(refNumber))
				continue;
			try {
				Double.parseDouble(refNumber);
			} catch (NumberFormatException e) {
				continue;
			}
			refNumber = String.valueOf((int) Double.parseDouble(refNumber));
			long orderCount = em
					.createQuery(
							"select count(o) from Order o where o.refNumber = :refNumber",
							Long.class).setParameter("refNumber", refNumber)
					.getSingleResult();
			if (orderCount == 0) {
				log.warn("No order found for refNumber:" + refNumber);
				continue;
			}
			if (orderCount > 1) {
				log.warn("Will not deal with duplicate refNumber, please manually deal with it. RefNumber:"
						+ refNumber);
				continue;
			}

			if (FULL.equals(status)) {
				em.getTransaction().begin();
				Order order = em
						.createQuery(
								"select o from Order o where o.refNumber = :refNumber",
								Order.class)
						.setParameter("refNumber", refNumber).getSingleResult();
				Delivery delivery = new Delivery();
				String express = readString(row.getCell(19));
				if ("快递".equals(express))
					delivery.setCompany(ExpressCompany.中通.name());
				else if ("平邮".equals(express)) {
					delivery.setCompany(ExpressCompany.邮政.name());
				}
				delivery.setNumber(expressNum);

				for (OrderItem item : order.getItems()) {
					DeliveryItem di = new DeliveryItem();
					di.setOrderItem(item);
					di.setCount(item.getCount());
					delivery.addItem(di);
				}
				delivery.prepare();

				em.persist(delivery);
				em.getTransaction().commit();
			} else if (!StringUtils.isEmpty(unsent)) {

				String[] unsentList = unsent.split("[\\s,，]+");
				Map<String, String> unsentMap = new HashMap<String, String>();
				for (String unsentItem : unsentList)
					unsentMap.put(unsentItem, unsentItem);

				em.getTransaction().begin();
				Order order = em
						.createQuery(
								"select o from Order o where o.refNumber = :refNumber",
								Order.class)
						.setParameter("refNumber", refNumber).getSingleResult();
				Delivery delivery = new Delivery();
				String express = readString(row.getCell(19));
				if ("快递".equals(express))
					delivery.setCompany(ExpressCompany.中通.name());
				else if ("平邮".equals(express)) {
					delivery.setCompany(ExpressCompany.邮政.name());
				}
				delivery.setNumber(expressNum);

				for (OrderItem item : order.getItems()) {
					if (unsentMap.containsKey(item.getProduct().getCode()))
						continue;
					DeliveryItem di = new DeliveryItem();
					di.setOrderItem(item);
					di.setCount(item.getCount());
					delivery.addItem(di);
				}
				delivery.prepare();

				em.persist(delivery);
				em.getTransaction().commit();
			}
		}
	}

	protected static String readString(Cell cell) {
		if (null == cell)
			return null;
		if (cell.getCellType() == Cell.CELL_TYPE_STRING)
			return cell.getStringCellValue();
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
			return String.valueOf((long) cell.getNumericCellValue());
		return null;
	}

}
