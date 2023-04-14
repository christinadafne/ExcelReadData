package dataProviderExcel_Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utils {

	public static XSSFRow row;
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static FileInputStream fin;
	public static String sheetname = "Sheet1";

	public static void excelfile() throws Exception {
		String path = System.getProperty("user.dir");
		fin = new FileInputStream(path + "/src/test/resources/excelfile/datafile.xlsx");
		wb = new XSSFWorkbook(fin);
		sheet = wb.getSheet("Sheet1");

	}

	public static int rowcount() {

		int rsize = 0;
		try {
			rsize = sheet.getPhysicalNumberOfRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rsize;
	}

	public static int colcount() {

		int csize = 0;
		try {
			csize = sheet.getRow(0).getPhysicalNumberOfCells();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return csize;
	}

	public static Object[][] stringdata() throws Exception {
		excelfile();
		int rc = rowcount();
		int cc = colcount();
		Object[][] data = new Object[rc - 1][cc];
		for (int i = 1; i < rc; i++) {
			for (int j = 0; j < cc; j++) {
				String celldata = sheet.getRow(i).getCell(j).getStringCellValue();

				data[i - 1][j] = celldata;

			}
		}
		return data;
	}

	/*
	 * public static void main(String[] args) { try { stringdata(); } catch
	 * (Exception e) {
	 * 
	 * e.printStackTrace(); } }
	 */
}
