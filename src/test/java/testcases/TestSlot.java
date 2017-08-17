package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.LoginModule.login;
import static modules.SlotModule.addSlot;
import static utils.Actions.*;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static modules.mainModule.process;

import utils.Actions;
import utils.ReadExcel;

public class TestSlot {
	@Test(dataProvider = "dp",enabled=true)
	public void testSlot(String caseName, String function, String action, String test) throws Exception {
		try {
			process(caseName, function, action, test);
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}

	@Test
	public void testLength() throws Exception{
		try {
			waitBeClick("mainPage", "slot");
			waitAndClick("slotPage", "add");
			sendKeys("slotPage", "name", "testlength");
			click("slotPage", "ext");
			clear("slotPage", "min");
			sendKeys("slotPage", "min", "30");
			clear("slotPage", "max");
			sendKeys("slotPage", "max", "10");
			click("slotPage", "submit");
			String result = getText("slotPage", "lengthErrorMsg");
			Actions.assertTrue(result.equals("最长值不能小于最短值"), "expect [最长值不能小于最短值] but [" + result + "]","slot_testLength");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_slot");
			Thread.sleep(1000);
		}
	}
	
	@Test
	public void testMinMax() throws Exception{
		try {
			addSlot("testminmax", "ext", "null", "-1", "1026");
			Thread.sleep(5000);
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_slot");
			Thread.sleep(2000);
		}
		String min = getDriver().findElement(By.xpath("//*[@title='testminmax']/following-sibling::*[4]")).getText();
		String max = getDriver().findElement(By.xpath("//*[@title='testminmax']/following-sibling::*[5]")).getText();
		Actions.assertTrue(min.equals("1")&max.equals("1024"),"slot_testMinMax");
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
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "slot").getData();
	}
}
