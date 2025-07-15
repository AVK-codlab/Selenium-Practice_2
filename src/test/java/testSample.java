import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import excelData.dataDriven;

public class testSample {

	@Test(dataProvider="GoogleData")
	public void GoogleTest(String data) throws IOException {

//		String testcase = "Actor";
//		
//		dataDriven d = new dataDriven();
//		ArrayList<String> data = d.excelData(testcase);
//		
//		String actor = data.get(1);
		
		System.out.println(data);
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.findElement(By.name("q")).sendKeys(data,Keys.ENTER);
		driver.quit();
	}
	
	@DataProvider(name="GoogleData")
	public Object[] dataTest() throws IOException {
		
		String testcase = "Actor";
		
		dataDriven d = new dataDriven();
		ArrayList<String> testData = d.excelData(testcase);
		
		return new Object[] {testData.get(1),testData.get(2),testData.get(3)};
	}

}
