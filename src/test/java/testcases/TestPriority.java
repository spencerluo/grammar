package testcases;

import static modules.AppModule.createApp;
import static modules.AppModule.deleteApp;
import static modules.AppModule.enterApp;
import static modules.GrammarModule.addGrammar;
import static modules.LoginModule.login;
import static modules.RuleModule.addRule;
import static modules.SlotModule.addSlot;
import static modules.TemplateModule.addTemplate;
import static utils.Actions.navigate;
import static utils.Actions.openBrowser;
import static utils.Actions.quite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestPriority {
  @Test
  public void f() {
  }
  
	@BeforeClass
	public void beforeClass() throws Exception {
		openBrowser("chrome");
		login();
		createApp("test1");
		enterApp("test1");
		try {
			addGrammar("我要吃饭", "我要吃饭", "我要吃饭", "null");
			addGrammar("打开电视", "打开电视", "打开电视", "null");
			Thread.sleep(5000);
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
		createApp("test2");
		enterApp("test2");
		try {
			addSlot("饭", "ext", "null", "1", "20");
			addGrammar("我要吃饭", "我要吃<饭>", "我要吃饭", "null");
			addGrammar("打开电视", "打开电视", "打开电视", "null");
			Thread.sleep(5000);
		} finally {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
			Thread.sleep(1000);
		}
		
	}

	@AfterClass
	public void afterClass() throws Exception {
		try {
			deleteApp("test1");
			deleteApp("test2");
			Thread.sleep(2000);
		} finally {
			quite();
		}
	}
}
