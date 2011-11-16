package order;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.kooobao.gsm.domain.dao.xml.XmlSupportDao;
import com.kooobao.gsm.domain.entity.group.Group;
import com.kooobao.gsm.domain.entity.order.Order;
import com.kooobao.gsm.domain.entity.order.OrderItem;
import com.kooobao.gsm.domain.entity.product.Product;
import com.kooobao.gsm.domain.entity.rule.DeliveryAmountRule;
import com.kooobao.gsm.domain.entity.rule.DeliveryMethod;
import com.kooobao.gsm.domain.entity.rule.GrossWeightRule;
import com.kooobao.gsm.service.OrderService;

public class OrderImporter {
	static Log log = LogFactory.getLog(OrderImporter.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("GroupSellManagerDomain");
		EntityManager em = emf.createEntityManager();

		Workbook orderwb = new HSSFWorkbook(new FileInputStream(
				"data_file/order/181-217.xls"));

		Sheet sheet = orderwb.getSheet("Sheet1");

		List<Order> orders = new ArrayList<Order>();

		int count = sheet.getFirstRowNum() + 1;

		Query query = em
				.createQuery("select p from Product p where p.code = ?1");

		Group g = em.createQuery("select g from Group g", Group.class)
				.getSingleResult();

		while (count < sheet.getLastRowNum() || count < 30) {
			Row row = sheet.getRow(count++);

			if (row == null || row.getCell(0) == null)
				continue;

			double number = row.getCell(0).getNumericCellValue();
			try {
				// System.out.println(number);
				String id = row.getCell(2).getStringCellValue();
				List<Long> productList = new ArrayList<Long>();
				String list = null;
				if (row.getCell(4).getCellType() == Cell.CELL_TYPE_NUMERIC) {
					productList
							.add((long) row.getCell(4).getNumericCellValue());
				} else {
					list = row.getCell(4).getStringCellValue();
					productList = parseBookList(list, number);
				}
				String dlvMthd = row.getCell(13).getStringCellValue();
				String name = row.getCell(6).getStringCellValue();
				double paid = row.getCell(8).getNumericCellValue();

				String address = row.getCell(7).getCellType() == Cell.CELL_TYPE_NUMERIC ? String
						.valueOf(row.getCell(7).getNumericCellValue()) : row
						.getCell(7).getStringCellValue();
				if (row.getCell(7).getCellType() == Cell.CELL_TYPE_NUMERIC)
					log.warn("Order :" + number + " " + id
							+ " Numeric Address Found");
				String remark = row.getCell(14).getStringCellValue() + " ";
				if (row.getCell(15) != null) {
					if (row.getCell(15).getCellType() == Cell.CELL_TYPE_NUMERIC) {
						remark += ((long) row.getCell(15).getNumericCellValue());
					} else {
						remark += row.getCell(15).getStringCellValue();
					}
				}
				if (StringUtils.isEmpty(id) || number == 0)
					continue;

				Order order = new Order();
				order.setRefNumber(String.valueOf((int) number));
				order.setCustomer(id);
				if (POST.equals(dlvMthd))
					order.setExpectDeliveryMethod(DeliveryMethod.POST.name());
				else if (EXPRESS.equals(dlvMthd)) {
					order.setExpectDeliveryMethod(DeliveryMethod.EXPRESS.name());
				} else {
					log.warn("Order :" + number + " " + id
							+ " Unrecognized Delivery Method:" + dlvMthd);
					order.setExpectDeliveryMethod(DeliveryMethod.EXPRESS.name());
				}
				order.getContact().setName(name);
				order.setPaidAmount(new BigDecimal(paid));
				order.getContact().setAddress(address);
				order.setRemark(remark);
				order.setGroup(g.getName());
				for (Long productId : productList) {
					Product product = null;
					try {
						product = (Product) query.setParameter(1,
								String.valueOf(productId)).getSingleResult();

						OrderItem item = new OrderItem();
						item.setCount(1);
						item.setProduct(product);
						item.setUnitPrice(product.getRefUnitPrice());
						order.addItem(item);
					} catch (Exception e) {
						log.warn("Order :" + number + " " + id + " Product "
								+ productId + " not found");
					}
				}
				if (0 == order.getItems().size()) {
					log.warn("Order :" + number + " " + id + " has no item");
					continue;
				}
				OrderService.updateOrderTotal(order);
				// Update Package Weight
				// Update Total Amount

				GrossWeightRule gwRule = supportDao.getWeightRule(order);

				DeliveryAmountRule rule = supportDao.getAmountRule(order);
				OrderService.updateOrderTotalAmount(order, rule, gwRule);

				orders.add(order);
			} catch (Exception e) {
				log.error("Order :" + number + " has Exception", e);
			}
		}

		 em.getTransaction().begin();
		 for (Order order : orders)
		 em.persist(order);
		 em.getTransaction().commit();

	}

	private static XmlSupportDao supportDao = new XmlSupportDao();

	static final String EXPRESS = "快递";
	static final String POST = "平邮";

	static final String REG_EXP = "(\\d+)[\\s\\t,、\\u00A0\\.-《]*.*";

	static final String REG_EXP2 = "(\\d+[\\. ]+)*(\\d+)\\.?";

	static Pattern pattern = Pattern.compile(REG_EXP);

	public static List<Long> parseBookList(String content, double number) {

		List<Long> result = new ArrayList<Long>();

		String[] rows = content.split("[\n\r]+");
		for (String row : rows) {
			if (!StringUtils.isEmpty(row)) {
				row = row.trim();
			}
			Matcher matcher = pattern.matcher(row);
			if (matcher.matches()) {
				result.add(Long.parseLong(matcher.group(1)));
			} else {
				if (!StringUtils.isEmpty(row))
					log.warn("Order " + number + " Unrecongized Product Line "
							+ row);
			}
		}
		if (0 == result.size()) {
			if (Pattern.matches(REG_EXP2, content)) {
				String[] vals = content.split("\\.");
				for (String val : vals)
					result.add(Long.valueOf(val));
			}
		}
		if (0 == result.size()) {
			// try to remove additional crlf
			String[] items = content.split("[\n\r][\n\r]+");
			for (String item : items) {
				item = item.replaceAll("[\n\r]", " ");
				Matcher matcher = pattern.matcher(item);
				if (matcher.matches()) {
					result.add(Long.valueOf(matcher.group(1)));
				} else if (result.size() > 0) {
					log.warn("Order " + number
							+ " Some single line was found unmatched");
				}
			}
		}
		return result;
	}

}
