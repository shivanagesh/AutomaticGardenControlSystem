/**
 * 
 */
package sensors;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1>Sensor super class for sensors</h1>
 * <p>
 * It contains common functions which are required by the all sensors
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 18, 2016 Senor.java
 */
public abstract class Sensor {
	private static AtomicInteger uniqueId = new AtomicInteger();
	private int id;
	private String type;
	/**
	 * Indicates whether sensor is turn ON/OFF
	 */
	private boolean isActive;

	public Sensor(String name) {
		this.id = uniqueId.getAndIncrement();
		this.type = name;
		this.isActive = true;
	}

	/**
	 * Method to get id of sensor.
	 * 
	 * @return Returns id of sensor.
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Method to set sensor type.
	 * 
	 * @param type
	 *            Set sensor type.
	 */
	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
