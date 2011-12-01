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

public class GenerateSalesStatisticsReport {

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
		
		EntityManager em = Persistence.createEntityManagerFactory(
				"GroupSellManagerDomain").createEntityManager();
		List results = em
				.createNativeQuery(
						"select p.code,p.name,gg.cnt from("
								+ "select gsi.product_id, sum(gsi.sent_count) cnt from gsm_order go "
								+ "join gsm_order_item gsi on gsi.order_id = go.obj_id "
								+ "where go.status = 'CONFIRMED' and go.delivery_status in ('DELIVERED','PARTIALLY_DELIVERED') "
								+ "group by gsi.product_id) gg join gsm_product p on p.obj_id = gg.product_id")
				.getResultList();

		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("Sheet 1");
		int rowCount = 0;
		int cellCount = 0;
		Row row = sheet.createRow(rowCount++);
		row.createCell(cellCount++).setCellValue("编号");
		row.createCell(cellCount++).setCellValue("书名");
		row.createCell(cellCount++).setCellValue("数量");
		row.createCell(cellCount++).setCellValue("每件提成");		
		row.createCell(cellCount++).setCellValue("总提成");
		for (Object result : results) {
			cellCount = 0;
			row = sheet.createRow(rowCount++);
			Object[] resultArray = (Object[])result;
			row.createCell(cellCount++).setCellValue((String)resultArray[0]);
			row.createCell(cellCount++).setCellValue((String)resultArray[1]);
			Cell cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			cell.setCellValue(((BigDecimal)resultArray[2]).doubleValue());
			
			
			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			BigDecimal commission = commissions.get((String)resultArray[0]);
			cell.setCellValue(commission.doubleValue());
			
			cell = row.createCell(cellCount++);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
			
			commission = commission.multiply((BigDecimal)resultArray[2]);
			cell.setCellValue(commission.doubleValue());
		}
		FileOutputStream fos = new FileOutputStream("CommissionReport.xls");
		wb.write(fos);
		fos.close();
	}

}
