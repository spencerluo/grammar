package modules;
import static utils.Actions.*;

import org.testng.Assert;



public class AppModule {
	
	public static void createApp(String appName, String msg) throws Exception{
		waitAndClick("mainPage", "changeApp");
		waitAndClick("modelPage", "add");
		sendKeys("modelPage", "addAppName",appName);
		click("modelPage", "submit");
		waitElement("modelPage","closeAddNotice");
		try {
			if (msg != null) {
				String result = getText("mainPage", "subMsg");
				Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
			} 
		} finally {
			click("modelPage","closeAddNotice");
		}
	}
	
	public static void createApp(String appName) throws Exception{
		createApp(appName, null);
	}
	
	
	public static void deleteApp(String appName, String msg) throws Exception{
		waitAndClick("mainPage", "changeApp");
		clickByXpath("//*[@value='"+appName+"']/following-sibling::*/a[1]");
		waitAndClick("modelPage", "delete");
		waitAndClick("modelPage", "deleteTwice");
		waitElement("modelPage","closeDeleteNotice");
		try {
			if (msg != null) {
				String result = getText("modelPage", "deleteMsg");
				Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
			} 
		} finally {
			click("modelPage","closeDeleteNotice");
		}
	}
	
	public static void deleteApp(String appName) throws Exception{
		deleteApp(appName, null);
	}
	
	public static void enterApp(String appName) throws Exception {
		clickByXpath("//*[@value='"+appName+"']/following-sibling::*/a[2]");
	}
}
