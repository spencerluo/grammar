package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitBeClick;
import static utils.Actions.waitElement;


public class TemplateModule {

	public static void addTemplate(String name, String content) throws Exception {
		waitBeClick("mainPage", "template");
		waitAndClick("templatePage", "add");
		sendKeys("templatePage", "name", name);
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("mainPage", "subMsg");
	}

	public static void addTemplateError(String name, String content) throws Exception {
		waitBeClick("mainPage", "template");
		waitAndClick("templatePage", "add");
		sendKeys("templatePage", "name", name);
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
	}

	public static void changeTemplate(String name, String content) throws Exception {
		waitBeClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/div[1]/img");
		clear("templatePage", "content");
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
	}

	public static void changeChangeTemplate(String name, String content) throws Exception {
		waitBeClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/div[1]/img");
		clear("templatePage", "content");
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("templatePage", "changeSubmit");
		Thread.sleep(500);
		click("templatePage", "changeSubmit");
		Thread.sleep(1000);
	}

	public static void deleteTemplate(String name) throws Exception {
		waitBeClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/div[2]/img");
		click("templatePage", "submit");
	}

}
