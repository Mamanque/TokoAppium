package tokopediaDani;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SearchResultProduct extends BrowserBaseTest {
	
	
	@Test
	public void SearchResult() throws InterruptedException
	{
		//driver.get("http://google.co.id");
		driver.get("https://www.tokopedia.com/");
		
		System.out.println(driver.getTitle());
		
		driver.findElement(By.cssSelector("input[type='search']")).click();
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys("Laptop Gaming");
		driver.findElement(By.cssSelector("input[type='text']")).sendKeys(Keys.ENTER);
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@class='unf-icon'])[5]"))).click();
				
		//driver.findElement(By.xpath("(//*[@class='unf-icon'])[5]")).click();
		//driver.findElement(By.xpath("(//div[@class='css-1kt63sp e1pwamvv3'])[1]")).click();
		//driver.findElement(By.xpath("(//div[@class='css-xermmo'])[3]")).click();
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1000)","");
		
		//String productName = driver.findElement(By.cssSelector("a[href='/angularAppdemo/products/3']")).getText();
		//Assert.assertEquals(productName, "Devops");
		
		Thread.sleep(3000);

	}

}
