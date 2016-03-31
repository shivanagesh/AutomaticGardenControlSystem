/**
 * 
 */
package sensors;

/**
 * <h1> It contains functionality for moisture level in soil</h1>
 * <p>
 * It contains functionality for moisture level in soil
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 18, 2016 MoistureSensor.java
 */
public class MoistureSensor extends Sensor {

	private int requiredMoisture, sensorMoisture;

	/**
	 * Constructor.
	 * 
	 * @param moisture
	 *            Moisture value that user wants.
	 */
	public MoistureSensor(int moisture) {
		super("MoistureSensor");
		this.requiredMoisture = moisture;
	}

	/**
	 * Method sets moisture level that user wants.
	 * 
	 * @param moisture
	 *            Moisture level that user wants.
	 */
	public void setHumidity(int moisture) {
		this.requiredMoisture = moisture;
	}

	/**
	 * Method to get wanted moisture level that is saved.
	 * 
	 * @return Returns wanted moisture level.
	 */
	public int getHumidity() {
		return requiredMoisture;
	}

	/**
	 * Method to get moisture sensor value.
	 * 
	 * @return Returns sensor moisture level.
	 */
	public int getSensorHumidity() {
		return sensorMoisture;
	}

}
