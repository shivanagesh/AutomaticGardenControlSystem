/**
 * 
 */
package logger;

import java.io.IOException;
import java.util.logging.*;

/**
 * @author Shivanagesh Chandra Feb 14, 2016 2016 Logger.java
 */
public class EventLogger {

	public final static Logger logger = Logger.getLogger(EventLogger.class.getName());

	public EventLogger() {
		initLogger();
	}

	/*
	 * This method initialize default parameters and objects for logger class
	 */
	private void initLogger() {
		try {
			Logger rootLogger = Logger.getLogger("");
			rootLogger.setUseParentHandlers(false);
			Handler csvFileHandler = new FileHandler("logger/log.csv", true);
			Formatter logformater = new LogFormatter();
			rootLogger.addHandler(csvFileHandler);
			csvFileHandler.setFormatter(logformater);
			logger.setLevel(Level.ALL);
		} catch (IOException ie) {
			System.out.println("Logger initialization failed");
			ie.printStackTrace();
		}
	}

	public void log(LogRecord record) {
		logger.log(record);
	}

	public void writeLogToCsv() {

		System.out.println("writetocsv");
		logger.severe("This is a SEVERE-level log");
		logger.warning("This is a WARNING-level log");
		logger.info("This is a INFO-level log");
		logger.finest("This is a FINEST-level log");
		try {
			// Simulating Exceptions
			throw new Exception("Simulating an exception");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage(), ex);
		}
	}

	public Logger getLogger() {
		System.out.println(logger);
		return logger;
	}

	// public static void main(String[] argv){
	// System.out.println("hey");
	// EventLogger lg = new EventLogger();
	// lg.writeLogToCsv();
	//
	// }

}
