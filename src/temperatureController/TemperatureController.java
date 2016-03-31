/**
 * 
 */
package temperatureController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import devices.Device;
import sensors.TemperatureSensor;

/**
 * <h1> Temperature Controller</h1>
 * <p> it manages heater and cooler to maintain temperature of garden</p>
 * @author Shivanagesh Chandra Feb 19, 2016 TemperatorController.java
 */
public class TemperatureController {
	private TemperatureSensor temperatureSensor;
	private Device heater;
	private Device cooler;
	private boolean userOveride;
	private boolean isScheduleRunning;

	private int minTemperatue;
	private int maxTemperature;
	private int scheduleMinTemperatue;
	private int scheduleMaxTemperatue;
	
	private int currentMinTemperature;
	private int currentMaxTemperature;

	public TemperatureController() {
		minTemperatue = 20;
		maxTemperature = 80;
		heater = null;
		cooler = null;
		temperatureSensor = null;
		userOveride=false;
		isScheduleRunning = false;

	}

	public void setTempeartureRange(int min, int max) {
		this.minTemperatue = min;
		this.maxTemperature = max;
	}
	
	public void setScheduleTempeartureRange(int min, int max) {
		this.scheduleMinTemperatue = min;
		this.scheduleMaxTemperatue = max;
	}

	public List<Integer> getTempeartureRange() {
		return new ArrayList<>(Arrays.asList(this.minTemperatue, this.maxTemperature));

	}

	public void removeHeater() {
		this.heater = null;
	}

	public void removeCooler() {
		this.cooler = null;
	}

	public void removeTempeartureSensor() {
		this.temperatureSensor = null;
	}

	public TemperatureStatus getTemperatureStatus() {
		updateTemperature();
		if (temperatureSensor.getSensorTemperature() > currentMinTemperature
				&& temperatureSensor.getSensorTemperature() < currentMaxTemperature && !userOveride) {
			if (this.cooler.getStatus())
				this.cooler.turnOff();
			if (this.heater.getStatus())
				this.heater.turnOff();
			return TemperatureStatus.InControl;
		} else {
			return TemperatureStatus.outOfRange;
		}
	}

	public void controlTemperature() {
	
		if(!userOveride){
		if (temperatureSensor.getSensorTemperature() < currentMinTemperature) {
			if (this.cooler.getStatus())
				this.cooler.turnOff();
			if (!this.heater.getStatus()) {
				this.heater.turnOn();
				//Replicate heater function 
				this.getTemperatureSensor().setSensorTemperature(this.getTemperatureSensor().getSensorTemperature()+1);
			}else{
				//Replicate heater function 
				this.getTemperatureSensor().setSensorTemperature(this.getTemperatureSensor().getSensorTemperature()+1);
			}
		} else if (temperatureSensor.getSensorTemperature() > currentMaxTemperature) {
			if (this.heater.getStatus())
				this.heater.turnOff();
			if (!this.cooler.getStatus()) {
				this.cooler.turnOn();
				//Replicate Cooler function 
				this.getTemperatureSensor().setSensorTemperature(this.getTemperatureSensor().getSensorTemperature()-1);
			}else{
				//Replicate Cooler function 
				this.getTemperatureSensor().setSensorTemperature(this.getTemperatureSensor().getSensorTemperature()-1);
			}

		} else {
			if (this.cooler.getStatus())
				this.cooler.turnOff();
			if (this.heater.getStatus())
				this.heater.turnOff();
		}}else{
			if(this.getHeater().getStatus())
				//Replicate heater function 
				this.getTemperatureSensor().setSensorTemperature(this.getTemperatureSensor().getSensorTemperature()+1);
			else if(this.getCooler().getStatus())
				//Replicate Cooler function 
				this.getTemperatureSensor().setSensorTemperature(this.getTemperatureSensor().getSensorTemperature()-1);
		}

	}

	/**
	 * @return the temperatureSensor
	 */
	public TemperatureSensor getTemperatureSensor() {
		return temperatureSensor;
	}

	/**
	 * @param temperatureSensor
	 *            Add temperatureSensor to TempeartureController
	 */
	public void addTemperatureSensor(TemperatureSensor temperatureSensor) {
		this.temperatureSensor = temperatureSensor;
	}

	/**
	 * @return the heater
	 */
	public Device getHeater() {
		return heater;
	}

	/**
	 * @param heater
	 *            Add heater to TempeartureController
	 */
	public void addHeater(Device heater) {
		this.heater = heater;
	}

	/**
	 * @return the cooler
	 */
	public Device getCooler() {
		return cooler;
	}

	/**
	 * @param cooler
	 *            Add cooler to TempeartureController
	 */
	public void addCooler(Device cooler) {
		this.cooler = cooler;
	}

	/**
	 * @return the minTemperatue
	 */
	public int getMinTemperatue() {
		return minTemperatue;
	}

	/**
	 * @param minTemperatue the minTemperatue to set
	 */
	public void setMinTemperatue(int minTemperatue) {
		this.minTemperatue = minTemperatue;
	}

	/**
	 * @return the maxTemperature
	 */
	public int getMaxTemperature() {
		return maxTemperature;
	}

	/**
	 * @param maxTemperature the maxTemperature to set
	 */
	public void setMaxTemperature(int maxTemperature) {
		this.maxTemperature = maxTemperature;
	}

	/**
	 * @return the userOveride
	 */
	public boolean isUserOveride() {
		return userOveride;
	}

	/**
	 * @param userOveride the userOveride to set
	 */
	public void setUserOveride(boolean userOveride) {
		this.userOveride = userOveride;
	}

	/**
	 * @return the isScheduleRunning
	 */
	public boolean isScheduleRunning() {
		return isScheduleRunning;
	}

	/**
	 * @param isScheduleRunning the isScheduleRunning to set
	 */
	public void setScheduleRunning(boolean isScheduleRunning) {
		this.isScheduleRunning = isScheduleRunning;
	}

	/**
	 * @return the scheduleMinTemperatue
	 */
	public int getScheduleMinTemperatue() {
		return scheduleMinTemperatue;
	}

	/**
	 * @param scheduleMinTemperatue the scheduleMinTemperatue to set
	 */
	public void setScheduleMinTemperatue(int scheduleMinTemperatue) {
		this.scheduleMinTemperatue = scheduleMinTemperatue;
	}

	/**
	 * @return the scheduleMaxTemperatue
	 */
	public int getScheduleMaxTemperatue() {
		return scheduleMaxTemperatue;
	}

	/**
	 * @param scheduleMaxTemperatue the scheduleMaxTemperatue to set
	 */
	public void setScheduleMaxTemperatue(int scheduleMaxTemperatue) {
		this.scheduleMaxTemperatue = scheduleMaxTemperatue;
	}
	
	
	/***
	 * Update Temperature variable
	 */
    public void updateTemperature(){
    	if(isScheduleRunning){
    		currentMaxTemperature = scheduleMaxTemperatue;
    		currentMinTemperature = scheduleMinTemperatue;
    	}else{
    		currentMaxTemperature = maxTemperature;
    		currentMinTemperature =  minTemperatue;		
    	}
    }
}
