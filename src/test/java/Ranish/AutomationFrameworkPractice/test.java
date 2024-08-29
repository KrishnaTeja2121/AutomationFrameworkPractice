package Ranish.AutomationFrameworkPractice;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);	
		driver.findElement(By.cssSelector(".totalRow button")).click();
		

	}

}
