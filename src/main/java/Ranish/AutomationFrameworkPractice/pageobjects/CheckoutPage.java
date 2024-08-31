package Ranish.AutomationFrameworkPractice.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationFrameworkPractice.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
     WebDriver driver;
	 public CheckoutPage(WebDriver driver){
		 super(driver);
		 this.driver=driver;
		 PageFactory.initElements(driver, this);
	 }
	 
	 @FindBy(css="[placeholder='Select Country']")
	 WebElement country;
	 
	 @FindBy(css=".action__submit")
	 WebElement submit;
	 
	 @FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	 WebElement selectCountry;
	 
	 @FindBy(xpath=".hero-primary")
	 WebElement confirmationMessage;
	 
	 By results=By.cssSelector(".ta-results");
	 
	 public void selectCountry(String countryName) {
		 Actions a=new Actions(driver);
		 a.sendKeys(country,countryName).build().perform();
		 selectCountry.click();
	 }
	 
	 public void submitOrder() {
		 submit.click();
	 }
	 
	 public String getConfimrationMessage() {
		 return confirmationMessage.getText();
	 }
			 
			 
	 
	 
	


	

}
