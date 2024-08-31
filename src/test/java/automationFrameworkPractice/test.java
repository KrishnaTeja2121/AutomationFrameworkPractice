package automationFrameworkPractice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Ranish.AutomationFrameworkPractice.pageobjects.CartPage;
import Ranish.AutomationFrameworkPractice.pageobjects.CheckoutPage;
import Ranish.AutomationFrameworkPractice.pageobjects.LandingPage;
import Ranish.AutomationFrameworkPractice.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String productName="ZARA COAT 3";
		
		LandingPage landpage=new LandingPage(driver);
		landpage.goTo();
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
