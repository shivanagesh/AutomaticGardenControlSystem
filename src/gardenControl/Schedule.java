/**
 * 
 */
package gardenControl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import wateringSystem.WaterPressure;

/**
 * <h1> This class store schedules that are need to performed in garden management.
 * User can store many schedule with following implementation but, in out garden
 * management system, currently using a single schedule</h1>
 * <p>
 * This class store schedules that are need to performed in garden management.
 * User can store many schedule with following implementation but, in out garden
 * management system, currently using a single schedule
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 22, 2016 Schedule.java
 */
public class Schedule {

	/**
	 * waterTimings - Stores water timings, it store multiple watering timing
	 * for single schedule Note: In Garden management, only one watering time is
	 * stored
	 */
	private List<List<Integer>> waterTimings;

	/**
	 * minTemperature, Minimum temperature that is needed to maintain while this
	 * schedule is running
	 */
	private int minTemperature;
	/**
	 * maxTemperature, Maximum temperature that is needed to maintain while this
	 * schedule is running
	 */
	private int maxTemperature;
	/**
	 * waterPressure, Water pressure level that is needed to maintain while this
	 * schedule is running
	 */
	private WaterPressure waterPressure;

	/**
	 * id - Unique id which is used to identify the schedule
	 */
	private int id;

	/**
	 * Time in 1-24 hours format
	 */
	/**
	 * scheduleStartTime, Start time of schedule
	 */
	private float scheduleStartTime;
	/**
	 * scheduleStartTime, End time of schedule
	 */
	private float scheduleEndTime;
	private static AtomicInteger uniqueId = new AtomicInteger();

	/**
	 * Default constructor, which load initial values
	 */
	public Schedule() {
		// TODO Auto-generated constructor stub
		this.waterPressure = WaterPressure.MEDIUM;
		this.minTemperature = 50;
		this.maxTemperature = 80;
		waterTimings = new ArrayList<List<Integer>>();
		this.id = uniqueId.incrementAndGet();
	}

	/**
	 * Method add water timing to schedule
	 * 
	 * @param timings,
	 *            This Array List contains timing, First start time, then end
	 *            time
	 */

	public void addWaterTimings(List<Integer> timings) {
		this.waterTimings.add(timings);

	}

	/**
	 * Method gives all water timings of schedule
	 * 
	 * @return List of watering times
	 * 
	 */

	public List<List<Integer>> getWaterTimings() {
		return this.waterTimings;

	}

	/**
	 * Method removes water timing from schedule
	 * 
	 * @param timings,
	 *            This Array List contains timing, First start time, then end
	 *            time
	 */

	public void removeWaterTimings(List<Integer> timings) {

		this.waterTimings.remove(timings);

	}

	/**
	 * Method give Minimum temperature of schedule
	 * 
	 * @return the minTemperature
	 */
	public int getMinTemperature() {
		return minTemperature;
	}

	/**
	 * Method set Minimum temperature of schedule
	 * 
	 * @param minTemperature
	 *            the minTemperature to set
	 */
	public void setMinTemperature(int minTemperature) {
		this.minTemperature = minTemperature;
	}

	/**
	 * Method give Maximum temperature of schedule
	 * 
	 * @return the maxTemperature
	 */
	public int getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * Method set Maximum temperature of schedule
	 * 
	 * @param maxTemperature
	 *            the maxTemperature to set
	 */
	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	/**
	 * Method give Water Pressure of schedule
	 * 
	 * @return the waterPressure
	 */
	public WaterPressure getWaterPressure() {
		return waterPressure;
	}

	/**
	 * Method set Water Pressure of schedule
	 * 
	 * @param waterPressure
	 *            the waterPressure to set
	 */
	public void setWaterPressure(WaterPressure waterPressure) {
		this.waterPressure = waterPressure;
	}

	/**
	 * Method set Water Pressure of schedule
	 * 
	 * @param waterPressure
	 *            the waterPressure to set
	 */
	public void setWaterPressure(String waterPressure) {
		this.waterPressure = WaterPressure.valueOf(waterPressure);
	}

	/**
	 * Method give id of schedule
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Method give Start Time of schedule
	 * 
	 * @return the scheduleStartTime
	 */
	public float getScheduleStartTime() {
		return scheduleStartTime;
	}

	/**
	 * Method set Start Time of schedule
	 * 
	 * @param scheduleStartTime
	 *            the scheduleStartTime to set
	 */
	public void setScheduleStartTime(float scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	/**
	 * Method give End Time of schedule
	 * 
	 * @return the scheduleEndTime
	 */
	public float getScheduleEndTime() {
		return scheduleEndTime;
	}

	/**
	 * Method set End Time of schedule
	 * 
	 * @param scheduleEndTime
	 *            the scheduleEndTime to set
	 */
	public void setScheduleEndTime(float scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}

}
