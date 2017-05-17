package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.getText;
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
		try {
			if (msg != null) {
				Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
			} 
		} finally {
			click("mainPage", "subMsgClose");
		}
	}
	
	public static void addTemplate(String name, String content) throws Exception {
		addTemplate(name, content, null);
	}

	public static void changeTemplate(String name, String content, String msg) throws Exception{
		waitAndClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[3]/img[1]");
		clear("templatePage", "content");
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("mainPage", "subMsgClose");
		try {
			Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
		} finally {
			click("mainPage", "subMsgClose");
		}
	}
	
	public static void deleteTemplate(String name, String msg) throws Exception{
		waitAndClick("mainPage", "template");
		Thread.sleep(1000);
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[3]/img[2]");
		click("templatePage", "submit");
		waitElement("templatePage", "deleteMsgClose");
		try {
			Assert.assertTrue(getText("templatePage", "deleteMsg").contains(msg));
		} finally {
			click("templatePage", "deleteMsgClose");
		}
	}
	
	
}
