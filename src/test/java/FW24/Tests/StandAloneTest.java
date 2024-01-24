package FW24.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	public static void main(String[] args) throws InterruptedException {
		String ProductName = "IPHONE 13 PRO";

		//Invoke the driver and open the browser
		WebDriverManager.edgedriver().setup();
		WebDriver driver = new EdgeDriver();
		driver.manage().window().maximize();

		//implisit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");

		// Login Scenario
		//Locate username and enter username
		driver.findElement(By.id("userEmail")).sendKeys("sachin.waghmare@gmail.com");

		//Locate password and enter password
		driver.findElement(By.id("userPassword")).sendKeys("Sachin@123");

		//Locate login btn and click
		driver.findElement(By.id("login")).click();

		//Explisit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait until product list loads
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		//Grab products in list 
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		//Filter the product from list
		WebElement prod = products.stream().filter(product-> 
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);

		//Add product to cart
		prod.findElement(By.cssSelector(".card-body  button:last-of-type")).click();

		//Wait until "product added to cart" element appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// Wait until loading disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		//Click on cart button
		driver.findElement(By.cssSelector("[routerlink *= 'cart']")).click();

		//Validate cart products are as expected
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean match =  cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(ProductName));
		Assert.assertTrue(match);

		//click on checkout button
		driver.findElement(By.cssSelector(".totalRow button")).click();

		//Select country from dropdown
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.xpath("//input[@placeholder = 'Select Country']")), "india").build().perform();

		//Wait until dropdown list visible
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));

		driver.findElement(By.xpath("(//span[@class='ng-star-inserted'])[2]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btnn.action__submit.ng-star-inserted")));

		//		 JavascriptExecutor js = (JavascriptExecutor) driver;
		//		 js.executeScript("window.scrollBy(0,350)", "");

		WebElement orderBtn = driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted"));
		orderBtn.click();		 

		//validate order confirmation message
		String confirmMessage = driver.findElement(By.xpath("//h1[@class='hero-primary']")).getText();
		System.out.println(confirmMessage);
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		//js.executeScript("window.scrollBy(0,-350)", "");
		//goto orders page
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']")).click();

		//grab list of ordered products and print the name of ordered product
		List<WebElement> orderedProducts =  driver.findElements(By.cssSelector("tr td:nth-child(3)"));

		for(int i=0; i<orderedProducts.size(); i++) {
			String orderedProducttext =orderedProducts.get(i).getText();
			if(orderedProducttext.equalsIgnoreCase(ProductName)) {
				System.out.println(orderedProducttext);
				break;
			}
		}
		driver.close();	 
	}
}
