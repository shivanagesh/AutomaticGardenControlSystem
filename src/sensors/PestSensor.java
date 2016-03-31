/**
 * 
 */
package sensors;

/**
 * <h1>It contains functionality for identify pest in garden</h1>
 * <p>
 * It contains functionality for identify pest in garden
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 25, 2016 PestSensor.java
 */
public class PestSensor extends Sensor {

	private boolean status;

	/**
	 * Constructor
	 */
	public PestSensor() {
		super("PestSensor");
		this.status = false;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the status, True if pest is detected
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

}
