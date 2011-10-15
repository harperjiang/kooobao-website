package product;

import java.io.IOException;
import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kooobao.gsm.domain.entity.product.Category;
import com.kooobao.gsm.domain.entity.product.Product;

public class ProductImport {

	public static void main(String[] args) throws IOException {

		Workbook wb = new XSSFWorkbook("data/product.xlsx");
		Sheet sheet = wb.getSheet("Sheet1");

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("GroupSellManagerDomain");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		for (int index = sheet.getFirstRowNum(); index < sheet.getLastRowNum(); index++) {
			Row row = sheet.getRow(index);
			String id = String.valueOf(row.getCell(0).getNumericCellValue());
			String name = row.getCell(1).getStringCellValue();
			String refPrice = String.valueOf(row.getCell(2)
					.getNumericCellValue());
			String weight = String
					.valueOf(row.getCell(3).getNumericCellValue());
			if (StringUtils.isEmpty(name))
				continue;
			Product product = new Product();
			product.setCode(id);
			product.setCategory(Category.BOOK.name());
			product.setName(name);
			product.setRefUnitPrice(new BigDecimal(refPrice));
			product.setNetWeight(new BigDecimal(weight));
			em.persist(product);
		}
		em.getTransaction().commit();
	}
}
