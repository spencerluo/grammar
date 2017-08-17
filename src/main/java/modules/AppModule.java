package modules;

import static utils.Actions.*;


public class AppModule {

	public static void createApp(String appName) throws Exception {
		waitBeClick("mainPage", "changeApp");
		waitAndClick("modelPage", "add");
		sendKeys("modelPage", "addAppName", appName);
		click("modelPage", "submit");
	}
	
	public static void createAppError(String appName) throws Exception{
		waitBeClick("mainPage", "changeApp");
		waitAndClick("modelPage", "add");
		sendKeys("modelPage", "addAppName", appName);
		click("modelPage", "submit");
	}


	public static void deleteApp(String appName) throws Exception {
		waitBeClick("mainPage", "changeApp");
		clickByXpath("//*[@value='" + appName + "']/following-sibling::*/a[4]");
		waitAndClick("modelPage", "delete");
		waitAndClick("modelPage", "deleteTwice");
	}


	public static void enterApp(String appName) throws Exception {
		Thread.sleep(6000);
		clickByXpath("//*[@value='" + appName + "']/following-sibling::*/a[1]");
		Thread.sleep(1000);
	}
	
	public static void importApp(String appName) throws Exception{
		waitBeClick("mainPage", "changeApp");
		waitAndClick("modelPage", "import");
		clickByXpath("//*[@value='" + appName + "']");
		click("modelPage", "导入");
	}
}
