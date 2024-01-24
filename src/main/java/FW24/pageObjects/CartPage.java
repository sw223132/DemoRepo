package FW24.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


import FW24.abstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy (css=".totalRow button")
	WebElement checkoutBtn;
	
	public Boolean verifyCartProduct(String productName){
		Boolean match =  cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equals(productName));
		return match;	
	}
	
	public CheckOut goTocheckOut() {
		checkoutBtn.click();
		CheckOut checkOut = new CheckOut(driver);
		return checkOut;
	}



}
