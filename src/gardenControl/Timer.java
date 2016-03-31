/**
 * 
 */
package gardenControl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <h1>This class contains time of garden system  </h1>
 * <p> This class contains time of garden system <p>
 *  
 * @author Shivanagesh Chandra
 * Feb 23, 2016 
 * Timer.java
 */
public class Timer {

	/**
	 * Configuration variable to set time format, In future we can provide in UI to change, Currently directly fixing to 24 min according to requirements
	 */
	private long dayInminutes;
	/**
	 * Store start Date time when Garden management system started 
	 */
	private Date startDateTime;
	/**
	 * Store days of garden management system
	 */
	private int days;
	
	/**
	 * Constructor - loads initial values 
	 */
	public Timer() {
		 this.dayInminutes = 24;
		 startDateTime = new Date();
		 days = 0;
	}
	
	/**
	 * Method gives current time in form of minutes  of system in form of float data type
	 * @return time current time in form of minutes  of garden management system 
	 */
	public  float currentTime() {
	    long millisec = ((new Date().getTime()) - (startDateTime.getTime())); 
		long mintues = TimeUnit.MINUTES.convert(millisec,TimeUnit.MILLISECONDS);
		this.days = (int)(mintues/dayInminutes);
		return (float)(mintues%dayInminutes);
	}
	
	/**
	 * Method gives current time in form of minutes  of system in form of string
	 * @return time current time in form of minutes of garden management system 
	 */
	public  String currentTimeString() {
	    long millisec = ((new Date().getTime()) - (startDateTime.getTime())); 
		long mintues = TimeUnit.MINUTES.convert(millisec,TimeUnit.MILLISECONDS);
		long seconds = TimeUnit.SECONDS.convert(millisec,TimeUnit.MILLISECONDS);
		return (int)(mintues%dayInminutes)+":"+(seconds%(60));
	
	}
	
	/**
	 * Method gives time in form seconds in form of system
	 * @return time in form of Seconds
	 */
	public int getCurrentTimeInSec(){
		long millisec = ((new Date().getTime()) - (startDateTime.getTime())); 
		long seconds = TimeUnit.SECONDS.convert(millisec,TimeUnit.MILLISECONDS);
		return (int) (seconds%(60));
	}

	/**
	 * Method returns - how many minutes we currently considering as day 
	 * @return the dayInminutes
	 */
	public long getDayInminutes() {
		return dayInminutes;
	}

	/**
	 * Method Sets - how many minutes we currently considering as day 
	 * @param dayInminutes the dayInminutes to set
	 */
	public void setDayInminutes(long dayInminutes) {
		this.dayInminutes = dayInminutes;
	}

	/**
	 * Method get start date time of system 
	 * @return the startDateTime
	 */
	public Date getStartDateTime() {
		return startDateTime;
	}

	/**
	 * Method get days of system 
	 * @return the days
	 */
	public int getDays() {
		return days;
	}


}
