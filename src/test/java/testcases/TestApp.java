package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteCurrentApp;
import static modules.AppModule.enterApp;
import static modules.LoginModule.login;
import static modules.mainModule.process;
import static utils.Actions.openBrowser;
import static utils.Actions.quite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ReadExcel;

public class TestApp {
  @Test(dataProvider="dp")
  public void testApp(String function, String action, String caseName, String test) throws Exception {
	  process(function, action, test);
  }
  
	@BeforeClass
	public void beforeClass() throws Exception {
		openBrowser("chrome");
		login();
	}

	@AfterClass
	public void afterClass() throws Exception {
		Thread.sleep(2000);
		quite();
	}

	@DataProvider
	public Object[][] dp() throws Exception {
		return new ReadExcel("config/grammar.xlsx","app").getData();
	}
	
}
