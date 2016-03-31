/**
 * 
 */
package wateringSystem;

import java.util.concurrent.atomic.AtomicInteger;

import plant.Plant;

/**
 * <h1> Sprinkler super class for sprinkler implementation class </h1>
 * <p> It contains common function for sprinkler's </p> 
 * @author Shivanagesh Chandra Feb 18, 2016 2016 Sprinkler.java
 */
public abstract class Sprinkler {

	private static AtomicInteger uniqueId = new AtomicInteger();
	protected boolean active;
	protected int id;
	protected int zone;
	protected Plant plant;

	public Sprinkler(int zone, Plant plant) {
		this.active = false;
		this.id = uniqueId.getAndIncrement();
		this.zone = zone;
		this.plant = plant;
	}

	/**
	 * Abstract method
	 */
	public void turnOff() {
	}

	/**
	 * Abstract method
	 */
	public void turnOn() {
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the zone
	 */
	public int getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(int zone) {
		this.zone = zone;
	}

	/**
	 * @return the plant
	 */
	public Plant getPlant() {
		return plant;
	}

	/**
	 * @param plant the plant to set
	 */
	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	

}
