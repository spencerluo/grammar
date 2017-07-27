package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {

	private static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	public static void openBrowser(String browser) throws Exception {
		Locator.init();
		switch (browser) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "ie":
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			caps.setCapability("ignoreZoomSetting", true);
			driver = new InternetExplorerDriver(caps);
			break;

		default:
			Log.error("no such Browser");
			throw new Exception("not such browser");
		}
		driver.manage().window().maximize();
		Log.info("open " + browser + " browser");
	}

	public static void navigate(String url) throws Exception {
		try {
			driver.get(url);
			Log.info("navigate to [" + url + "]");
		} catch (Exception e) {
			Log.error("can't nvigate to [" + url + "]");
			throw new Exception("can't nvigate to [" + url + "]");
		}
	}

	public static WebElement getElement(String pageName, String objectName) throws Exception {
		WebElement element = null;
		try {
			element = driver.findElement(Locator.getLocator(pageName, objectName));
		} catch (Exception e) {
			Log.error("can't locate [" + objectName + "] in [" + pageName + "]");
			throw new Exception("can't locate [" + objectName + "] in [" + pageName + "]");
		}
		return element;
	}

	public static void click(String pageName, String objectName) throws Exception {
		WebElement element = null;
		try {
			element = getElement(pageName, objectName);
			element.click();
			Log.info("click [" + objectName + "] in [" + pageName + "]");
		} catch (Exception e) {
			Log.error("can't click [" + objectName + "] in [" + pageName + "]");
			throw new Exception("can't click [" + objectName + "] in [" + pageName + "]");
		}
	}

	public static void waitAndClick(String pageName, String objectName) throws Exception {
		try {
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.presenceOfElementLocated(Locator.getLocator(pageName, objectName)));
		} catch (Exception e) {
			throw new Exception("[" + objectName + "] in [" + pageName + "] don't appear at 10s");
		}
		click(pageName, objectName);
	}
	
	public static void waitBeClick(String pageName, String objectName) throws Exception {
		final String page = pageName;
		final String object = objectName;
		try {
			new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver input) {
					boolean b =false;
					try {
						getElement(page, object).click();
						b = true;
					} catch (Exception e) {
					}
					return b;
				}
			});
		} catch (Exception e) {
			throw new Exception("[" + objectName + "] in [" + pageName + "] can't be click at 10s");
		}
	}

	public static void clickAndSwitch(String pageName, String objectName) throws Exception {
		String currentHandle = getDriver().getWindowHandle();
		click(pageName, objectName);
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(currentHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}
	
	public static void switchTo() throws Exception {
		String currentHandle = getDriver().getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(currentHandle)) {
				driver.switchTo().window(handle);
				break;
			}
		}
	}

	public static void clickByXpath(String xpath) {
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
	}

	public static void clear(String pageName, String objectName) throws Exception {
		try {
			getElement(pageName, objectName).clear();
			Log.info("clear [" + objectName + "] in [" + pageName + "]");
		} catch (Exception e) {
			Log.error("can't clear [" + objectName + "] in [" + pageName + "]");
			throw new Exception("can't clear [" + objectName + "] in [" + pageName + "]");
		}
	}

	public static void sendKeys(String pageName, String objectName, String data) throws Exception {
		try {
			getElement(pageName, objectName).sendKeys(data);
			;
			Log.info("send [" + data + "] to [" + objectName + "] in [" + pageName + "]");
		} catch (Exception e) {
			Log.error("can't send [" + data + "] to [" + objectName + "] in [" + pageName + "]");
			throw new Exception("can't send [" + data + "] to [" + objectName + "] in [" + pageName + "]");
		}
	}

	public static void quite() {
		driver.quit();
		Log.info("close the browser");
	}

	public static void switchToFrame(String pageName, String objectName) throws Exception {
		try {
			WebElement frame = getElement(pageName, objectName);
			driver.switchTo().frame(frame);
			Log.info("switch to the frame [" + objectName + "] in [" + pageName + "]");
		} catch (Exception e) {
			Log.error("can't switch to the frame [" + objectName + "] in [" + pageName + "]");
			throw new Exception("can't switch to the frame [" + objectName + "] in [" + pageName + "]");
		}
	}

	public static void switchToDefaultFrame() {
		try {
			driver.switchTo().defaultContent();
			Log.info("switch to default frame");
		} catch (Exception e) {
			Log.error("can't switch to default frame");
		}
	}

	public static String getPageSource() {
		return driver.getPageSource();
	}

	public static String getTitle() {
		return driver.getTitle();
	}

	public static void takeScreenShot(String name) {
		TakesScreenshot tss = (TakesScreenshot) driver;
		File file = tss.getScreenshotAs(OutputType.FILE);
		String date = getDate();
		try {
			FileUtils.copyFile(file, new File("photos\\" + name + "_" + date + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String s = sdf.format(date);
		return s;
	}

	public static String getText(String pageName, String objectName) throws Exception {
		String text = null;
		try {
			WebElement element = getElement(pageName, objectName);
			text = element.getText();
			Log.info("get  the " + text + " from [" + objectName + "] in [" + pageName + "]");
		} catch (Exception e) {
			Log.error("can't get  the text from [" + objectName + "] in [" + pageName + "]");
			throw new Exception("can't get  the text from  [" + objectName + "] in [" + pageName + "]");
		}
		return text;
	}

	public static String getValue(String pageName, String objectName) throws Exception {
		String value = null;
		try {
			WebElement element = getElement(pageName, objectName);
			value = element.getAttribute("value");
			Log.info("get  the " + value + " from [" + objectName + "] in [" + pageName + "]");
		} catch (Exception e) {
			Log.error("can't get  the value from [" + objectName + "] in [" + pageName + "]");
			throw new Exception("can't get  the value from  [" + objectName + "] in [" + pageName + "]");
		}
		return value;
	}

	public static void waitElement(String pageName, String objectName) throws Exception {
		try {
			new WebDriverWait(driver, 10)
					.until(ExpectedConditions.presenceOfElementLocated(Locator.getLocator(pageName, objectName)));
		} catch (Exception e) {
			throw new Exception("[" + objectName + "] in [" + pageName + "] don't appear at 10s");
		}
	}
	
	public static void scrollToBase() throws Exception{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("scroll(0,3000);");

	}
	
	public static JavascriptExecutor getJS(){
		JavascriptExecutor js=(JavascriptExecutor)driver;
		return js;
	}
}
