package testcases;

import org.testng.annotations.Test;

import utils.Csv;
import utils.ReadExcel;

import org.testng.annotations.BeforeMethod;
import static modules.GrammarModule.*;
import static modules.AppModule.createApp;
import static modules.AppModule.deleteCurrentApp;
import static modules.AppModule.enterApp;
import static modules.LoginModule.login;
import static modules.SlotModule.addSlot;
import static modules.mainModule.process;
import static utils.Actions.openBrowser;
import static utils.Actions.quite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class TestGrammar {
	@Test(dataProvider = "dp")
	public void testGrammar(String function, String action, String caseName, String test) throws Exception {
		process(function, action, test);
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
		deleteCurrentApp();
		Thread.sleep(2000);
		quite();
	}

	@DataProvider
	public Object[][] dp() throws Exception {
		return new ReadExcel(System.getProperty("user.dir")+"\\config\\grammar.xlsx","grammar").getData();
	}
}
