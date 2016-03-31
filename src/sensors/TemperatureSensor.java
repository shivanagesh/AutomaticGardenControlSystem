/**
 * 
 */
package sensors;

/**
 * <h1> It contains functionality for temperature in garden/zone</h1>
 * <p>
 * It contains functionality for temperature in garden/zone
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 18, 2016 RainSensor.java
 */
public class TemperatureSensor extends Sensor {

	// Stores temperature in fahrenheit
	private int sensorTemperature;

	/**
	 * Constructor.
	 */
	public TemperatureSensor() {
		super("TemperatureSensor");
	}

	/**
	 * Method to get temperature sensor value.
	 * 
	 * @return Returns sensor temperature value in fahrenheit .
	 */
	public int getSensorTemperature() {
		return sensorTemperature;
	}

	/**
	 * Method to set temperature sensor value.
	 * 
	 * @param temperature
	 *            set temperature in fahrenheit
	 */
	public void setSensorTemperature(int temperature) {
		this.sensorTemperature = temperature;
	}

}
