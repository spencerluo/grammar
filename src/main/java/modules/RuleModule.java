package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.getText;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitElement;

import org.testng.Assert;


public class RuleModule {

	public static void addRule(String name, String content, String msg) throws Exception {
		waitAndClick("mainPage", "rule");
		waitAndClick("rulePage", "add");
		sendKeys("rulePage", "name", name);
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		waitElement("mainPage", "subMsgClose");
		try {
			if (msg != null) {
				Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
			} 
		} finally {
			click("mainPage", "subMsgClose");
		}
	}
	
	public static void addRule(String name, String content) throws Exception {
		addRule(name, content, null);
	}

	public static void changeRule(String name, String content, String msg) throws Exception{
		waitAndClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[3]/img[1]");
		clear("rulePage", "content");
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		waitElement("mainPage", "subMsgClose");
		try {
//			System.out.println( getText("mainPage", "subMsg"));
			Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
		} finally {
			click("mainPage", "subMsgClose");
		}
	}
	
	public static void deleteRule(String name, String msg) throws Exception{
		waitAndClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[3]/img[2]");
		click("rulePage", "submit");
		waitElement("rulePage", "deleteMsgClose");
		try {
			Assert.assertTrue(getText("rulePage", "deleteMsg").contains(msg));
		} finally {
			click("rulePage", "deleteMsgClose");
		}
	}
}
