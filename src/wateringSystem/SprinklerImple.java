/**
 * 
 */
package wateringSystem;

import java.util.logging.Level;

import Base.Base;
import plant.Plant;

/**
 * <h1> Sprinkler imple </h1>
 * <p>
 * Which is implementation for sprinkler abstract class </p>
 * @author Shivanagesh Chandra Feb 18, 2016 2016 SprinklerImple.java
 */
public class SprinklerImple extends Sprinkler {

	/**
	 * Parameterized constructor
	 * 
	 * @param zone zone id in which sprinkler is located 
	 * @param plant plant for which sprinkler is assigned 
	 */
	public SprinklerImple(int zone, Plant plant) {
		super(zone, plant);
	}

	
	/**
	 * To turn off sprinkler
	 */
	@Override
	public void turnOff() {
		this.active = false;
		Base.getLogger().log(Level.INFO, "System : Sprinkler " + this.id + " turned off ");
	}

	/**
	 * To turn on sprinkler
	 */
	@Override
	public void turnOn() {
		this.active = true;
		Base.getLogger().log(Level.INFO, "System :  Sprinkler " + this.id + " turned on ");
	}

}
