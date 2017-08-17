package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.GrammarModule.addGrammar;
import static modules.LoginModule.login;
import static utils.Actions.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Actions;

public class TestCorpus {
	@Test
	public void testAddCorpus() throws Exception {

		addGrammar("吃水果", "吃(苹果|香蕉)", "吃苹果", "null");
		waitBeClick("mainPage", "corpus");
		sendKeys("corpusPage", "searchBox", "吃香蕉");
		click("corpusPage", "searchSubmit");
		waitAndClick("corpusPage", "corpusSubmit");
		waitElement("corpusPage", "subMsgClose");
		Thread.sleep(8000);
		Actions.assertTrue(getPageSource().contains("吃苹果"), "corpus_testAddCorpus");
	}
	
	@Test(dependsOnMethods="testAddCorpus")
	public void testSearchCorpus() throws Exception{
		waitBeClick("mainPage", "corpus");
		sendKeys("corpusPage", "searchBox", "吃香蕉");
		click("corpusPage", "searchSubmit");
		Thread.sleep(2000);
		Actions.assertTrue(getPageSource().contains("吃水果"),"corpus_testSearchCorpus");
		
	}
 
	@Test(dependsOnMethods="testSearchCorpus")
	public void testDeleteCorpus() throws Exception{
		waitBeClick("mainPage", "corpus");
		clickByXpath("//*[@title='吃香蕉']/following-sibling::*[5]/img");
		Thread.sleep(500);
		waitAndClick("corpusPage", "deleteSubmit");
		waitElement("corpusPage", "deleteMsg");
		Thread.sleep(500);
		String result = getText("corpusPage", "deleteMsg");
		Actions.assertTrue(result.contains("删除成功!"), "expect [删除成功!] but [" + result + "]","corpus_testDeleteCorpus");
		click("corpusPage", "deleteMsgClose");
	}
	
	@Test(dependsOnMethods="testDeleteCorpus")
	public void testDeleteCorpusFail() throws Exception{
		waitBeClick("mainPage", "corpus");
		clickByXpath("//*[@title='吃苹果']/following-sibling::*[5]/img");
		Thread.sleep(500);
		waitAndClick("corpusPage", "deleteSubmit");
		waitElement("corpusPage", "deleteMsg");
		Thread.sleep(500);
		String result = getText("corpusPage", "deleteMsg");
		Actions.assertTrue(result.contains("after corpus delete or modify , grammar:<吃水果> can not match any corpus, it must have at least one corpus."), "expect [after corpus delete or modify , grammar:<吃水果> can not match any corpus, it must have at least one corpus.] but [" + result + "]","corps_testDeleteCorpusFail");
		click("corpusPage", "deleteMsgClose");
	}
	@BeforeClass
	public void beforeClass() throws Exception {
		openBrowser("chrome");
		login();
		createApp("app5");
		enterApp("app5");
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
