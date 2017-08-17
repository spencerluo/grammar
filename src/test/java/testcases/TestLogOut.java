package testcases;

import static utils.Actions.*;

import java.util.Set;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Actions;

public class TestLogOut {
	@Test
	public void testLogin() throws Exception {
		navigate("http://portal.olavoice.com/open/website/login/login_show");
		sendKeys("loginPage", "username", "spencer");
		sendKeys("loginPage", "password", "asdD1234");
		sendKeys("loginPage", "yanzhengma", "abcd");
		click("loginPage", "submit");
		Thread.sleep(2000);
	}

	@Test(dependsOnMethods = "testLogin")
	public void testDocument() throws Exception {
		navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		waitAndClick("mainPage", "setting");
		click("mainPage", "document");
		switchTo();
		Actions.assertTrue(getPageSource().contains("OLAMI 文档中心"),"login_testDocument");
	}

	@Test(dependsOnMethods = "testDocument")
	public void testLogout() throws Exception {
		switchTo();
		waitAndClick("mainPage", "setting");
		click("mainPage", "logout");
		Thread.sleep(2000);
		Actions.assertTrue(getPageSource().contains("验证码"),"login_testLogout");
	}
	
	@Test(dependsOnMethods="testLogout")
	public void testChangeLogin() throws Exception{
		clear("loginPage", "username");
		sendKeys("loginPage", "username", "emmahao");
		clear("loginPage", "password");
		sendKeys("loginPage", "password", "asdf123");
		sendKeys("loginPage", "yanzhengma", "abcd");
		click("loginPage", "submit");
		Thread.sleep(2000);
		Actions.assertTrue(getText("loginPage", "user").contains("emmahao"),"login_testChangeLogin1");
		waitAndClick("loginPage", "user");
		click("loginPage", "nli");
		Set<String> handles = getDriver().getWindowHandles();
		Object[] object= handles.toArray();
		String handle = (String) object[2];
		getDriver().switchTo().window(handle);
		Thread.sleep(2000);
		Actions.assertTrue(getTitle().equals("模块"),"login_testChangeLogin2");
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		openBrowser("chrome");
	}

	@AfterClass
	public void afterClass() throws Exception {
		quite();
	}
}
