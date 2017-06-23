package modules;

import static utils.Actions.*;

import org.testng.Assert;

public class AppModule {

	public static void createApp(String appName, String msg) throws Exception {
		waitAndClick("mainPage", "changeApp");
		waitAndClick("modelPage", "add");
		sendKeys("modelPage", "addAppName", appName);
		click("modelPage", "submit");
		waitElement("modelPage", "closeAddNotice");
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		}
		click("modelPage", "closeAddNotice");
		Thread.sleep(1000);
	}

	public static void createApp(String appName) throws Exception {
		try {
			createApp(appName, null);
		} catch (Exception e) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(2000);
			createApp(appName, null);
		}
	}

	public static void deleteApp(String appName, String msg) throws Exception {
		waitAndClick("mainPage", "changeApp");
		clickByXpath("//*[@value='" + appName + "']/following-sibling::*/a[1]");
		waitAndClick("modelPage", "delete");
		waitAndClick("modelPage", "deleteTwice");
		waitElement("modelPage", "closeDeleteNotice");
		if (msg != null) {
			String result = getText("modelPage", "deleteMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		}
		click("modelPage", "closeDeleteNotice");
		Thread.sleep(1000);
	}

	public static void deleteApp(String appName) throws Exception {
		deleteApp(appName, null);
	}

	public static void enterApp(String appName) throws Exception {
		clickByXpath("//*[@value='" + appName + "']/following-sibling::*/a[2]");
		Thread.sleep(1000);
	}
}
