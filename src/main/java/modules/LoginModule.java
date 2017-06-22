package modules;

import static utils.Actions.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginModule {

	public static void login() throws Exception {

		navigate("http://portal.olavoice.com/open/website/login/login_show");
//		navigate("https://cn.olami.ai/open/website/login/login_show");
		sendKeys("loginPage", "username", "spencer");
		sendKeys("loginPage", "password", "asdD1234");
		sendKeys("loginPage", "yanzhengma", "abcd");
//		Thread.sleep(5000);
		click("loginPage", "submit");
		waitAndClick("loginPage", "user");
		clickAndSwitch("loginPage", "nli");
		Thread.sleep(2000);
	}
	
	public static void applicantManage() throws Exception {

		navigate("http://portal.olavoice.com/open/website/login/login_show");
//		navigate("https://cn.olami.ai/open/website/login/login_show");
		sendKeys("loginPage", "username", "spencer");
		sendKeys("loginPage", "password", "asdD1234");
		sendKeys("loginPage", "yanzhengma", "abcd");
//		Thread.sleep(5000);
		click("loginPage", "submit");
		waitAndClick("loginPage", "user");
		click("loginPage", "应用管理");
	}
	
	public static void configureModules(String appName) throws Exception {
		waitAndClick("loginPage", "配置模块");
		Thread.sleep(5000);
		getDriver().findElements(By.xpath("//*[@value='"+appName+"']")).get(1).click();
		click("loginPage", "configureSubmit");
		Thread.sleep(2000);
	}
}
