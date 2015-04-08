package util;

import java.util.logging.Logger;

public class Log {
	static Logger logger = Logger.getLogger(Log.class.getName()); 
	public static void info(String s){
		logger.info(s); 
	}
}
