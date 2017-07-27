package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.getText;
import static utils.Actions.navigate;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitBeClick;
import static utils.Actions.waitElement;

import org.testng.Assert;

public class RuleModule {

	public static void addRule(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "rule");
		waitAndClick("rulePage", "add");
		sendKeys("rulePage", "name", name);
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		}
//		Thread.sleep(1000);
	}

	public static void addRule(String name, String content) throws Exception {
		addRule(name, content, null);
	}
	
	public static void addRuleError(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "rule");
		waitAndClick("rulePage", "add");
		sendKeys("rulePage", "name", name);
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		Thread.sleep(500);
		String result = null;
		try {
			result = getText("rulePage", "titleErrorMsg");
		} catch (Exception e) {
			result = getText("rulePage", "contentErrorMsg");
		}
		Assert.assertTrue(result.equals(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);
	}

	public static void changeRule(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[1]");
		clear("rulePage", "content");
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);
	}

	public static void changeChangeRule(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[1]");
		clear("rulePage", "content");
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		waitElement("rulePage", "changeSubmit");
		Thread.sleep(500);
		click("rulePage", "changeSubmit");
		Thread.sleep(1000);
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);
	}

	public static void deleteRule(String name, String msg) throws Exception {
		waitBeClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[2]");
		click("rulePage", "submit");
		waitElement("rulePage", "deleteMsgClose");
		Thread.sleep(500);
		String result = getText("rulePage", "deleteMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);
	}
}
