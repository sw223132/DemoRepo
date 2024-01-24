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

public class OrderConfirmation extends AbstractComponent {
	WebDriver driver;
	public OrderConfirmation(WebDriver driver) {
		super(driver);
		this.driver =driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy (xpath="//h1[@class='hero-primary']")
	WebElement confirmMessage;
	
	public String getconfirmMessage() {
		 String cnfmMsg = confirmMessage.getText();
		return cnfmMsg;
	}
	
}
