package testcases;

import static utils.Actions.openBrowser;
import static utils.Actions.quite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import modules.*;
import utils.Actions;
import utils.Csv;

public class MyTest {

	@Test(dataProvider="dp")
	public void login(String name, String type, String subtype, String min, String max) throws Exception {
		SlotModule.addSlot(name, type, subtype, min, max);
		Thread.sleep(2000);
	}
	
	@BeforeClass
	public void berforeClass() throws Exception{
		openBrowser("chrome");
		LoginModule.login();
		AppModule.createApp("app5");
		AppModule.enterApp("app5");
		Thread.sleep(2000);
		
	}
	
	@AfterClass
	public void afterClass() throws Exception{
		AppModule.deleteCurrentApp();
		Thread.sleep(2000);
		quite();
		
	}
	
	@DataProvider
	public Object[][] dp() throws Exception{
		return Csv.readCsv("config\\slot.csv");
	}
}
