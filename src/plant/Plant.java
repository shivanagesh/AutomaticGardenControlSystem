/**
 * 
 */
package plant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import sensors.Sensor;
import wateringSystem.Sprinkler;

/**
 *  <h1>Plant super class for all kinds of plants</h1>
 * <p>
 * It contains common functions which are required by the all plants
 * </p>
 * @author Shivanagesh Chandra Feb 18, 2016 2016 Plant.java
 */
public abstract class Plant {

	private static AtomicInteger uniqueId = new AtomicInteger();
	protected String type;
	protected int id;
	protected List<Sensor> sensors;
	protected Sprinkler sprinkler;
	protected String name;
	protected int zone;
	protected int ageInDays;
	protected double growth;
	protected int daysTomaturity;
	

	/**
	 * Parameterized constructor
	 * @param type type of plant
	 * @param name  Name of plant 
	 * @param zone  Zone in which is present 
	 * @param daysTomaturity Number of days after plant can be reached mature stage
	 */
	public Plant(String type, String name,int zone,int daysTomaturity) {
		this.id = uniqueId.getAndIncrement();
		this.type = type;
		this.name = name;
		sensors = new ArrayList<>();
		sprinkler = null;
		this.zone = zone;
		this.ageInDays = 0;
		this.daysTomaturity = daysTomaturity;

	}

   /**
    * Method to add sensor to plant	
    * @param sensor sensor that is need 
    */

	public void addSensor(Sensor sensor) {
		sensors.add(sensor);
	}

	public void removeSensor(Sensor sensor) {
		for (Sensor sen : sensors) {
			if (sen.getId() == sensor.getId()) {
				sensors.remove(sen);
			}
		}
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the sprinkler
	 */
	public Sprinkler getSprinkler() {
		return sprinkler;
	}

	/**
	 * @param sprinkler
	 *            the sprinkler to set
	 */
	public void setSprinkler(Sprinkler sprinkler) {
		this.sprinkler = sprinkler;
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
	 * @return the ageInDays
	 */
	public int getAgeInDays() {
		return ageInDays;
	}

	/**
	 * Increase age by 1 day
	 */

	public void increaseAge() {
		this.ageInDays = this.ageInDays+1;
	}

	/**
	 * @return the growth
	 */
	public double getGrowth() {
		return growth;
	}

	/**
	 * @param growth the growth to set
	 */
	public void setGrowth(double growth) {
		this.growth = growth;
	}

	/**
	 * @return the dayTomaturity
	 */
	public int getDaysTomaturity() {
		return daysTomaturity;
	}

	/**
	 * @param daysTomaturity  Set daysTOMaturity 
	 */
	public void setDaysTomaturity(int daysTomaturity) {
		this.daysTomaturity = daysTomaturity;
	}



	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}



	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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

	

}
