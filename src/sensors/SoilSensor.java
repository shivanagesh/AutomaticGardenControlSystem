/**
 * 
 */
package sensors;

/**
 * <h1>It contains functionality for PH value of soil</h1>
 * <p>
 * It contains functionality for PH value of soil
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 18, 2016 SoilSensor.java
 */
public class SoilSensor extends Sensor {

	private int sensorPH;

	/**
	 * Constructor.
	 * 
	 */
	public SoilSensor() {
		super("SoilSensor");
		this.setSensorPH(-1);

	}

	/**
	 * @return the sensorPH
	 */
	public int getSensorPH() {
		return sensorPH;
	}

	/**
	 * Dummy method to replicate sensor working, its like stub
	 * 
	 * @param sensorPH
	 *            the sensorPH to set
	 */
	public void setSensorPH(int sensorPH) {
		this.sensorPH = sensorPH;
	}

}
