/**
 * 
 */
package sensors;

/**
 * <h1>It contains functionality for humidity</h1>
 * <p>
 * It contains functionality for humidity
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 18, 2016 RainSensor.java
 */
public class HumiditySensor extends Sensor {

	private int wantedHumidity, sensorHumidity;

	/**
	 * Constructor.
	 * 
	 * @param humidity
	 *            Humidity value that user wants.
	 */
	public HumiditySensor(int humidity) {
		super("HumiditySensor");
		this.wantedHumidity = humidity;
	}

	/**
	 * Method sets humidity level that user wants.
	 * 
	 * @param humidity
	 *            Humidity level that user wants.
	 */
	public void setHumidity(int humidity) {
		this.wantedHumidity = humidity;
	}

	/**
	 * Method to get wanted humidity level that is saved.
	 * 
	 * @return Returns wanted humidity level.
	 */
	public int getHumidity() {
		return wantedHumidity;
	}

	/**
	 * Method to get humidity sensor value.
	 * 
	 * @return Returns sensor humidity level.
	 */
	public int getSensorHumidity() {
		return sensorHumidity;
	}

}
