package selenium.tests;

import static org.junit.Assert.assertFalse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class ListSuccess {

	
		
		private static WebDriver driver;
			
			@BeforeClass
			public static void setUp() throws Exception 
			{
				//driver = new HtmlUnitDriver();
				ChromeDriverManager.getInstance().setup();
				driver = new ChromeDriver();
			}
			
			@AfterClass
			public static void  tearDown() throws Exception
			{
				driver.close();
				driver.quit();
			}
			@Test
			public void postMessage()
			{
				driver.get("https://csc510testingslackbot.slack.com/");

				// Wait until page loads and we can see a sign in button.
				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signin_btn")));

				// Find email and password fields.
				WebElement email = driver.findElement(By.id("email"));
				WebElement pw = driver.findElement(By.id("password"));

				// Type in our test user login info.
				email.sendKeys("buildslacker@gmail.com");
				pw.sendKeys("Buildslacker123");

				// Click
				WebElement signin = driver.findElement(By.id("signin_btn"));
				signin.click();

				// Wait until we go to general channel.
				wait.until(ExpectedConditions.titleContains("general"));

				// Switch to #bots channel and wait for it to load.
				driver.get("https://csc510testingslackbot.slack.com/messages/general");
				wait.until(ExpectedConditions.titleContains("general"));

				// Type something
				WebElement messageBot = driver.findElement(By.id("message-input"));
				messageBot.sendKeys("@bsbot change project");
				messageBot.sendKeys(Keys.RETURN);
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				messageBot.sendKeys("TestFolder1");
				messageBot.sendKeys(Keys.RETURN);
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				messageBot.sendKeys("@bsbot list dependencies");
				messageBot.sendKeys(Keys.RETURN);

				//wait.withTimeout(3, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class);
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				/*messageBot.sendKeys("abcd");
				messageBot.sendKeys(Keys.RETURN);

				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//wait.withTimeout(3, TimeUnit.SECONDS).ignoring(StaleElementReferenceException.class);
				List<WebElement> msg = driver.findElements(By.xpath("//span[@class='message_body']"));
				int i= msg.size();
				assertEquals("The following dependencies can be updated to the versions listed:\n" + 
				"io.dropwizard.metrics:metrics-core:3.1.0:[3.1.1, 3.1.2]\n" + 
				"com.beust:jcommander:1.58:[]\n" + 
				"junit:junit:3.8.1:[3.8.2, 4.0, 4.2, 4.8.2, 4.10, 4.12]", msg.get(i-1).getText());
			}
		




}
