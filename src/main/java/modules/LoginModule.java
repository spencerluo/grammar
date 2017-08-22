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
		Thread.sleep(1000);
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
	
	public static void configureModules(String appName, String priorityValue) throws Exception {
		waitAndClick("loginPage", "配置模块");
		Thread.sleep(2000);
		List<WebElement>  apps= getDriver().findElements(By.xpath("//*[@id='ct1']/span/label/input"));
		List<WebElement> priority = getDriver().findElements(By.xpath("//*[@id='ct1']/span/input"));
		boolean flag = false;
		int i = 0;
		for(WebElement app: apps){
			if(app.getAttribute("value").equals(appName)){
				try {
					priority.get(i).clear();
					priority.get(i).sendKeys(priorityValue);
				} catch (Exception e) {
					app.click();
					priority.get(i).clear();
					priority.get(i).sendKeys(priorityValue);
				}
				click("loginPage", "configureSubmit");
				Thread.sleep(2000);
				flag = true;
				break;
			}
			i +=1;
		}
		if (!flag) {
			throw new Exception(appName + " is not exist");
		}
	}
}
