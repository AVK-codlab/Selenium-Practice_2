import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.Keys;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.emulation.Emulation;
import org.openqa.selenium.devtools.v127.fetch.Fetch;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.Request;
import org.openqa.selenium.devtools.v127.network.model.Response;
import java.net.URI;

public class devtoolDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		ChromeDriver driver = new ChromeDriver();

		DevTools devTools = driver.getDevTools();

		devTools.createSession();
		
//*****************Device- mobile device************//
//		devTools.send(Emulation.setDeviceMetricsOverride(320, 500, 50, true, Optional.empty(), Optional.empty(),
//				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
//				Optional.empty(), Optional.empty(), Optional.empty()));

	//	driver.get("https://www.google.com/");
		
	//	driver.executeCdpCommand(null, null);	
		

//*************************************************************************************//
//***********************Location****************************//	
		
//		Map<String, Object> location = new HashMap<String, Object>();
//		location.put("latitude", 40);
//		location.put("longitude", 3);
//		location.put("accuracy", 1);
//		
//		devTools.send(Emulation.setGeolocationOverride(Optional.of(40), Optional.of(3), Optional.of(100)));
//		driver.executeCdpCommand("Emulation.setGeolocationOverride", location);
//		driver.get("https://where-am-i.org/");
//		
//		driver.get("https://netflix.com");
		
//		driver.get("https://www.google.com/");
//		driver.findElement(By.name("q")).sendKeys("netflix",Keys.ENTER);
//		driver.findElements(By.cssSelector(".LC20lb")).get(0).click();
//		String title = driver.findElement(By.xpath("//h1[@data-uia='nmhp-card-hero-text-title']")).getText();
//		System.out.println(title);

		
//*************************************************************************************//
//******************************Network*************************//
		
//		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//		//request sent
//		devTools.addListener(Network.requestWillBeSent(), request ->
//		{
//			Request req = request.getRequest();
//		//	System.out.println(req.getUrl());
//		//	System.out.println(req.ge);
//			
//		});
//		
//		//event will get fired
//		devTools.addListener(Network.responseReceived(), response ->
//		{
//			Response res = response.getResponse();
//			System.out.println(res.getUrl());
//			System.out.println(res.getStatus());
//				
//				
//	});
//		
//		driver.get("https://www.google.com");
		
//*************************************************************************************//		
//*******************Mocking response - Fetch*****************************************//
		
//		devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
//		devTools.addListener(Fetch.requestPaused(), request->
//		{
//			if(request.getRequest().getUrl().contains("shetty")) {
//				String mockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");	
//				
//				System.out.println(mockedUrl);
//				
//				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.empty(), 
//						Optional.empty(), Optional.empty(), Optional.empty()));
//				}
//			else {
//				devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()), Optional.empty(), 
//						Optional.empty(), Optional.empty(), Optional.empty()));
//			}
//		});
//		
//		driver.get("https://rahulshettyacademy.com/angularAppdemo/");
//		driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
//		Thread.sleep(5000);
//		System.out.println(driver.findElement(By.cssSelector("p")).getText());

	
//*************************************************************************************//
//*******************************Authentication using uri****************************************//
		
		Predicate<URI> uriPredicate = uri -> uri.getHost().contains("httpbin.org");
		((HasAuthentication)driver).register(uriPredicate, UsernameAndPassword.of("foo", "bar"));
		
		driver.get("https://httpbin.org/basic-auth/foo/bar");
		
//****************************************************************************************************//
//
	}

}
