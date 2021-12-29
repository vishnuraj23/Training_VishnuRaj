package SeleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

public class Assignment2 {
	/**
	 * @author VishnuRaj
	 *
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumBrowserDrivers\\chromedriver.exe");

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
		// Navigate to Add New Payee
		driver.findElement(By.partialLinkText("Add New Pay")).click();
		Thread.sleep(2000);
		// Add New Payee details
		driver.findElement(By.xpath("//input[contains(@id,'np_new_payee_nam')]")).sendKeys("Vishnu Raj");
		driver.findElement(By.cssSelector("textarea#np_new_payee_address")).sendKeys("Address of the new payee");
		driver.findElement(By.xpath("//input[@id='np_new_payee_account']")).sendKeys("Test Account");
		driver.findElement(By.xpath("//input[@name='details']")).sendKeys("Details XYZ");
		driver.findElement(By.id("add_new_payee")).click();
		Thread.sleep(2000);
		// Verify the add Payee text
		boolean VerifyAddPayee = driver.findElement(By.xpath("//div[contains(text(),'The new payee ')]")).isDisplayed();
		if (!VerifyAddPayee) {
			System.out.println("The new payee ji was successfully created." + ": Not displayed");
			Assert.fail();
		} else {
			System.out.println("The new payee ji was successfully created." + ": Is displayed");
		}
		// Sign Out
		driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[2]")).click();
		driver.findElement(By.linkText("Logout")).click();
		// Navigate to Feedback
		driver.findElement(By.xpath("//*[text()='Feedback']")).click();
		// Add Feedback
		driver.findElement(By.xpath("//input[@placeholder='Your Name']")).sendKeys("Vishnu Raj");
		driver.findElement(By.cssSelector("#email")).sendKeys("vishnu@gmail.com");
		driver.findElement(By.xpath("//*[@id='subject']")).sendKeys("Test Automation");
		driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("My question is XYZ");
		driver.findElement(By.xpath("//input[@value='Send Message']")).click();
		Thread.sleep(2000);
		// Verify the add feedback text
		boolean Verifyfeedback = driver.findElement(By.xpath("//*[contains(@class,'offset3 span6')]")).isDisplayed();
		if (!Verifyfeedback) {
			System.out.println(
					"Thank you for your comments, Vishnu Raj. They will be reviewed by our Customer Service staff and given the full attention that they deserve."
							+ ": Not displayed");
			Assert.fail();
		} else {
			System.out.println(
					"Thank you for your comments, Vishnu Raj. They will be reviewed by our Customer Service staff and given the full attention that they deserve."
							+ ": Is displayed");
		}
		// close the browser
		driver.close();
		// Quit the driver
		driver.quit();
	}

}
