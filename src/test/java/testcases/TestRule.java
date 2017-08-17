package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.LoginModule.login;
import static modules.mainModule.process;
import static utils.Actions.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Actions;
import utils.ReadExcel;

public class TestRule {

	@Test(dataProvider = "dp",priority=1)
	public void testRule(String caseName, String function, String action, String test) throws Exception {
		try {
			process(caseName, function, action, test);
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
		Actions.assertTrue(getPageSource().contains("_nihao"),"rule_searchRule");
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
