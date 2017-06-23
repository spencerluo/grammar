package modules;

import static utils.Actions.*;

import org.testng.Assert;

public class SlotModule {

	public static void addSlot(String name, String type, String subtype, String min, String max, String msg)
			throws Exception {
		waitAndClick("mainPage", "slot");
		waitAndClick("slotPage", "add");
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
		click("mainPage", "subMsgClose");
		if (!msg.equals("提交成功!")) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		}
		Thread.sleep(1000);
	}

	public static void addSlot(String name, String type, String subtype, String min, String max) throws Exception {
		addSlot(name, type, subtype, min, max, null);
	}

	public static void addTypeSlot(String name, String type, String mainType, String subType, String msg)
			throws Exception {
		waitAndClick("mainPage", "slot");
		waitAndClick("slotPage", "add");
		sendKeys("slotPage", "name", name);
		click("slotPage", type);
		sendKeys("slotPage", "mainType", mainType);
		if (!subType.equals("null")) {
			sendKeys("slotPage", "subType", subType);
		}
		click("slotPage", "submit");
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		if (msg != null) {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		}
		click("mainPage", "subMsgClose");
		if (!msg.equals("提交成功!")) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		}
		Thread.sleep(1000);
	}

	public static void changeSlot(String name, String type, String subtype, String min, String max, String msg)
			throws Exception {
		waitAndClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[7]/img[1]");
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
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		click("mainPage", "subMsgClose");
		if (!msg.equals("提交成功!")) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		}
		Thread.sleep(1000);
	}

	public static void changeChangeSlot(String name, String type, String subtype, String min, String max, String msg)
			throws Exception {
		waitAndClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[7]/img[1]");
		click("slotPage", type);
		if (!subtype.equals("null")) {
			click("slotPage", subtype);
		}
		clear("slotPage", "min");
		sendKeys("slotPage", "min", min);
		clear("slotPage", "max");
		sendKeys("slotPage", "max", max);
		click("slotPage", "submit");
		waitElement("slotPage", "changeSubmit");
		Thread.sleep(500);
		click("slotPage", "changeSubmit");
		Thread.sleep(1000);
		waitElement("mainPage", "subMsgClose");
		Thread.sleep(500);
		String result = getText("mainPage", "subMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		click("mainPage", "subMsgClose");
		if (!msg.equals("提交成功!")) {
			navigate("http://portal.olavoice.com/open/nli/web/search_grammar");
		}
		Thread.sleep(1000);
	}

	public static void deleteSlot(String name, String msg) throws Exception {
		waitAndClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[7]/img[2]");
		click("slotPage", "submit");
		waitElement("slotPage", "deleteMsgClose");
		Thread.sleep(500);
		String result = getText("slotPage", "deleteMsg");
		Assert.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]");
		click("slotPage", "deleteMsgClose");
		Thread.sleep(1000);
	}
}
