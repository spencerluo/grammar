package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.GrammarModule.addGrammar;
import static modules.LoginModule.configureModules;
import static modules.LoginModule.login;
import static modules.SlotModule.addSlot;
import static modules.mainModule.release;
import static utils.Actions.clear;
import static utils.Actions.click;
import static utils.Actions.getValue;
import static utils.Actions.navigate;
import static utils.Actions.openBrowser;
import static utils.Actions.quite;
import static utils.Actions.sendKeys;
import static utils.Actions.switchTo;
import static utils.Actions.waitAndClick;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utils.Actions;

public class TestPriority {
	@Test()
	public void testPriority1() throws Exception {
		switchTo();
		waitAndClick("loginPage", "user");
		waitAndClick("loginPage", "应用管理");
		configureModules("test1", "50");
		configureModules("test2", "10");
		waitAndClick("loginPage", "test");
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "我要吃饭");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer = getValue("loginPage", "answer");
		Actions.assertTrue(answer.contains("test1"), "testpriority1");
	}

	@Test(dependsOnMethods = "testPriority1")
	public void testPriority2() throws Exception {
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "打开电视");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer = getValue("loginPage", "answer");
		Actions.assertTrue(answer.contains("test2"), "testpriority2");
	}

	@Test(dependsOnMethods = "testPriority2")
	public void testPriority3() throws Exception {
		navigate("http://portal.olavoice.com/open/website/applicationmanage/application_show");
		configureModules("test2", "50");
		waitAndClick("loginPage", "test");
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "打开电视");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer = getValue("loginPage", "answer");
		Actions.assertTrue(answer.contains("test1"), "testpriority3");
	}
	
	@Test(dependsOnMethods = "testPriority3")
	public void testPriority4() throws Exception {
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "天气如何");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer1 = getValue("loginPage", "answer");
		Actions.assertTrue(answer1.contains("test1"), "testpriority41");
		
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "天气");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer2 = getValue("loginPage", "answer");
		Actions.assertTrue(answer2.contains("weather"), "testpriority42");
		
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "天气如何");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer3 = getValue("loginPage", "answer");
		Actions.assertTrue(answer3.contains("weather"), "testpriority43");
	}
	
	@Test(dependsOnMethods = "testPriority4")
	public void testPriority5() throws Exception {
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "查天气");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer1 = getValue("loginPage", "answer");
		Actions.assertTrue(answer1.contains("test2"), "testpriority51");
		
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "天气");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer2 = getValue("loginPage", "answer");
		Actions.assertTrue(answer2.contains("weather"), "testpriority52");
		
		clear("loginPage", "question");
		sendKeys("loginPage", "question", "查天气");
		click("loginPage", "testSubmit");
		Thread.sleep(4000);
		String answer3 = getValue("loginPage", "answer");
		Actions.assertTrue(answer3.contains("test2"), "testpriority53");
	}

	@BeforeClass
	public void beforeClass() throws Exception {
		openBrowser("chrome");
		login();
		createApp("test1");
		enterApp("test1");
		try {
			addSlot("object", "ext", "null", "1", "20");
			addGrammar("我要吃饭", "我要吃饭", "我要吃饭", "null");
			addGrammar("打开电视", "打开电<object>", "打开电视", "null");
			addGrammar("天气如何", "天气<object>", "天气如何", "null");
			Thread.sleep(5000);
			release();
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
		createApp("test2");
		enterApp("test2");
		try {
			addSlot("object", "ext", "null", "1", "20");
			addGrammar("我要吃饭", "我要吃<object>", "我要吃饭", "null");
			addGrammar("打开电视", "打开<object>", "打开电视", "null");
			addGrammar("查天气", "查天气", "查天气", "null");
			Thread.sleep(5000);
			release();
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}

	}

	@AfterClass
	public void afterClass() throws Exception {
		try {
			switchTo();
			deleteApp("test1");
			deleteApp("test2");
			Thread.sleep(5000);
		} finally {
			quite();
		}
	}
}
