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

public class TemplateModule {

	public static void addTemplate(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "template");
		waitAndClick("templatePage", "add");
		sendKeys("templatePage", "name", name);
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");

		}
//		Thread.sleep(1000);
	}

	public static void addTemplate(String name, String content) throws Exception {
		addTemplate(name, content, null);
	}
	
	public static void addTemplateError(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "template");
		waitAndClick("templatePage", "add");
		sendKeys("templatePage", "name", name);
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		Thread.sleep(500);
		String result = null;
		try {
			result = getText("templatePage", "titleErrorMsg");
		} catch (Exception e) {
			result = getText("templatePage", "contentErrorMsg");
		}
		Assert.assertTrue(result.equals(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);
	}

	public static void changeTemplate(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[1]");
		clear("templatePage", "content");
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);
	}

	public static void changeChangeTemplate(String name, String content, String msg) throws Exception {
		waitBeClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[1]");
		clear("templatePage", "content");
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("templatePage", "changeSubmit");
		Thread.sleep(500);
		click("templatePage", "changeSubmit");
		Thread.sleep(1000);
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);

	}

	public static void deleteTemplate(String name, String msg) throws Exception {
		waitBeClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[2]");
		click("templatePage", "submit");
		waitElement("templatePage", "deleteMsgClose");
		Thread.sleep(500);
		String result = getText("templatePage", "deleteMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(1000);
	}

}
