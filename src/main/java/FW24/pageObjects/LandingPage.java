package FW24.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import FW24.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	
	}

	@FindBy (id="userEmail")
	WebElement username;

	@FindBy (id="userPassword")
	WebElement passWord;

	@FindBy (id="login")
	WebElement submitBtn;
	
	@FindBy (css=".ng-star-inserted")
	WebElement errorToaster;
	
	
	public ProductCatalog loginApplication(String userName, String password) {
		username.sendKeys(userName);
		passWord.sendKeys(password);
		submitBtn.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public String getErrorMessage() {
		waitForElementToDisplay(errorToaster);
		String errorMessage= errorToaster.getText();
		return errorMessage;
	}
}
