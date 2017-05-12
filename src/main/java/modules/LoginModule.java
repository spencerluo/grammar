package modules;

import static utils.Actions.*;

public class LoginModule {

	public static void login() throws Exception {

		navigate("http://portal.olavoice.com/open/website/login/login_show");
		sendKeys("loginPage", "username", "spencer");
		sendKeys("loginPage", "password", "asdD1234");
		sendKeys("loginPage", "yanzhengma", "abcd");
		click("loginPage", "submit");
		waitAndClick("loginPage", "user");
		clickAndSwitch("loginPage", "nli");
	}
}
