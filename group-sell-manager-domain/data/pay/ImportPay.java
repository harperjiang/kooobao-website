package pay;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ImportPay {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Workbook wb = new HSSFWorkbook(
				ImportPay.class
						.getResourceAsStream("新69#订单 1-218 复核后的总表(含第四批发货单号).xls"));
		Sheet sheet = wb.getSheet("总表（61以后部分发货的）");

		
		for (int count = sheet.getFirstRowNum(); count < sheet.getLastRowNum(); count++) {
			
			Row row = sheet.getRow(count);
			
		}
	}

}
