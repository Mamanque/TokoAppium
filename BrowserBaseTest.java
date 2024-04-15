package tokopediaDani;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;



public class BrowserBaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	
	
	@BeforeClass
	public void AppiumConfig() throws MalformedURLException, URISyntaxException
	{
		// code to start Appium server
		//write a code here
		// Android Driver, iOS Driver
		//</usr/local/lib/node_modules/appium/index.js>
		service = new AppiumServiceBuilder().withAppiumJS(new File("//usr//local//lib//node_modules//appium//index.js"))
		 .withIPAddress("127.0.0.1").usingPort(4723).build();
		service.start();		
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setChromedriverExecutable("//Users//dani//Documents//chromedriverAndroid");
		options.setDeviceName("AVD_DANI");
		options.setCapability("browserName", "Chrome");
		
		
		driver = new AndroidDriver(new URI("http://127.0.0.1:4723").toURL(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	

	public void longPressAction(WebElement ele)
	{
		((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",  
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(),
						"duration",2000));
	}
	
	/* public void scrollUntilText()
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Views\"));"));
	} */
	
	public void scrollUntilEnd(WebElement ele, String direction)
	{
		boolean canScrollMore;
		do
		{
		canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", direction,
			    "percent", 1.0,
			    "speed", 5000
			));
		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement ele, String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement) ele).getId(),
		    "direction", direction,
		    "percent", 0.75,
		    "speed", 1500
		));
	}
	
	public void dragDropAction(WebElement ele)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) ele).getId(),
			    "endX", 475,
			    "endY", 424
			));
	}
	
	public Double getFormattedAmount(String amount)
	{
		Double price = Double.parseDouble(amount.substring(1));
		return price;	
	}
	
	
	@AfterClass
	public void teardown() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.quit();
		service.stop();
	}

}
