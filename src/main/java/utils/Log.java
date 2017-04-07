package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {


	private static Logger log = LogManager.getLogger(Log.class);
	


	public static void info(String data) {
		log.info(data);
	}

	public static void error(String data) {
		log.error(data);
	}

	public static void debug(String data) {
		log.debug(data);
	}

	public static void warn(String data) {
		log.warn(data);
	}

	public static void testStart() {
		log.info("----------Test Start----------");
	}

	public static void testEnd() {
		log.info("----------Test End----------\n");
	}

}
