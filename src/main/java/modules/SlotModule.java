package modules;

import static utils.Actions.*;


public class SlotModule {

	public static void addSlot(String name, String type, String subtype, String min, String max)
			throws Exception {
		waitBeClick("mainPage", "slot");
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
	}


	public static void addSlotError(String name, String type, String mainType, String subType)
			throws Exception {
		waitBeClick("mainPage", "slot");
		waitAndClick("slotPage", "add");
		sendKeys("slotPage", "name", name);
		click("slotPage", type);
		sendKeys("slotPage", "mainType", mainType);
		if (!subType.equals("null")) {
			sendKeys("slotPage", "subType", subType);
		}
		Thread.sleep(2000);
		click("slotPage", "submit");
	}

	public static void addTypeSlot(String name, String type, String mainType, String subType)
			throws Exception {
		waitBeClick("mainPage", "slot");
		waitAndClick("slotPage", "add");
		sendKeys("slotPage", "name", name);
		click("slotPage", type);
		sendKeys("slotPage", "mainType", mainType);
		if (!subType.equals("null")) {
			sendKeys("slotPage", "subType", subType);
		}
		click("slotPage", "submit");
	}

	public static void changeSlot(String name, String type, String subtype, String min, String max)
			throws Exception {
		waitBeClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[7]/div[1]/img");
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

	public static void changeChangeSlot(String name, String type, String subtype, String min, String max)
			throws Exception {
		waitBeClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[7]/div[1]/img");
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
	}

	public static void deleteSlot(String name) throws Exception {
		waitBeClick("mainPage", "slot");
		Thread.sleep(1000);
		clickByXpath("//*[@title='" + name + "']/following-sibling::*[7]/div[2]/img");
		click("slotPage", "submit");
	}
}
