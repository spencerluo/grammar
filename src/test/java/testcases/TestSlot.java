package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.LoginModule.login;
import static utils.Actions.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static modules.mainModule.process;
import utils.ReadExcel;

public class TestSlot {
	@Test(dataProvider = "dp")
	public void testSlot(String caseName, String function, String action, String test) throws Exception {
		try {
			process(function, action, test);
		} catch (Exception e) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(2000);
			throw e;
		}
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
		deleteApp("app5");
		Thread.sleep(2000);
		quite();
	}

	@DataProvider
	public Object[][] dp() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "slot").getData();
	}
}
