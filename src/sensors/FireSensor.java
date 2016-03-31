/**
 * 
 */
package sensors;

/**
 * <h1>It contains functionality for alerting users when fire occurred</h1>
 * <p>
 * It contains functionality for alerting users when fire occurred
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 19, 2016 FireSensor.java
 */
public class FireSensor extends Sensor {

	private boolean status;
	private boolean alarm;

	/**
	 * Constructor
	 */
	public FireSensor() {
		super("FireSensor");
		this.status = false;
	}

	/**
	 * Method to get Alarm status
	 * 
	 * @return True if Alarm is active
	 */
	public boolean isAlarm() {
		return alarm;
	}

	/**
	 * Method to set Alarm status
	 * 
	 * @param alarm
	 *            the alarm to set
	 */
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	/**
	 * Method to Cancel alarm , reset Fire sensor and alarm
	 */

	public void cancelAlram() {
		this.status = false;
		this.alarm = false;
	}

	/**
	 * @return the status, True if fire is detected
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set, True if fire is occurred
	 */
	public void setStatus(boolean status) {
		this.status = status;
		this.alarm = status;
	}

}
