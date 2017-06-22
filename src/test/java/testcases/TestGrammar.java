package testcases;

import static modules.AppModule.*;
import static modules.LoginModule.*;
import static modules.mainModule.*;
import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.getValue;
import static utils.Actions.navigate;
import static utils.Actions.openBrowser;
import static utils.Actions.quite;
import static utils.Actions.sendKeys;
import static utils.Actions.switchTo;
import static utils.Actions.waitAndClick;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ReadExcel;

public class TestGrammar {
	@Test(dataProvider = "grammar",priority=1)
	public void testGrammar(String caseName, String function, String action, String test) throws Exception{
		try {
			process(function, action, test);
		} catch (Exception e) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(2000);
			throw e;
		}
	}
	
	@Test(priority=2)
	public void  goToTest() throws Exception{
		release();
		switchTo();
		waitAndClick("loginPage", "user");
		click("loginPage", "应用管理");
		configureModules("app5");
		waitAndClick("loginPage", "test");
	}
	
	@Test(dataProvider = "corpus",priority=3)
	public void testCorpus(String function, String name, String question, String result) throws Exception {
		clear("loginPage", "question");
		sendKeys("loginPage", "question", question);
		click("loginPage", "testSubmit");
		Thread.sleep(2000);
		String answer = getValue("loginPage", "answer");
		Assert.assertEquals(answer, result);
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
		switchTo();
		deleteApp("app5");
		Thread.sleep(2000);
		quite();
	}

	@DataProvider
	public Object[][] grammar() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx","grammar").getData();
	}
	
	@DataProvider
	public Object[][] corpus() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "corpus")
				.getData();
	}
}
