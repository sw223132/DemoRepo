package FW24.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import FW24.abstractComponent.AbstractComponent;

public class CheckOut extends AbstractComponent {
	WebDriver driver;
	public CheckOut(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath="//input[@placeholder = 'Select Country']")
	WebElement selectCountryDd;
	
	@FindBy (xpath="(//span[@class='ng-star-inserted'])[2]")
	WebElement selectCountry;
	
	@FindBy (css=".btnn.action__submit.ng-star-inserted")
	WebElement placeOrderBtn;
	
	By results = By.cssSelector(".ta-results.list-group.ng-star-inserted");
	By sbmtBtnBy = By.cssSelector(".btnn.action__submit.ng-star-inserted");
	
	public void selectCountry(String country) {
		Actions a = new Actions(driver);
	a.sendKeys(selectCountryDd, country).build().perform();
	waitForElementToApprear(results);
	selectCountry.click();
	}
	
	public OrderConfirmation submitOrder() {
		placeOrderBtn.click();
		OrderConfirmation orderConfirmation = new OrderConfirmation(driver);
		return orderConfirmation;
	}
	
}
