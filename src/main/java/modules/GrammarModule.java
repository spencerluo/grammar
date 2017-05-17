package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.getText;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitElement;

import org.testng.Assert;


public class GrammarModule {

	public static void addGrammar(String name, String content, String corpus, String answer, String msg) throws Exception {
		waitAndClick("mainPage", "grammar");
		waitAndClick("grammarPage", "add");
		sendKeys("grammarPage", "name", name);
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if(answer.equals("null")){
			click("grammarPage", "yuyi");
		}
		else{
			sendKeys("grammarPage", "answer", answer);
		}
		click("grammarPage", "submit");
		waitElement("mainPage", "subMsgClose");
		try {
			if (msg != null) {
				Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
			} 
		} finally {
			click("mainPage", "subMsgClose");
		}
		Thread.sleep(2000);
	}
	
	public static void addGrammar(String name, String content, String corpus, String answer) throws Exception {
		addGrammar(name, content, corpus, answer, null);
	}
	
	public static void addChangeGrammar(String name, String content, String corpus, String answer, String msg) throws Exception {
		waitAndClick("mainPage", "grammar");
		waitAndClick("grammarPage", "add");
		sendKeys("grammarPage", "name", name);
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if(answer.equals("null")){
			click("grammarPage", "yuyi");
		}
		else{
			sendKeys("grammarPage", "answer", answer);
		}
		click("grammarPage", "submit");
		waitElement("grammarPage", "submitChange");
		try {
			if (msg != null) {
				Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
			} 
		} finally {
			click("grammarPage", "submitChange");
			Thread.sleep(5000);
		}
	}

	public static void changeGrammar(String name, String content, String corpus, String answer, String msg) throws Exception{
		waitAndClick("mainPage", "grammar");
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[4]/img[1]");
		clear("grammarPage", "content");
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (!answer.equals("null")) {
			clear("grammarPage", "answer");
			sendKeys("grammarPage", "answer", answer);
		}
		click("grammarPage", "submit");
		waitElement("mainPage", "subMsgClose");
		try {
			Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
		} finally {
			click("mainPage", "subMsgClose");
			Thread.sleep(2000);
		}
	}
	
	public static void changeChangeGrammar(String name, String content, String corpus, String answer, String msg) throws Exception{
		waitAndClick("mainPage", "grammar");
		Thread.sleep(2000);
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[4]/img[1]");
		clear("grammarPage", "content");
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (!answer.equals("null")) {
			clear("grammarPage", "answer");
			sendKeys("grammarPage", "answer", answer);
		}
		click("grammarPage", "submit");
		waitElement("grammarPage", "submitChange");
		try {
			Assert.assertTrue(getText("mainPage", "subMsg").contains(msg));
		} finally {
			click("grammarPage", "submitChange");
			Thread.sleep(5000);
		}
		
	}
	
	public static void deleteGrammar(String name, String msg) throws Exception{
		waitAndClick("mainPage", "grammar");
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[4]/img[2]");
		waitAndClick("grammarPage", "deleteSubmit");
		waitElement("grammarPage", "deleteMsgClose");
		try {
			Assert.assertTrue(getText("grammarPage", "deleteMsg").contains(msg));
		} finally {
			click("grammarPage", "deleteMsgClose");
			Thread.sleep(2000);
		}
	}
}
