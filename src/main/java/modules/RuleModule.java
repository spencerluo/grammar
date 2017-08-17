package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitBeClick;
import static utils.Actions.waitElement;


public class RuleModule {

	public static void addRule(String name, String content) throws Exception {
		waitBeClick("mainPage", "rule");
		waitAndClick("rulePage", "add");
		sendKeys("rulePage", "name", name);
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
	}

	public static void addRuleError(String name, String content) throws Exception {
		waitBeClick("mainPage", "rule");
		waitAndClick("rulePage", "add");
		sendKeys("rulePage", "name", name);
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		Thread.sleep(200);
	}

	public static void changeRule(String name, String content) throws Exception {
		waitBeClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/div[1]/img");
		clear("rulePage", "content");
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
	}

	public static void changeChangeRule(String name, String content) throws Exception {
		waitBeClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/div[1]/img");
		clear("rulePage", "content");
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		waitElement("rulePage", "changeSubmit");
		Thread.sleep(500);
		click("rulePage", "changeSubmit");
		Thread.sleep(1000);
	}

	public static void deleteRule(String name) throws Exception {
		waitBeClick("mainPage", "rule");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/div[2]/img");
		click("rulePage", "submit");
	}
}
