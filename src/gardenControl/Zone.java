/**
 * 
 */
package gardenControl;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import fertilization.FertilizationController;
import plant.Plant;
import sensors.FireSensor;
import sensors.PestSensor;
import temperatureController.TemperatureController;
import wateringSystem.WaterController;

/**
 * <h1>Garden can be divided into zones, each zone many have different option like
 * Temperature control, water control ,soil control etc</h1>
 * 
 * <p>
 * Garden can be divided into zones, each zone many have different option like
 * Temperature control, water control ,soil control etc
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 19, 2016 Zone.java
 */
public class Zone {

	/**
	 * Store plants in the zone
	 */
	private List<Plant> plants;
	/**
	 * Temperature Controller - which controls temperature in zone
	 */
	private TemperatureController temperatuerController;
	/**
	 * Water Controller - which controls watering in zone
	 */
	private WaterController waterController;
	/**
	 * Fertilization Controller- which controls fertilizer in zone
	 */
	private FertilizationController fertilizerController;
	/**
	 * Fire sensor
	 */
	private FireSensor fireSensor;
	/**
	 * unique id
	 */
	private int id;
	/**
	 * schedules a zone may have more than one schedule
	 */
	private List<Schedule> schedules;
	private static AtomicInteger uniqueId = new AtomicInteger();
	/**
	 * Store days of system
	 */
	private int days;
	/**
	 * Pest sensor
	 */

	private PestSensor pestSensor;

	/**
	 * Default constructor, Which is used for loading initial values
	 */
	public Zone() {
		id = uniqueId.incrementAndGet();
		temperatuerController = null;
		waterController = null;
		fertilizerController = null;
		plants = new ArrayList<>();
		fireSensor = null;
		schedules = new ArrayList<>();
		setDays(0);
	}

	/**
	 * Method to check if temperatureController exists.
	 * 
	 * @return True if temperatureController exists.
	 */
	public boolean temperatureControllerExists() {
		if (temperatuerController != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method to check if waterController exists.
	 * 
	 * @return True if waterController exists.
	 */
	public boolean waterControllerExists() {
		if (waterController != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method to check if fireSensor exists.
	 * 
	 * @return True if fireSensor exists.
	 */
	public boolean fireSensorExists() {
		if (fireSensor != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method to check if schedule exists.
	 * 
	 * @return True if Schedules exists.
	 */
	public boolean schedulesExists() {
		if (schedules.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Method to check if fertilizerController exists.
	 * 
	 * @return True if fertilizerController exists.
	 */
	public boolean fertilizerControllerExists() {
		if (fertilizerController != null) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method add plant to zone
	 * 
	 * @param plant
	 *            add plant to zone
	 */

	public void addPlant(Plant plant) {
		plants.add(plant);
	}

	/**
	 * Method gives all plant of zone
	 * 
	 * @return All plants in the system
	 */

	public List<Plant> getPlants() {
		return this.plants;
	}

	/**
	 * @param plant
	 *            remove plant to zone
	 */

	public void removePlant(Plant plant) {
		for (Plant p : plants) {
			if (p.getId() == plant.getId()) {
				plants.remove(plant);
			}
		}
	}

	/**
	 * Method return TemperatureController
	 * 
	 * @return the temperatuerController
	 */
	public TemperatureController getTemperatuerController() {
		return temperatuerController;
	}

	/**
	 * Method add TemperatureController
	 * 
	 * @param temperatuerController
	 *            the temperatuerController to set
	 */
	public void addTemperatuerController(TemperatureController temperatuerController) {
		this.temperatuerController = temperatuerController;
	}

	/**
	 * Method return WaterController
	 * 
	 * @return the waterController
	 */
	public WaterController getWaterController() {
		return waterController;
	}

	/**
	 * Method add WaterController
	 * 
	 * @param waterController
	 *            the waterController to set
	 */
	public void addWaterController(WaterController waterController) {
		this.waterController = waterController;
	}

	/**
	 * Method return FertilizationController
	 * 
	 * @return the fertilizerController
	 */
	public FertilizationController getFertilizerController() {
		return fertilizerController;
	}

	/**
	 * Method add FertilizationController
	 * 
	 * @param fertilizerController
	 *            the fertilizerController to set
	 */
	public void addFertilizerController(FertilizationController fertilizerController) {
		this.fertilizerController = fertilizerController;
	}

	/**
	 * Method return Fire sensor
	 * 
	 * @return the fireSensor
	 */
	public FireSensor getFireSensor() {
		return fireSensor;
	}

	/**
	 * Method add Fire sensor
	 * 
	 * @param fireSensor
	 *            the fireSensor to set
	 */
	public void addFireSensor(FireSensor fireSensor) {
		this.fireSensor = fireSensor;
	}

	/**
	 * Method remove Fire sensor 
	 */
	public void removeFireSensor() {
		this.fireSensor = null;
	}

	/**
	 *  Method return id
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 *  Method add Schedule
	 * @param schedule
	 *            add Schedule to zone
	 */

	public void addSchedule(Schedule schedule) {
		schedules.add(schedule);
	}

	/**
	 *  Method return all schedules
	 * @return List of schedules
	 */

	public List<Schedule> getSchedules() {
		return schedules;
	}

	/**
	 *  Method remove Schedule
	 * @param schedule
	 *            remove schedule to zone
	 */

	public void removeSchedule(Schedule schedule) {
		for (Schedule s : schedules) {
			if (s.getId() == schedule.getId()) {
				schedules.remove(schedule);
			}
		}
	}

	/**
	 *  Method return first Schedule for UI purpose
	 * @return First schedule
	 */
	public Schedule getFirstSchedule() {
		return schedules.get(0);
	}

	/**
	 *  Method  return true if plants exists
	 * @return Return True if plants exists, else false
	 */
	public boolean plantsExists() {
		if (plants.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 *  Method  return days of system
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * Method  set days of system
	 * @param days
	 *            the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * Method return pestSensor
	 * @return the pestSensor
	 */
	public PestSensor getPestSensor() {
		return pestSensor;
	}

	/**
	 * Method add pestSensor
	 * @param pestSensor
	 *            the pestSensor to set
	 */
	public void addPestSensor(PestSensor pestSensor) {
		this.pestSensor = pestSensor;
	}

	/**
	 * @return True if pest sensor present
	 */
	public boolean pestSensorExists() {
		if (pestSensor != null) {
			return true;
		} else {
			return false;
		}
	}

}
