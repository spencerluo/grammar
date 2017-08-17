package testcases;

import static modules.AppModule.*;
import static modules.LoginModule.*;
import static modules.mainModule.*;
import static utils.Actions.*;
import static modules.GrammarModule.quickAddRule;
import static modules.GrammarModule.quickAddSlot;
import static modules.GrammarModule.quickAddTemplate;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utils.Actions;
import utils.ReadExcel;

public class TestGrammar {
	@Test(dataProvider = "grammar", priority = 1, enabled=true)
	public void testGrammar(String caseName, String function, String action, String test) throws Exception {
		try {
			process(caseName, function, action, test);
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}

	@Test(priority = 2, enabled=true)
	public void testQuickAddEditRule() throws Exception {
		try {
			waitBeClick("mainPage", "grammar");
			waitAndClick("grammarPage", "add");
			Thread.sleep(2000);
			quickAddRule("r1", "r1");
			waitElement("mainPage", "subMsg");
			Thread.sleep(200);
			String result1 = getText("mainPage", "subMsg");
			Actions.assertTrue(result1.contains("提交成功!"), "expect [提交成功!] but [" + result1 + "]", "grammar_testQuickAddEditRule1");
			Thread.sleep(6000);
			sendKeys("grammarPage", "content", "<r1");
			waitBeClick("grammarPage", "quickEdit");
			Thread.sleep(1000);
			clear("rulePage", "content");
			sendKeys("rulePage", "content", "r2");
			click("rulePage", "submit");
			waitElement("mainPage", "subMsgClose");
			Thread.sleep(200);
			String result2 = getText("mainPage", "subMsg");
			Actions.assertTrue(result2.contains("提交成功!"), "expect [提交成功!] but [" + result2 + "]", "grammar_testQuickAddEditRule2");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}
	
	@Test(priority = 3, enabled=true)
	public void testQuickAddEditSlot() throws Exception {
		try {
			waitBeClick("mainPage", "grammar");
			waitAndClick("grammarPage", "add");
			Thread.sleep(2000);
			quickAddSlot("s1", "datetime", "null", "1", "20");
			waitElement("mainPage", "subMsg");
			Thread.sleep(200);
			String result1 = getText("mainPage", "subMsg");
			Actions.assertTrue(result1.contains("提交成功!"), "expect [提交成功!] but [" + result1 + "]", "grammar_testQuickAddEditSlot1");
			Thread.sleep(6000);
			sendKeys("grammarPage", "content", "<s1");
			waitBeClick("grammarPage", "quickEdit");
			Thread.sleep(1000);
			click("slotPage", "internal");
			click("slotPage", "submit");
			waitElement("mainPage", "subMsgClose");
			Thread.sleep(200);
			String result2 = getText("mainPage", "subMsg");
			Actions.assertTrue(result2.contains("提交成功!"), "expect [提交成功!] but [" + result2 + "]", "grammar_testQuickAddEditSlot2");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}
	
	@Test(priority = 4, enabled=true)
	public void testQuickAddEditTemplate() throws Exception {
		try {
			waitBeClick("mainPage", "grammar");
			waitAndClick("grammarPage", "add");
			Thread.sleep(2000);
			quickAddTemplate("t1", "[=s=]t1$(s)");
			waitElement("mainPage", "subMsg");
			Thread.sleep(200);
			String result1 = getText("mainPage", "subMsg");
			Actions.assertTrue(result1.contains("提交成功!"), "expect [提交成功!] but [" + result1 + "]", "grammar_testQuickAddEditTemplate1");
			Thread.sleep(6000);
			sendKeys("grammarPage", "content", "<t1");
			waitBeClick("grammarPage", "quickEdit");
			Thread.sleep(1000);
			clear("templatePage", "content");
			sendKeys("templatePage", "content", "[=s=]t2$(s)");
			click("templatePage", "submit");
			waitElement("mainPage", "subMsgClose");
			Thread.sleep(200);
			String result2 = getText("mainPage", "subMsg");
			Actions.assertTrue(result2.contains("提交成功!"), "expect [提交成功!] but [" + result2 + "]", "grammar_testQuickAddEditTemplate2");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
	}

	@Test(enabled=true,priority=5)
	public void testNumberOfAnswer() throws Exception {
		try {
			waitBeClick("mainPage", "grammar");
			waitAndClick("grammarPage", "add");
			sendKeys("grammarPage", "name", "answers");
			sendKeys("grammarPage", "content", "testanswers");
			sendKeys("grammarPage", "corpus", "testanswers");
			click("grammarPage", "addAnswers");
			click("grammarPage", "addAnswers");
			sendKeys("grammarPage", "answer1", "answer1");
			getJS().executeScript("document.getElementsByName('grammarAnswer')[1].innerHTML='answer2'");
			getJS().executeScript("document.getElementsByName('grammarAnswer')[2].innerHTML='answer3'");
			click("grammarPage", "submit");
			waitElement("mainPage", "subMsgClose");
			Thread.sleep(200);
			String result = getText("mainPage", "subMsg");
			Actions.assertTrue(result.contains("提交成功!"), "expect [ 提交成功!] but [" + result + "]", "grammar_testNumberOfAnswer1");
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(2000);
		}
		getDriver().findElement(By.xpath("//*[@title='answers']/preceding-sibling::*[3]/img")).click();
		String answer1 = getDriver().findElement(By.xpath("//*[@title='answers']/following-sibling::*[2]/span/div[1]")).getText();
		String answer2 = getDriver().findElement(By.xpath("//*[@title='answers']/following-sibling::*[2]/span/div[2]")).getText();
		String answer3 = getDriver().findElement(By.xpath("//*[@title='answers']/following-sibling::*[2]/span/div[3]")).getText();
		Actions.assertTrue(answer1.equals("answer1")&answer2.equals("answer2")&answer3.equals("answer3"),"don't contains answer", "grammar_testNumberOfAnswer2");

	}

	@Test(priority = 6, enabled=true)
	public void goToTest() throws Exception {
		release();
		switchTo();
		waitAndClick("loginPage", "user");
		waitAndClick("loginPage", "应用管理");
		configureModules("app5");
		waitAndClick("loginPage", "test");
	}

	@Test(dataProvider = "corpus", priority = 7, enabled=true)
	public void testCorpus(String function, String name, String question, String result) throws Exception {
		clear("loginPage", "question");
		sendKeys("loginPage", "question", question);
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer = getValue("loginPage", "answer");
		Actions.assertTrue(answer.equals(result), "grammar_"+question);
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
			switchTo();
			deleteApp("app5");
			Thread.sleep(2000);
		} finally {
			quite();
		}
	}

	@DataProvider
	public Object[][] grammar() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "grammar")
				.getData();
	}

	@DataProvider
	public Object[][] corpus() throws Exception {
		return new ReadExcel("D:\\eclipse-jee-mars-1-win32-x86_64\\workspace\\grammar\\config\\grammar.xlsx", "corpus")
				.getData();
	}
}
