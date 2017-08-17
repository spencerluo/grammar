package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Actions;
import utils.ReadExcel;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.LoginModule.configureModules;
import static modules.LoginModule.login;
import static modules.mainModule.process;
import static modules.mainModule.release;
import static utils.Actions.*;

public class TestCite {
	@Test(dataProvider = "cite",priority=1)
	public void addCase(String caseName, String function, String action, String test) throws Exception{
		try {
			process(caseName, function, action, test);
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}
	
	@Test(priority=2)
	public void goToTest() throws Exception{
		release();
		switchTo();
		waitAndClick("loginPage", "user");
		waitAndClick("loginPage", "应用管理");
		configureModules("app5");
		waitAndClick("loginPage", "test");
	}
	
	@Test(dataProvider = "dp",priority=3)
	public void testCite(String type, String Q1, String Q2, String result) throws Exception {
		String[] questions = { Q1, Q2 };
		for (String question : questions) {
			clear("loginPage", "question");
			sendKeys("loginPage", "question", question);
			click("loginPage", "testSubmit");
			Thread.sleep(4000);
		}
		String answer = getValue("loginPage", "answer");
		Actions.assertTrue(answer.contains(result), "cite_"+Q2);
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
			switchTo();
			deleteApp("app5");
			Thread.sleep(2000);
		} finally {
			quite();
		}
	}

	@DataProvider
	public Object[][] dp() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "引用")
				.getData();
	}
	
	@DataProvider
	public Object[][] cite() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "引用数据")
				.getData();
	}
}
