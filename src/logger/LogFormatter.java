/**
 * 
 */
package logger;

//imports 
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * 
 * <h1>Logformat</h2> Create a custom Log logging Formatter to show the entries
 * in a CSV file with 3 columns: level, time-stamp, source module and message.
 *
 * @author Shivanagesh Chandra Feb 15, 2016 2016 LogFormat.java
 */
public class LogFormatter extends Formatter {

	/**
	 * Default Constructor: This method initialize default variables
	 */
	public LogFormatter() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * <h1>format</h1> This custom log method which is called when ever log
	 * records need to published. This methods return message in string format
	 * <p>
	 * 
	 * @param record it is object of type java.util.LogRecord
	 * 
	 * @return Log record in the form of String
	 * 
	 */
	@Override
	public String format(LogRecord record) {

		StringBuilder formartRecord = new StringBuilder();
		formartRecord.append("\n");
		formartRecord.append(record.getLevel());
		formartRecord.append(",");
		formartRecord.append(fomatDate(record.getMillis()));
		formartRecord.append(",");
		formartRecord.append(record.getSourceClassName());
		formartRecord.append(",");
		formartRecord.append(record.getMessage());
		formartRecord.append(",");
		formartRecord.append(record.getLoggerName());
		return formartRecord.toString();
	}

	/*
	 * This method converts given time in Milli sec to a Format which is
	 * required foe log
	 */

	private String fomatDate(long milisec) {
		SimpleDateFormat date = new SimpleDateFormat("MM-dd-yy HH:mm:ss");
		Date recordDate = new Date(milisec);
		return date.format(recordDate);
	}
}
