/**
 * 
 */
package devices;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

import Base.Base;

/**
 * <h1>This class contain common features, that every device have like turn on, turn
 * off</h1>
 * <p>
 * This class contain common features, that every device have like turn on, turn
 * off
 * <p>
 * 
 * @author Shivanagesh Chandra Feb 19, 2016 Device.java
 */
public abstract class Device {

	private static AtomicInteger uniqueId = new AtomicInteger();

	/**
	 * Status - will represent status of device, whether device is turn on or
	 * turned off
	 */
	private boolean status;

	/**
	 * id - This will unique id for each every device those are created in
	 * system, this id is will initialized to unique id
	 */
	private long id;

	/**
	 * name - This will give name of device whether heater, cooler etc
	 */
	private String name;

	/**
	 * Constructor
	 * 
	 * @param name
	 *            which name of device like heater, cooler etc
	 */
	public Device(String name) {
		this.name = name;
		this.id = uniqueId.getAndIncrement();
	}

	/**
	 * Method to turn off the device
	 */
	public void turnOff() {
		this.status = false;
		Base.getLogger().log(Level.INFO, "System :" + this.name + " " + this.id + " is turned off");

	}

	/**
	 * Method to turn on the device
	 */
	public void turnOn() {
		this.status = true;
		Base.getLogger().log(Level.INFO, "System :" + this.name + " " + this.id + " is turned on");

	}

	/**
	 * Method to get the status of device
	 * 
	 * @return True if device is on status, else False
	 */
	public boolean getStatus() {
		return this.status;
	}

	/**
	 * Method to get the Id of device
	 * 
	 * @return Id
	 */

	public long getId() {
		return this.id;
	}

	/**
	 * Method to get the name of device
	 * 
	 * @return Name of device
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Method to set the name of device
	 * 
	 * @param name Name of device
	 */

	public void setName(String name) {
		this.name = name;
	}

}
