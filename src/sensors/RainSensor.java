/**
 * 
 */
package sensors;

/**
 * <h1>It contains functionality for identify whether it is raining or not</h1>
 * <p>
 * It contains functionality for identify whether it is raining or not
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 18, 2016 RainSensor.java
 */
public class RainSensor extends Sensor {

	private boolean status;

	/**
	 * Constructor.
	 */
	public RainSensor() {
		super("RainSensor");
		this.status = false;
	}

	/**
	 * Method to get whether it is raining or not.
	 * 
	 * @return True if it is raining.
	 */
	public boolean getStatus() {

		return this.status;
	}

	/**
	 * Method to whether it is raining or not.
	 * 
	 * @param status
	 *            Set status if is raining.
	 */
	public void setStatus(boolean status) {

		this.status = status;
	}

}
