package FW24.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import FW24.pageObjects.CartPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (css="[routerlink *= 'cart']")
	WebElement cartBtnHeader;
	
	@FindBy (xpath="//button[@routerlink ='/dashboard/myorders']")
	WebElement orderBtnHeader;

	public void waitForElementToApprear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToDisapprear(WebElement webEle) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(webEle));		
	}
	
	public void waitForElementToDisplay(WebElement ele){
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public CartPage goToCartPage() {
		cartBtnHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public void goToOrderPage() {
		orderBtnHeader.click();
	}
}