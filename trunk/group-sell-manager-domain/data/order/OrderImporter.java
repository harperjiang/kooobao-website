package order;

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
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

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

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("GroupSellManagerDomain");
		EntityManager em = emf.createEntityManager();

		HSSFWorkbook orderwb = new HSSFWorkbook(
				OrderImporter.class.getResourceAsStream("../order.xls"));

		Sheet sheet = orderwb.getSheet("1-20-ok");

		List<Order> orders = new ArrayList<Order>();

		int count = sheet.getFirstRowNum() + 1;

		Query query = em
				.createQuery("select p from Product p where p.code = ?1");

		Group g = em.createQuery("select g from Group g", Group.class)
				.getSingleResult();

		while (count < sheet.getLastRowNum()) {
			Row row = sheet.getRow(count++);
			if ((row.getCell(0).getCellType() == Cell.CELL_TYPE_STRING && StringUtils
					.isEmpty(row.getCell(0).getStringCellValue()))
					|| (row.getCell(0).getCellType() == Cell.CELL_TYPE_NUMERIC && row
							.getCell(0).getNumericCellValue() == 0))
				continue;

			double number = row.getCell(0).getNumericCellValue();
			String id = row.getCell(2).getStringCellValue();
			String list = row.getCell(3).getStringCellValue();
			List<Long> productList = parseBookList(list);
			String dlvMthd = row.getCell(5).getStringCellValue();
			String name = row.getCell(6).getStringCellValue();
			double paid = row.getCell(8).getNumericCellValue();
			String address = row.getCell(9).getStringCellValue();
			String remark = row.getCell(10).getStringCellValue();

			Order order = new Order();
			order.setRefNumber(String.valueOf((int) number));
			order.setCustomer(id);
			order.setExpectDeliveryMethod(POST.equals(dlvMthd) ? DeliveryMethod.POST
					.name() : DeliveryMethod.EXPRESS.name());
			order.getContact().setName(name);
			order.setPaidAmount(new BigDecimal(paid));
			order.getContact().setAddress(address);
			order.setRemark(remark);
			order.setGroup(g.getName());
			for (Long productId : productList) {
				Product product = (Product) query.setParameter(1,
						String.valueOf(productId)).getSingleResult();
				if (null == product) {
					LogFactory.getLog(OrderImporter.class).warn(
							"Product " + productId + " of Order " + id
									+ " not found");
				}
				OrderItem item = new OrderItem();
				item.setCount(1);
				item.setProduct(product);
				item.setUnitPrice(product.getRefUnitPrice());
				order.addItem(item);
			}

			OrderService.updateOrderTotal(order);
			// Update Package Weight
			// Update Total Amount

			GrossWeightRule gwRule = supportDao.getWeightRule(order);

			DeliveryAmountRule rule = supportDao.getAmountRule(order);
			OrderService.updateOrderTotalAmount(order, rule, gwRule);

			orders.add(order);
		}

		em.getTransaction().begin();
		for (Order order : orders)
			em.persist(order);
		em.getTransaction().commit();

	}

	private static XmlSupportDao supportDao = new XmlSupportDao();

	static final String EXPRESS = "快递";
	static final String POST = "平邮";

	static final String REG_EXP = "(\\d+)[\\s\\t,、\\u00A0]+.*";

	static Pattern pattern = Pattern.compile(REG_EXP);

	public static List<Long> parseBookList(String content) {

		List<Long> result = new ArrayList<Long>();
		String[] rows = content.split("[\n\r]+");
		for (String row : rows) {
			Matcher matcher = pattern.matcher(row);
			if (matcher.matches()) {
				result.add(Long.parseLong(matcher.group(1)));
			}
		}
		return result;
	}

}
