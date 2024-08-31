package automationFrameworkPractice;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationFrameworkPractice.pageobjects.CartPage;
import AutomationFrameworkPractice.pageobjects.CheckoutPage;
import AutomationFrameworkPractice.pageobjects.LandingPage;
import AutomationFrameworkPractice.pageobjects.ProductCatalog;
import automationFrameworkPractice.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class test extends BaseTest{
	
	
	@Test
	public void submitOrder() throws IOException{		

		// TODO Auto-generated method stub
		String productName="ZARA COAT 3";
		LandingPage landpage=launchApp();

		
		landpage.loginApplication("test21@test.com", "Test1234");
		
		
		ProductCatalog productCatalog=new ProductCatalog(driver);
		List<WebElement> products=productCatalog.getProductsList();
		productCatalog.addProductToCart(productName);
		productCatalog.goToCartPage();
		
		CartPage cart=new CartPage(driver);
		Boolean match=cart.VerifyproductDisplay(productName);

		Assert.assertTrue(match);	
		cart.gotoCheckout();
		CheckoutPage checkout=new CheckoutPage(driver);
		checkout.selectCountry("india");
		checkout.submitOrder();
		String confirmMessage=checkout.getConfimrationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();		

	}

}
