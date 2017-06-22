package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.getText;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitElement;
import static utils.Actions.navigate;
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
		Thread.sleep(500);
		try {
			if (msg != null) {
				Thread.sleep(200);
				String result = getText("mainPage", "subMsg");
				Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
				
			} 
		} finally {
			click("mainPage", "subMsgClose");
			if(!msg.equals("提交成功!")){
				navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
//				navigate("https://cn.olami.ai/open/nli/web/search_grammar");
			}
			Thread.sleep(4000);
		}
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
		Thread.sleep(500);
		click("grammarPage", "submitChange");
		Thread.sleep(1000);
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		try {
			if (msg != null) {
				Thread.sleep(200);
				String result = getText("mainPage", "subMsg");
				Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
			} 
		} finally {
			click("mainPage", "subMsgClose");
			Thread.sleep(4000);
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
		Thread.sleep(500);
		try {
			Thread.sleep(200);
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
		} finally {
			click("mainPage", "subMsgClose");
			if(!msg.equals("提交成功!")){
				navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
//				navigate("https://cn.olami.ai/open/nli/web/search_grammar");
			}
			Thread.sleep(4000);
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
		Thread.sleep(500);
		click("grammarPage", "submitChange");
		Thread.sleep(1000);
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		try {
			Thread.sleep(200);
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
		} finally {
			click("mainPage", "subMsgClose");
			Thread.sleep(4000);
		}
		
	}
	
	public static void deleteGrammar(String name, String msg) throws Exception{
		waitAndClick("mainPage", "grammar");
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[4]/img[2]");
		Thread.sleep(500);
		waitAndClick("grammarPage", "deleteSubmit");
		waitElement("grammarPage", "deleteMsgClose");
		Thread.sleep(500);
		try {
			Thread.sleep(2000);
			String result = getText("grammarPage", "deleteMsg");
			Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
		} finally {
			click("grammarPage", "deleteMsgClose");
			Thread.sleep(2000);
		}
	}
	
	public static void deleteCorpus(String name, String msg) throws Exception{
		waitAndClick("mainPage", "corpus");
		
	}
}
