package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.GrammarModule.addGrammar;
import static modules.RuleModule.addRule;
import static modules.TemplateModule.addTemplate;
import static modules.SlotModule.addSlot;
import static modules.LoginModule.login;
import static utils.Actions.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Actions;

public class TestSearch {
	@Test(enabled=true)
	public void searchGrammar() throws Exception {
		try {
			clear("mainPage", "searchBox");
			sendKeys("mainPage", "searchBox", "grammar1");
			click("mainPage", "searchSubmit");
			waitAndClick("searchPage", "grammar");
			Thread.sleep(1000);
			Actions.assertTrue(getPageSource().contains("你好"),"AllSearch_searchGrammar1");
			clickByXpath("//*[@title='grammar1']/following-sibling::*[4]/div[1]/img");
			clear("grammarPage", "content");
			sendKeys("grammarPage", "content", "你好|好啊");
			sendKeys("grammarPage", "corpus", "好啊");
			click("grammarPage", "submit");
			waitAndClick("mainPage", "subMsgClose");
			Thread.sleep(4000);
			clear("mainPage", "searchBox");
			sendKeys("mainPage", "searchBox", "grammar1");
			click("mainPage", "searchSubmit");
			waitAndClick("searchPage", "grammar");
			Thread.sleep(2000);
			Actions.assertTrue(getPageSource().contains("你好|好啊"),"AllSearch_searchGrammar2");
			clickByXpath("//*[@title='grammar1']/following-sibling::*[4]/div[2]/img");
			waitAndClick("grammarPage", "deleteSubmit");
			waitAndClick("grammarPage", "deleteMsgClose");
			Thread.sleep(5000);
			waitAndClick("searchPage", "grammar");
			Thread.sleep(2000);
			Actions.assertTrue(!getPageSource().contains("你好|好啊"),"AllSearch_searchGrammar3");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}
	@Test
	public void searchRule() throws Exception{
		try {
			clear("mainPage", "searchBox");
			sendKeys("mainPage", "searchBox", "rule1");
			click("mainPage", "searchSubmit");
			waitAndClick("searchPage", "rule");
			Thread.sleep(1000);
			Actions.assertTrue(getPageSource().contains("苹果|香蕉"),"AllSearch_searchRule1");
			clickByXpath("//*[@title='rule1']/following-sibling::*[3]/div[1]/img");
			clear("rulePage", "content");
			sendKeys("rulePage", "content", "苹果|香蕉|草莓");
			click("rulePage", "submit");
			waitAndClick("mainPage", "subMsgClose");
			Thread.sleep(6000);
			waitAndClick("searchPage", "rule");
			Thread.sleep(2000);
			Actions.assertTrue(getPageSource().contains("苹果|香蕉|草莓"),"AllSearch_searchRule2");
			clickByXpath("//*[@title='rule1']/following-sibling::*[3]/div[2]/img");
			click("rulePage", "submit");
			waitAndClick("rulePage", "deleteMsgClose");
			Thread.sleep(5000);
			waitAndClick("searchPage", "rule");
			Thread.sleep(2000);
			Actions.assertTrue(!getPageSource().contains("苹果|香蕉|草莓"),"AllSearch_searchRule3");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}
	
	@Test
	public void searchTemplate() throws Exception{
		try {
			clear("mainPage", "searchBox");
			sendKeys("mainPage", "searchBox", "temp1");
			click("mainPage", "searchSubmit");
			waitAndClick("searchPage", "template");
			Thread.sleep(1000);
			Actions.assertTrue(getPageSource().contains("[=s=]吃$(s)"),"AllSearch_searchTemplate1");
			clickByXpath("//*[@title='temp1']/following-sibling::*[3]/div[1]/img");
			clear("templatePage", "content");
			sendKeys("templatePage", "content", "[=s=]喝$(s)");
			click("templatePage", "submit");
			waitAndClick("mainPage", "subMsgClose");
			Thread.sleep(6000);
			waitAndClick("searchPage", "template");
			Thread.sleep(2000);
			Actions.assertTrue(getPageSource().contains("[=s=]喝$(s)"),"AllSearch_searchTemplate2");
			clickByXpath("//*[@title='temp1']/following-sibling::*[3]/div[2]/img");
			click("templatePage", "submit");
			waitAndClick("templatePage", "deleteMsgClose");
			Thread.sleep(5000);
			waitAndClick("searchPage", "template");
			Thread.sleep(2000);
			Actions.assertTrue(!getPageSource().contains("[=s=]喝$(s)"),"AllSearch_searchTemplat3");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}
	
	@Test
	public void searchSlot() throws Exception{
		try {
			clear("mainPage", "searchBox");
			sendKeys("mainPage", "searchBox", "slot1");
			click("mainPage", "searchSubmit");
			waitAndClick("searchPage", "slot");
			Thread.sleep(1000);
			Actions.assertTrue(getPageSource().contains("47"),"AllSearch_searchSlot1");
			clickByXpath("//*[@title='slot1']/following-sibling::*[7]/div[1]/img");
			clear("slotPage", "min");
			sendKeys("slotPage", "min", "53");
			click("slotPage", "submit");
			waitAndClick("mainPage", "subMsgClose");
			Thread.sleep(6000);
			waitAndClick("searchPage", "slot");
			Thread.sleep(2000);
			Actions.assertTrue(getPageSource().contains("53"),"AllSearch_searchSlot2");
			clickByXpath("//*[@title='slot1']/following-sibling::*[7]/div[2]/img");
			waitAndClick("slotPage", "submit");
			waitAndClick("slotPage", "deleteMsgClose");
			Thread.sleep(5000);
			waitAndClick("searchPage", "slot");
			Thread.sleep(2000);
			Actions.assertTrue(!getPageSource().contains("53"),"searchSlot3");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		openBrowser("chrome");
		login();
		createApp("app5");
		enterApp("app5");
		try {
			addGrammar("grammar1", "你好", "你好", "null");
			addRule("rule1", "苹果|香蕉");
			addSlot("slot1", "datetime", "null", "47", "59");
			addTemplate("temp1", "[=s=]吃$(s)");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}

	@AfterClass
	public void afterClass() throws Exception {
		try {
			deleteApp("app5");
			Thread.sleep(2000);
		} finally {
			quite();
		}
	}
}
