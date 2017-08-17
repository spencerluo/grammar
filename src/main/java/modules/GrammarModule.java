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

	public static void addGrammar(String name, String content, String corpus, String answer)
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
	}

	public static void addGrammarError(String name, String content, String corpus, String answer) throws Exception{
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
	}

	public static void addChangeGrammar(String name, String content, String corpus, String answer)
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
	}

	public static void changeGrammar(String name, String content, String corpus, String answer)
			throws Exception {
		waitBeClick("mainPage", "grammar");
		click("grammarPage", "sortButton");
		click("grammarPage", "sortByChange");
		Thread.sleep(2000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[4]/div[1]/img");
		clear("grammarPage", "content");
		sendKeys("grammarPage", "content", content);
		sendKeys("grammarPage", "corpus", corpus);
		if (!answer.equals("null")) {
			clear("grammarPage", "answer1");
			sendKeys("grammarPage", "answer1", answer);
		}
		click("grammarPage", "submit");
	}

	public static void changeChangeGrammar(String name, String content, String corpus, String answer)
			throws Exception {
		waitBeClick("mainPage", "grammar");
		click("grammarPage", "sortButton");
		waitAndClick("grammarPage", "sortByChange");
		Thread.sleep(2000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[4]/div[1]/img");
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
	}

	public static void deleteGrammar(String name) throws Exception {
		waitBeClick("mainPage", "grammar");
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[4]/div[2]/img");
		Thread.sleep(500);
		waitAndClick("grammarPage", "deleteSubmit");
	}

	
	public static void quickAddRule(String name, String content) throws Exception{
		click("grammarPage", "quickAddRule");
		Thread.sleep(2000);
		sendKeys("rulePage", "name", name);
		sendKeys("rulePage", "content", content);
		click("rulePage", "submit");
	}
	
	public static void quickAddSlot(String name, String type, String subtype, String min, String max) throws Exception{
		click("grammarPage", "quickAddSlot");
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
	}
	
	public static void quickAddTemplate(String name, String content) throws Exception{
		click("grammarPage", "quickAddTemplate");
		Thread.sleep(2000);
		sendKeys("templatePage", "name", name);
		sendKeys("templatePage", "content", content);
		click("templatePage", "submit");
	}
}
