/**
 * 
 */
package wateringSystem;

import java.util.ArrayList;
import java.util.List;

import sensors.RainSensor;

/**
 * <h1>Watering Controller </h1>
 * <p> Which manages watering like watering for specific time. if it raining turn off the watering etc</p>
 * 
 * @author Shivanagesh Chandra Feb 17, 2016 2016 WateringSytem.java
 */
public class WaterController {

	/**
	 * Watering system, which perform watering
	 */
	private Watering waterSystem;
	/**
	 * Rain sensor - to identify whether it is raining or not
	 */
	private RainSensor rainSensor;
	/**
	 * List of sprinkler's under this water controller
	 */
	private List<Sprinkler> sprinklers;

	/**
	 * Default constructor
	 */
	public WaterController() {
		this.waterSystem = null;
		sprinklers = new ArrayList<>();
		rainSensor = null;

	}

	/**
	 * Adds sprinkler to Water Controller
	 * @param sprinkler Sprinkler object
	 */
	public void addSprinkler(Sprinkler sprinkler) {
		this.sprinklers.add(sprinkler);
		this.setSprinklerConfig(sprinklers);
	}

	/**
	 * Adds sprinkler to Water Controller
	 * @param sprinkler Sprinkler which is needed to remove
	 */
	public void removeSprinkler(Sprinkler sprinkler) {
		for (Sprinkler s : sprinklers) {
			if (s.getId() == sprinkler.getId()) {
				sprinklers.remove(sprinkler);
			}
		}
	}

	/**
	 * Return watering system
	 * @return the waterSystem 
	 */
	public Watering getWaterSystem() {
		return waterSystem;
	}

	/**
	 * Method to add watering system
	 * @param waterSystem
	 *            adds the waterSystem
	 */
	public void addWaterSystem(Watering waterSystem) {
		this.waterSystem = waterSystem;
	}

	/**
	 * @param sprinkler List of sprinkler's 
	 * @see wateringSystem.Watering#setSprinklerConfig(java.util.List)
	 */
	public void setSprinklerConfig(List<Sprinkler> sprinkler) {
		waterSystem.setSprinklerConfig(sprinkler);
	}

	/**
	 * Method to get rainSensor 
	 * @return the rainSensor
	 */
	public RainSensor getRainSensor() {
		return rainSensor;
	}

	/**
	 * Method to add rain sensor
	 * @param rainSensor
	 *            adds the rainSensor
	 */
	public void addRainSensor(RainSensor rainSensor) {
		this.rainSensor = rainSensor;
	}

	/**
	 * Method to remove rain sensor 
	 * Remove rainSensor adds the rainSensor
	 */
	public void removeRainSensor() {
		this.rainSensor = null;
	}

}
