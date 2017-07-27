package modules;

import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.clickByXpath;
import static utils.Actions.getText;
import static utils.Actions.sendKeys;
import static utils.Actions.waitAndClick;
import static utils.Actions.waitBeClick;
import static utils.Actions.waitElement;
import org.testng.Assert;

public class GrammarModule {

	public static void addGrammar(String name, String content, String corpus, String answer, String msg)
			throws Exception {
		waitBeClick("mainPage", "grammar");
		waitAndClick("grammarPage", "add");
		sendKeys("grammarPage", "name", name);
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (answer.equals("null")) {
			click("grammarPage", "yuyi");
		} else {
			sendKeys("grammarPage", "answer1", answer);
		}
		click("grammarPage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");

		}
//		Thread.sleep(4000);
	}

	public static void addGrammar(String name, String content, String corpus, String answer) throws Exception {
		addGrammar(name, content, corpus, answer, null);
	}
	
	public static void addGrammarError(String name, String content, String corpus, String answer, String msg) throws Exception{
		waitBeClick("mainPage", "grammar");
		waitAndClick("grammarPage", "add");
		sendKeys("grammarPage", "name", name);
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (answer.equals("null")) {
			click("grammarPage", "yuyi");
		} else {
			sendKeys("grammarPage", "answer1", answer);
		}
		click("grammarPage", "submit");
		String result = null;
		try {
			result = getText("grammarPage", "titleErrorMsg");
		} catch (Exception e) {
			try {
				result = getText("grammarPage", "contentErrorMsg");
			} catch (Exception e1) {
				try {
					result = getText("grammarPage", "corpusErrorMsg");
				} catch (Exception e2) {
					result = getText("grammarPage", "answerErrorMsg");
				}
			}
		}
		Assert.assertTrue(result.equals(msg), "expect [" + msg + "] but [" + result + "]");
	}

	public static void addChangeGrammar(String name, String content, String corpus, String answer, String msg)
			throws Exception {
		waitBeClick("mainPage", "grammar");
		waitAndClick("grammarPage", "add");
		sendKeys("grammarPage", "name", name);
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (answer.equals("null")) {
			click("grammarPage", "yuyi");
		} else {
			sendKeys("grammarPage", "answer1", answer);
		}
		click("grammarPage", "submit");
		waitElement("grammarPage", "submitChange");
		Thread.sleep(500);
		click("grammarPage", "submitChange");
		Thread.sleep(1000);
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(4000);
	}

	public static void changeGrammar(String name, String content, String corpus, String answer, String msg)
			throws Exception {
		waitBeClick("mainPage", "grammar");
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[4]/img[1]");
		clear("grammarPage", "content");
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (!answer.equals("null")) {
			clear("grammarPage", "answer1");
			sendKeys("grammarPage", "answer1", answer);
		}
		click("grammarPage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(4000);
	}

	public static void changeChangeGrammar(String name, String content, String corpus, String answer, String msg)
			throws Exception {
		waitBeClick("mainPage", "grammar");
		Thread.sleep(2000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[4]/img[1]");
		clear("grammarPage", "content");
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (!answer.equals("null")) {
			clear("grammarPage", "answer1");
			sendKeys("grammarPage", "answer1", answer);
		}
		click("grammarPage", "submit");
		waitElement("grammarPage", "submitChange");
		Thread.sleep(500);
		click("grammarPage", "submitChange");
		Thread.sleep(1000);
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(4000);

	}

	public static void deleteGrammar(String name, String msg) throws Exception {
		waitBeClick("mainPage", "grammar");
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[4]/img[2]");
		Thread.sleep(500);
		waitAndClick("grammarPage", "deleteSubmit");
		waitElement("grammarPage", "deleteMsgClose");
		Thread.sleep(500);
		String result = getText("grammarPage", "deleteMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
//		Thread.sleep(2000);
	}

	
	public static void quickAddRule(String name, String content, String msg) throws Exception{
		waitBeClick("grammarPage", "quickAddRule");
		Thread.sleep(2000);
		sendKeys("rulePage", "name", name);
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		}
	}
	
	public static void quickAddSlot(String name, String type, String subtype, String min, String max, String msg) throws Exception{
		waitBeClick("grammarPage", "quickAddSlot");
		Thread.sleep(2000);
		sendKeys("slotPage", "name", name);
		click("slotPage", type);
		if (!subtype.equals("null")) {
			click("slotPage", subtype);
		}
		clear("slotPage", "min");
		sendKeys("slotPage", "min", min);
		clear("slotPage", "max");
		sendKeys("slotPage", "max", max);
		click("slotPage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		}
	}
	
	public static void quickAddTemplate(String name, String content, String msg) throws Exception{
		waitBeClick("grammarPage", "quickAddTemplate");
		Thread.sleep(2000);
		sendKeys("templatePage", "name", name);
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");

		}
	}
}
