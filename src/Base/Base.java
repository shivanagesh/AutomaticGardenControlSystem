/**
 * 
 */
package Base;

import java.util.logging.Logger;

import logger.GardenLogger;
import logger.LogFormatter;

/**
 * <h1>Base Class</h1>
 * <p>
 * Base, this contains common functionality like logger or any other useful
 * function
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 26, 2016 Base.java
 */
public class Base {

	private static GardenLogger gardenLogger = new GardenLogger();
	protected static Logger logger = gardenLogger.getLogger();
	protected static LogFormatter logFormatter= gardenLogger.getLogFormatter();

	/**
	 * Method returns Logger
	 * 
	 * @return logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	public static LogFormatter getLogFormatter() {
		return logFormatter;
	}

}
