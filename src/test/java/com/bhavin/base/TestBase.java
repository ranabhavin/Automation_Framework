package com.bhavin.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.bhavin.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	/*
	 * Initialization
	 * 
	 * WebDriver Properties Logs DB Excel Mail
	 */

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static ExtentTest parentTests;

	@BeforeSuite
	public void loadDirectories() throws IOException {

		// Config = new Properties();
		fis = new FileInputStream("./src/test/resources/properties/Config.properties");
		Config.load(fis);

		// OR = new Properties();
		fis = new FileInputStream("./src/test/resources/properties/OR.properties");
		OR.load(fis);

		/*
		 * driver.get(Config.getProperty("testSiteURL"));
		 * 
		 * System.out.println(driver.getTitle());
		 */

	}

	@BeforeMethod
	public void setup() {

		if (Config.getProperty("browser").equals("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		} else if (Config.getProperty("browser").equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		} else if (Config.getProperty("browser").equalsIgnoreCase("IE")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {

			WebDriverManager.chromedriver().setup();

			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);

		wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));

	}

	/**
	 * Open URL.
	 *
	 * @param url the url
	 */
	public static void openURL(String url) {

		driver.get(Config.getProperty(url));

	}

	/**
	 * Type.
	 *
	 * @param locator the locator
	 * @param value the value
	 */
	public static void type(String locator, String value) {

		if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).clear();
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);

		} else if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);

		} else if (locator.endsWith("_NAME")) {

			driver.findElement(By.name(OR.getProperty(locator))).clear();
			driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);

		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).clear();
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);

		}

	}

	/**
	 * Click.
	 *
	 * @param locator the locator
	 */
	public static void click(String locator) {

		if (locator.endsWith("_XPATH")) {

			driver.findElement(By.xpath(OR.getProperty(locator))).click();

		} else if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_NAME")) {

			driver.findElement(By.name(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_LINKTEXT")) {

			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}

	}

	/**
	 * Checks if is element enabled.
	 *
	 * @param locator the locator
	 * @return true, if is element enabled
	 */
	public static boolean isElementEnabled(String locator) {

		if (locator.endsWith("_XPATH")) {

			if (driver.findElement(By.xpath(OR.getProperty(locator))).isEnabled() == true) {
				return true;
			} else {
				return false;
			}

		} else if (locator.endsWith("_CSS")) {

			driver.findElement(By.cssSelector(OR.getProperty(locator))).isEnabled();
			return true;
		} else if (locator.endsWith("_NAME")) {

			driver.findElement(By.name(OR.getProperty(locator))).isEnabled();
			return true;
		} else if (locator.endsWith("_ID")) {

			driver.findElement(By.id(OR.getProperty(locator))).isEnabled();
			return true;
		} else {
			System.out.println("Element not found");
			return false;
		}

	}

	/**
	 * Gets the text.
	 *
	 * @param locator the locator
	 * @return the text
	 */
	public static String getText(String locator) {

		if (locator.endsWith("_XPATH"))

			return driver.findElement(By.xpath(OR.getProperty(locator))).getText();
		else if (locator.endsWith("_ID"))
			return driver.findElement(By.id(locator)).getText();
		else if (locator.endsWith("_NAME"))
			return driver.findElement(By.name(locator)).getText();
		else if (locator.endsWith("_CSS"))
			return driver.findElement(By.cssSelector(locator)).getText();

		return null;

	}

	@AfterSuite
	public void quit() {

		driver.quit();

	}

}
