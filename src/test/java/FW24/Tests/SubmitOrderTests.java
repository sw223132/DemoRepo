package FW24.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import FW24.baseTest.BaseTest;
import FW24.pageObjects.CartPage;
import FW24.pageObjects.CheckOut;
import FW24.pageObjects.OrderConfirmation;
import FW24.pageObjects.OrderPage;
import FW24.pageObjects.ProductCatalog;

public class SubmitOrderTests extends BaseTest{
	String productName = "IPHONE 13 PRO";

	@Test(dataProvider = "getData")
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
	
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();	 
		Boolean match = cartPage.verifyCartProduct(input.get("productName"));
		Assert.assertTrue(match);
		CheckOut checkOut = cartPage.goTocheckOut();
		checkOut.selectCountry("india");
		OrderConfirmation orderConfirmation = checkOut.submitOrder();
		String cnfmMsg = orderConfirmation.getconfirmMessage();
		Assert.assertEquals(cnfmMsg, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistory() {
		landingPage.loginApplication("sachin.waghmare@gmail.com", "Sachin@123");
		landingPage.goToOrderPage();
		
		OrderPage orderPage = new OrderPage(driver);
		Boolean match = orderPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}
	
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\reports" + testCaseName + ".jpg");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"\\reports" + testCaseName + ".jpg";
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		 		List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\FW24\\data\\LoginTestData.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}};		
	}

	/*
	 * // // @DataProvider // public Object[][] getData() { // return new Object
	 * [][] { // {"sachin.waghmare@gmail.com", "Sachin@123", "IPHONE 13 PRO"}, //
	 * {"sachin.waghmare123@gmail.com", "Sachin@123", "ADIDAS ORIGINAL"} // }; // }
	 */	
	
	
	/*
	@DataProvider
	public Object[][] getData() throws IOException {
		
		 * // HashMap<String, String> map = new HashMap<String, String>(); //
		 * map.put("email", "sachin.waghmare@gmail.com"); // map.put("password",
		 * "Sachin@123"); // map.put("productName", "IPHONE 13 PRO"); // //
		 * HashMap<String, String> map1 = new HashMap<String, String>(); //
		 * map1.put("email", "sachin.waghmare123@gmail.com"); // map1.put("password",
		 * "Sachin@123"); // map1.put("productName", "ADIDAS ORIGINAL");
			List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+
				"\\src\\test\\java\\FW24\\data\\LoginTestData.json");
		return new Object [][] {{data.get(0)}, {data.get(1)}};		
	}
	 */	
}
