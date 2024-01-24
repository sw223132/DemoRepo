package FW24.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import FW24.baseTest.BaseTest;
import FW24.baseTest.Retry;
import FW24.pageObjects.CartPage;
import FW24.pageObjects.ProductCatalog;

public class ErrorValidationTests extends BaseTest{

	@Test(groups= {"errorValidation"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		landingPage.loginApplication("sachin.waghmare@gmail.com", "Sachin@1234");
		String errorMessage =landingPage.getErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password."); 
	}
	
	@Test
	public void cartProductErrorValidation() throws IOException, InterruptedException {
		String productName = "IPHONE 13 PRO";
		ProductCatalog productCatalog = landingPage.loginApplication("sachin.waghmare123@gmail.com", "Sachin@123");
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();	 
		Boolean match = cartPage.verifyCartProduct("IPHONE 13 PRO");
		Assert.assertTrue(match);
	}
	
}
