package modules;

import static modules.GrammarModule.*;
import static modules.AppModule.*;
import static modules.RuleModule.addRule;
import static modules.RuleModule.addRuleError;
import static modules.RuleModule.changeRule;
import static modules.RuleModule.changeChangeRule;
import static modules.RuleModule.deleteRule;
import static modules.SlotModule.*;
import static modules.TemplateModule.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.Actions;

import static utils.Actions.*;


public class mainModule {

	public static void process(String caseName, String function, String action, String test) throws Exception {
		JsonParser parser = new JsonParser();

		JsonObject object = (JsonObject) parser.parse(test);
		if (function.equals("rule")) {
			String name = object.get("name").getAsString();
			String content = object.get("content").getAsString();
			String msg = object.get("msg").getAsString();
			String result = null;
			switch (action) {
			case "add":
				addRule(name, content);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "addError":
				addRuleError(name, content);
				try {
					result = getText("rulePage", "titleErrorMsg");
				} catch (Exception e) {
					result = getText("rulePage", "contentErrorMsg");
				}
				break;
			case "change":
				changeRule(name, content);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "changeChange":
				changeChangeRule(name, content);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "delete":
				deleteRule(name);
				waitElement("rulePage", "deleteMsg");
				Thread.sleep(2000);
				result = getText("rulePage", "deleteMsg");
				break;
			default:
				throw new Exception("new such action " + action);
			}
			Actions.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]", function+"_"+caseName);
		} else if (function.equals("template")) {
			String name = object.get("name").getAsString();
			String content = object.get("content").getAsString();
			String msg = object.get("msg").getAsString();
			String result = null;
			switch (action) {
			case "add":
				addTemplate(name, content);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "addError":
				addTemplateError(name, content);
				try {
					result = getText("templatePage", "titleErrorMsg");
				} catch (Exception e) {
					result = getText("templatePage", "contentErrorMsg");
				}
				break;
			case "change":
				changeTemplate(name, content);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "changeChange":
				changeChangeTemplate(name, content);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "delete":
				deleteTemplate(name);
				waitElement("templatePage", "deleteMsg");
				Thread.sleep(3000);
				result = getText("templatePage", "deleteMsg");
				break;
			default:
				throw new Exception("new such action " + action);
			}
			Actions.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]", function+"_"+caseName);
		} else if (function.equals("slot")) {
			String name = object.get("name").getAsString();
			String type = object.get("type").getAsString();
			String subtype = null;
			String mainType = null;
			String subType = null;
			String min = null;
			String max = null;
			try {
				subtype = object.get("subtype").getAsString();
				min = object.get("min").getAsString();
				max = object.get("max").getAsString();
			} catch (Exception e) {
			}
			try{
				mainType = object.get("mainType").getAsString();
				subType = object.get("subType").getAsString();
			}catch (Exception e) {
			}
			String msg = object.get("msg").getAsString();
			String result = null;
			switch (action) {
			case "add":
				addSlot(name, type, subtype, min, max);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "addError":
				addSlotError(name, type, mainType, subType);
				try {
					result = getText("slotPage", "titleErrorMsg");
				} catch (Exception e) {
					try {
						result = getText("slotPage", "mainTypeErrorMsg");
					} catch (Exception e1) {
						result = getText("slotPage", "lengthErrorMsg");
					}
				}
				break;
			case "addType":
				addTypeSlot(name, type, mainType, subType);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "change":
				changeSlot(name, type, subtype, min, max);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "changeChange":
				changeChangeSlot(name, type, subtype, min, max);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "delete":
				deleteSlot(name);
				waitElement("slotPage", "deleteMsg");
				Thread.sleep(3000);
				result = getText("slotPage", "deleteMsg");
				break;
			default:
				throw new Exception("new such action " + action);
			}
			Actions.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]", function+"_"+caseName);
		} else if (function.equals("grammar")) {
			String name = object.get("name").getAsString();
			String content = object.get("content").getAsString();
			String corpus = object.get("corpus").getAsString();
			String answer = object.get("answer").getAsString();
			String msg = object.get("msg").getAsString();
			String result = null;
			switch (action) {
			case "add":
				addGrammar(name, content, corpus, answer);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "addError":
				addGrammarError(name, content, corpus, answer);
				try {
					result = getText("grammarPage", "titleErrorMsg");
				} catch (Exception e) {
					try {
						result = getText("grammarPage", "contentErrorMsg");
					} catch (Exception e1) {
						try {
							result = getText("grammarPage", "corpusErrorMsg");
						} catch (Exception e2) {
							result = getText("grammarPage", "answerErrorMsg");
						}
					}
				}
				break;
			case "addChange":
				addChangeGrammar(name, content, corpus, answer);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "change":
				changeGrammar(name, content, corpus, answer);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "changeChange":
				changeChangeGrammar(name, content, corpus, answer);
				waitElement("mainPage", "subMsg");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "delete":
				deleteGrammar(name);
				waitElement("grammarPage", "deleteMsg");
				Thread.sleep(3000);
				result = getText("grammarPage", "deleteMsg");
				break;
			default:
				throw new Exception("new such action " + action);
			}
			Actions.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]", function+"_"+caseName);
		}else if(function.equals("app")){
			String name = object.get("name").getAsString();
			String msg = object.get("msg").getAsString();
			String result = null;
			switch (action) {
			case "add":
				createApp(name);
				waitElement("modelPage", "closeAddNotice");
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "addError":
				createAppError(name);
				Thread.sleep(1000);
				result = getText("modelPage", "nameErrorMsg");
				break;
			case "import":
				importApp(name);
				waitElement("mainPage", "subMsgClose",20);
				Thread.sleep(500);
				result = getText("mainPage", "subMsg");
				break;
			case "delete":
				deleteApp(name);
				waitElement("modelPage", "closeDeleteNotice");
				Thread.sleep(500);
				result = getText("modelPage", "deleteMsg");
				break;
			default:
				throw new Exception("new such action " + action);
			}
			Actions.assertTrue(result.contains(msg), "expect [" + msg + "] but [" + result + "]", function+"_"+caseName);
		}else {
			throw new Exception("no such type " + function);
		}

	}
	
	public static void release() throws Exception{
		waitBeClick("mainPage", "release");
		click("releasePage", "release");
		waitAndClick("releasePage", "subMsgClose");
		Thread.sleep(1000);
	}
}
