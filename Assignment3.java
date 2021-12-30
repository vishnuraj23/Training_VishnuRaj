/**
 * 
 */
package SeleniumBasics;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

/**
 * @author Vishnu Raj
 *
 */
public class Assignment3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserdrivers\\chromedriver.exe");

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("incognito");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		WebDriver driver = new ChromeDriver(options);
		// maximize the window
		driver.manage().window().maximize();
		// open url
		driver.get("http://zero.webappsecurity.com/");
		// Login to Zero Bank
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.name("user_login")).sendKeys("username");
		driver.findElement(By.cssSelector("input[name='user_password']")).sendKeys("password");
		driver.findElement(By.xpath("//*[@value='Sign in']")).click();
		// Browser Advance option
		if (driver.findElement(By.id("details-button")).isDisplayed()) {
			driver.findElement(By.id("details-button")).click();
			driver.findElement(By.id("proceed-link")).click();
		}
		// Navigate to Pay bill
		driver.findElement(By.linkText("Pay Bills")).click();
		// Navigate to Purchase Foreign Currency
		Thread.sleep(1000);
		driver.findElement(By.partialLinkText("Purchase Foreign Currency")).click();
		// Keeping all the field empty and click on 'purchase' button to handle Alert.
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='Purchase']")).click();
		String alert = driver.switchTo().alert().getText();
		Assert.assertEquals(alert,"Please, ensure that you have filled all the required fields with valid values.","Alert text is not matching");
		System.out.println(alert);
		driver.switchTo().alert().accept();
		// Sign Out
		driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[2]")).click();
		driver.findElement(By.linkText("Logout")).click();
		
		
		
		//Navigate to W3schools
		driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm");
		String W3school =driver.getCurrentUrl();
		Assert.assertEquals(W3school, "https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_confirm","URL is not matching");
		System.out.println(W3school);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		String alertw3s = driver.switchTo().alert().getText();
		Assert.assertEquals(alertw3s,"Press a button!","Alert text is not matching");
		System.out.println(alertw3s);
		driver.switchTo().alert().accept();
		
		//Navigating to Naukri site
		driver.get("https://www.naukri.com/");
		Thread.sleep(4000);
		driver.findElement(By.xpath("//button[text()='GOT IT']")).click();
		Thread.sleep(5000);
		String NaukriURL =driver.getCurrentUrl();
		Assert.assertEquals(NaukriURL, "https://www.naukri.com/","URL is not matching");
		System.out.println(NaukriURL);
		//confirmation pop-up handling on home page 
		Thread.sleep(8000);
		String text = driver.findElement(By.xpath("//p[@class='caption']")).getText();
		Assert.assertEquals(text, "Share your location with Naukri.com for more relevant jobs","The displayed text is not matching");
		System.out.println(text);
		String text1 = driver.findElement(By.xpath("//p[@class='desc']")).getText();
		Assert.assertEquals(text1, "You can turn them off anytime from browser settings","The displayed text is not matching");
		System.out.println(text1);
		driver.findElement(By.xpath("//span[contains(text(), 'Later')]")).click();
		Thread.sleep(2000);
		//
		String windowHandle = driver.getWindowHandle();
		System.out.println(windowHandle);
		driver.findElement(By.xpath("//img[contains(@src,'/cognizant-hs-tp-21sep2018.gif')]")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandleList = new ArrayList<>(windowHandles);
		
		driver.switchTo().window(windowHandleList.get(1));
		String title =driver.getTitle();
		Assert.assertEquals(title, "Cognizant Jobs - Career Opportunities in Cognizant - Naukri.com","The displayed title is not matching");
		System.out.println(title);
		Thread.sleep(2000);

		driver.switchTo().window(windowHandleList.get(0));

		Thread.sleep(2000);


		// close the browser
		driver.close();
		// Quit the driver
		driver.quit();
	}

}
