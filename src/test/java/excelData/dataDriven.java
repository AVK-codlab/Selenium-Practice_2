package excelData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dataDriven {

	public ArrayList<String> excelData(String testcaseName) throws IOException {
		// TODO Auto-generated method stub

		ArrayList<String> a = new ArrayList<String>();

		FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\TestData\\TestData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		int sheets = wb.getNumberOfSheets();

		for (int i = 0; i < sheets; i++) {
			if (wb.getSheetName(i).equalsIgnoreCase("sheet1")) {
				XSSFSheet sheet = wb.getSheetAt(i); // required sheet is found

				Iterator<Row> rows = sheet.iterator(); // sheet is collection of rows. iterator to iterate rows
				Row firstRow = rows.next(); // enters first row
				Iterator<Cell> cells = firstRow.cellIterator(); // row is collection of cells. iterator to iterate cells

				int k = 0;
				int column = 0;

				while (cells.hasNext()) {
					Cell value = cells.next(); // enters first cell in that row
					if (value.getStringCellValue().equalsIgnoreCase("testcase")) {
						column = k;
					}
					k++;
				}

				System.out.println(column);

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> c = r.cellIterator();
						while (c.hasNext()) {
							Cell cv = c.next();

							if (cv.getCellType() == CellType.STRING) {
								a.add(cv.getStringCellValue());
							} else if(cv.getCellType() == CellType.NUMERIC) {
								a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));
							}
							else {
								a.add(null);
							}

						}
					}
				}
			}

		}
		return a;
	}

	public static void main(String[] args) {
	}
}
