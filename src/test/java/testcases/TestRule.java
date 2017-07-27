package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.LoginModule.login;
import static modules.mainModule.process;
import static utils.Actions.click;
import static utils.Actions.navigate;
import static utils.Actions.openBrowser;
import static utils.Actions.quite;
import static utils.Actions.sendKeys;
import static utils.Actions.getDriver;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitBeClick;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ReadExcel;

public class TestRule {

	@Test(dataProvider = "dp",priority=1)
	public void testRule(String caseName, String function, String action, String test) throws Exception {
		try {
			process(function, action, test);
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}

	@Test
	public void cancelAddRule() throws Exception {
		waitBeClick("mainPage", "rule");
		waitAndClick("rulePage", "add");
		sendKeys("rulePage", "name", "rule4");
		sendKeys("rulePage", "content", "rule4");
		click("rulePage", "cancel");
	}

	@Test(priority=2)
	public void searchRule() throws Exception{
		waitBeClick("mainPage", "rule");
		sendKeys("rulePage", "searchBox", "_nihao");
		click("rulePage", "searchSubmit");
		Thread.sleep(1000);
		String result = getDriver().findElement(By.xpath("//*[@id='subform']/div[2]/table/tbody/tr/td[4]/span/span")).getText();
		Assert.assertTrue(result.equals("_nihao"));
	}
	
	@BeforeClass
	public void beforeClass() throws Exception {
		openBrowser("chrome");
		login();
		createApp("app5");
		enterApp("app5");
	}

	@AfterClass
	public void afterClass() throws Exception {
		try {
			deleteApp("app5");
			Thread.sleep(2000);
		} finally {
			quite();
		}
	}

	@DataProvider
	public Object[][] dp() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "rule")
				.getData();
	}

}
