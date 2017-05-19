package modules;

import static utils.Actions.*;

import org.testng.Assert;



public class SlotModule {

	public static void addSlot(String name, String type, String subtype, String min, String max, String msg) throws Exception {
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
		try {
			if (msg != null) {
				String result = getText("mainPage", "subMsg");
				Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
			} 
		} finally {
			click("mainPage", "subMsgClose");
		}
	}

	public static void addSlot(String name, String type, String subtype, String min, String max) throws Exception {
		addSlot(name, type, subtype, min, max, null);
	}

	public static void changeSlot(String name,String type, String subtype, String min, String max, String msg) throws Exception{
		waitAndClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[6]/img[1]");
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
		try {
			String result = getText("mainPage", "subMsg");
			Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
		} finally {
			click("mainPage", "subMsgClose");
		}
	}
	
	public static void deleteSlot(String name, String msg) throws Exception{
		waitAndClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='"+name+"']/following-sibling::*[6]/img[2]");
		click("slotPage", "submit");
		waitElement("slotPage", "deleteMsgClose");
		try {
			String result = getText("slotPage", "deleteMsg");
			Assert.assertTrue(result.contains(msg),"expect ["+msg+"] but ["+result+"]");
		} finally {
			click("slotPage", "deleteMsgClose");
		}
	}
}
