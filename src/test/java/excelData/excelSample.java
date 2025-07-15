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

public class excelSample {

	public ArrayList<String> excelData(String testCaseName) throws IOException {
		
		ArrayList<String> a = new ArrayList();

		FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\TestData\\TestData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		int sheetNumber = wb.getNumberOfSheets();

		for (int i = 0; i < sheetNumber; i++) {
			if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase("sheet1")) {
				XSSFSheet sheet = wb.getSheetAt(i);

				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cells = firstRow.cellIterator();

				int column = 0;
				int k = 0;

				while (cells.hasNext()) {
					Cell cell = cells.next();
					if (cell.getStringCellValue().equalsIgnoreCase("testcase")) {
						column = k;
					}
					k++;
				}

				System.out.println(column);

				while (rows.hasNext()) {
					Row r = rows.next();

					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> c = r.cellIterator();
						while (c.hasNext()) {
							Cell cv = c.next();
							if(cv.getCellType() == CellType.STRING) {
								a.add(cv.getStringCellValue());
							}
							else {
							a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));
							}
						}
					}
				}

			}
		}
		return a;
	}

}
