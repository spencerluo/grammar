package testcases;

import static modules.LoginModule.login;
import static modules.mainModule.process;
import static utils.Actions.navigate;
import static utils.Actions.openBrowser;
import static utils.Actions.quite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.ReadExcel;

public class TestApp {
  @Test(dataProvider="dp")
  public void testApp(String caseName, String function, String action, String test) throws Exception {
	  try {
		process(caseName, function, action, test);
	} finally {
		navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		Thread.sleep(1000);
	}
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
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx","app").getData();
	}
	
}
