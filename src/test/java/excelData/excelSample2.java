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

public class excelSample2 {

	public ArrayList<String> excelData() throws IOException {

		ArrayList<String> a = new ArrayList<String>();

		String testcaseName = null;

		FileInputStream fis = new FileInputStream("C:\\Users\\Lenovo\\TestData\\TestData.xlsx");

		XSSFWorkbook wb = new XSSFWorkbook(fis);

		int sheetNum = wb.getNumberOfSheets();

		for (int i = 0; i < sheetNum; i++) {
			if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase("sheet1")) {
				XSSFSheet sheet = wb.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row row = rows.next();
				Iterator<Cell> cells = row.cellIterator();

				int column = 0;
				int k = 0;
				int j = 0;
				int flagcolumn = 0;

				while (cells.hasNext()) {
					Cell cell = cells.next();
					if (cell.getStringCellValue().equalsIgnoreCase("testcase")) {
						column = k;
					}
					k++;
				}

				System.out.println(column);

//******************************************************************//
				Iterator<Cell> flagcell = row.cellIterator();
				while (flagcell.hasNext()) {
					Cell cellfirstRow = flagcell.next();
					if (cellfirstRow.getStringCellValue().equalsIgnoreCase("flag")) {
						flagcolumn = j;
					}
					j++;
				}
				System.out.println(flagcolumn);

				while (rows.hasNext()) {
					Row rowflag = rows.next();
					Boolean f = rowflag.getCell(flagcolumn).getBooleanCellValue();
					if (f.equals(Boolean.TRUE)) {
						// Iterator<Cell> cellflag = rowflag.cellIterator();
						testcaseName = rowflag.getCell(column).getStringCellValue();
						System.out.println(testcaseName);
					}
				}

//******************************************************************//
				Iterator<Row> dataRow = sheet.iterator();
				while (dataRow.hasNext()) {
					System.out.println("lastwhile");
					Row rd = dataRow.next();
					if (rd.getCell(column).getStringCellValue().equals(testcaseName)) {
						Iterator<Cell> cd = rd.cellIterator();
						while (cd.hasNext()) {
							Cell cv = cd.next();
							if (cv.getCellType() == CellType.STRING) {

								a.add(cv.getStringCellValue());
							} else if(cv.getCellType() == CellType.NUMERIC){
								a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));
							} else {
								a.add(null);
							}
						}
					}
				}
			}
		}
		return a;
	}

}
