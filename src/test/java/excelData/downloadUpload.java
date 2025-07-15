package excelData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class downloadUpload {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		//
		String fruit = "Apple";
		String filePath = "C:\\Users\\Lenovo\\Downloads\\download.xlsx";
		String newPrice = "500";

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/upload-download-test/index.html");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(By.id("downloadButton")).click();
		Thread.sleep(10000);

		ArrayList<Integer> colRow = columnRowNumber(filePath, "price", fruit);

		System.out.println("column " + colRow.get(0));
		System.out.println("row " + colRow.get(1));
		//Thread.sleep(10000);
		
		Assert.assertTrue(updateCell(filePath, colRow, newPrice));
		//Thread.sleep(10000);

		WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
		upload.sendKeys("C:\\Users\\Lenovo\\Downloads\\download.xlsx");

		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[role='alert']")));

		String uploadmsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();

		Assert.assertEquals("Updated Excel Data Successfully.", uploadmsg);
		System.out.println(driver.findElement(By.xpath("//div[@role='alert']")).getText());

		w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[role='alert']")));

		String PriceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
		String actualPrice = driver.findElement(By.xpath(
				"//div[text()='" + fruit + "']/parent::div/parent::div/div[@id='cell-" + PriceColumn + "-undefined']"))
				.getText();
		System.out.println(actualPrice);
		Assert.assertEquals(actualPrice, newPrice);
		driver.quit();

	}

	private static ArrayList<Integer> columnRowNumber(String filePath, String price, String fruit) throws IOException {

		ArrayList<Integer> a = new ArrayList<Integer>();

		FileInputStream fis = new FileInputStream(filePath);

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("sheet1");
		Iterator<Row> rows = sheet.iterator();
		Row firstRow = rows.next();
		Iterator<Cell> cells = firstRow.cellIterator();

		int priceColumnNum = 0;
		int k = 0;

		while (cells.hasNext()) {
			Cell cell = cells.next();

			if (cell.getStringCellValue().equalsIgnoreCase(price)) {
				priceColumnNum = k;
				a.add(priceColumnNum);
			}
			k++;
		}
		System.out.println(priceColumnNum);

		Iterator<Row> fruitRows = sheet.iterator();
		int fruitRowNum = 0;
		int j = 0;
		while (fruitRows.hasNext()) {
			Row fruitRow = fruitRows.next();
			Iterator<Cell> ce = fruitRow.cellIterator();
			while (ce.hasNext()) {
				Cell cef = ce.next();
				if (cef.getCellType() == CellType.STRING && cef.getStringCellValue().equalsIgnoreCase(fruit)) {
					fruitRowNum = j;
					a.add(fruitRowNum);
				}
			}
			j++;
		}
		System.out.println(fruitRowNum);

		return a;

	}

	private static boolean updateCell(String filePath, ArrayList<Integer> colRow, String newPrice) throws IOException {

		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("sheet1");
		Row rowField = sheet.getRow(colRow.get(1));
		Cell cellField = rowField.getCell(colRow.get(0));
		cellField.setCellValue(newPrice);
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		workbook.close();
		fis.close();
		return true;

	}

}
