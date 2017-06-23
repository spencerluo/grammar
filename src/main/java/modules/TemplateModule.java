package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.getText;
import static utils.Actions.navigate;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitElement;

import org.testng.Assert;

public class TemplateModule {

	public static void addTemplate(String name, String content, String msg) throws Exception {
		waitAndClick("mainPage", "template");
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
		click("mainPage", "subMsgClose");
		if (!msg.equals("提交成功!")) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		}
		Thread.sleep(1000);
	}

	public static void addTemplate(String name, String content) throws Exception {
		addTemplate(name, content, null);
	}

	public static void changeTemplate(String name, String content, String msg) throws Exception {
		waitAndClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[1]");
		clear("templatePage", "content");
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		click("mainPage", "subMsgClose");
		if (!msg.equals("提交成功!")) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		}
		Thread.sleep(1000);
	}

	public static void changeChangeTemplate(String name, String content, String msg) throws Exception {
		waitAndClick("mainPage", "template");
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
		click("mainPage", "subMsgClose");
		if (!msg.equals("提交成功!")) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		}
		Thread.sleep(1000);

	}

	public static void deleteTemplate(String name, String msg) throws Exception {
		waitAndClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[3]/img[2]");
		click("templatePage", "submit");
		waitElement("templatePage", "deleteMsgClose");
		Thread.sleep(500);
		String result = getText("templatePage", "deleteMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		click("templatePage", "deleteMsgClose");
		Thread.sleep(1000);
	}

}
