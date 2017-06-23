package modules;

import static modules.GrammarModule.*;
import static modules.AppModule.*;
import static modules.RuleModule.addRule;
import static modules.RuleModule.changeRule;
import static modules.RuleModule.changeChangeRule;
import static modules.RuleModule.deleteRule;
import static modules.SlotModule.addSlot;
import static modules.SlotModule.addTypeSlot;
import static modules.SlotModule.changeSlot;
import static modules.SlotModule.changeChangeSlot;
import static modules.SlotModule.deleteSlot;
import static modules.TemplateModule.addTemplate;
import static modules.TemplateModule.changeTemplate;
import static modules.TemplateModule.changeChangeTemplate;
import static modules.TemplateModule.deleteTemplate;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static utils.Actions.*;
public class mainModule {

	public static void process(String function, String action, String test) throws Exception {
		JsonParser parser = new JsonParser();

		JsonObject object = (JsonObject) parser.parse(test);
		if (function.equals("rule")) {
			String name = object.get("name").getAsString();
			String content = object.get("content").getAsString();
			String msg = object.get("msg").getAsString();
			switch (action) {
			case "add":
				addRule(name, content, msg);
				break;
			case "change":
				changeRule(name, content, msg);
				break;
			case "changeChange":
				changeChangeRule(name, content, msg);
				break;
			case "delete":
				deleteRule(name, msg);
				break;
			default:
				throw new Exception("new such action " + action);
			}
		} else if (function.equals("template")) {
			String name = object.get("name").getAsString();
			String content = object.get("content").getAsString();
			String msg = object.get("msg").getAsString();
			switch (action) {
			case "add":
				addTemplate(name, content, msg);
				break;
			case "change":
				changeTemplate(name, content, msg);
				break;
			case "changeChange":
				changeChangeTemplate(name, content, msg);
				break;
			case "delete":
				deleteTemplate(name, msg);
				break;
			default:
				throw new Exception("new such action " + action);
			}
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
			switch (action) {
			case "add":
				addSlot(name, type, subtype, min, max, msg);
				break;
			case "addType":
				addTypeSlot(name, type, mainType, subType, msg);
				break;
			case "change":
				changeSlot(name, type, subtype, min, max, msg);
				break;
			case "changeChange":
				changeChangeSlot(name, type, subtype, min, max, msg);
				break;
			case "delete":
				deleteSlot(name, msg);
				break;
			default:
				throw new Exception("new such action " + action);
			}
		} else if (function.equals("grammar")) {
			String name = object.get("name").getAsString();
			String content = object.get("content").getAsString();
			String corpus = object.get("corpus").getAsString();
			String answer = object.get("answer").getAsString();
			String msg = object.get("msg").getAsString();
			switch (action) {
			case "add":
				addGrammar(name, content, corpus, answer, msg);
				break;
			case "addChange":
				addChangeGrammar(name, content, corpus, answer, msg);
				break;
			case "change":
				changeGrammar(name, content, corpus, answer, msg);
				break;
			case "changeChange":
				changeChangeGrammar(name, content, corpus, answer, msg);
				break;
			case "delete":
				deleteGrammar(name, msg);
				break;
			default:
				throw new Exception("new such action " + action);
			}
		}else if(function.equals("app")){
			String name = object.get("name").getAsString();
			String msg = object.get("msg").getAsString();
			switch (action) {
			case "add":
				createApp(name, msg);
				break;
			case "delete":
				deleteApp(name, msg);
				break;
			default:
				throw new Exception("new such action " + action);
			}
		}else {
			throw new Exception("no such type " + function);
		}

	}
	
	public static void release() throws Exception{
		click("mainPage", "release");
		click("releasePage", "release");
		waitAndClick("releasePage", "subMsgClose");
		Thread.sleep(1000);
	}
}
