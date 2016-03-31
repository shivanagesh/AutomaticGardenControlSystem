/**
 * 
 */
package fertilization;

import sensors.SoilSensor;

/**
 * <h1>Functions that related to soil checking, fertilization status </h1>
 * @author Shivanagesh Chandra Feb 19, 2016 FertilizationCOntroller.java
 */
public class FertilizationController {

	private int minPH;
	private int maxPH;

	private SoilStatus status;

	private SoilSensor soilSensor;

	public FertilizationController() {
		soilSensor = null;
	}

	/**
	 * Method to add soil sensor 
	 * @param sensor
	 *             sensor that is need to added 
	 */
	public void addSoilSensor(SoilSensor sensor) {
		this.soilSensor = sensor;
	}

	/**
	 * Method to get minimum PH
	 * @return the minPH
	 */
	public int getMinPH() {
		return minPH;
	}

	/**
	 * Method to set minimum PH
	 * @param minPH
	 *            the minPH to set
	 */
	public void setMinPH(int minPH) {
		this.minPH = minPH;
	}

	/**
	 * Method to get maximum PH
	 * @return the maxPH
	 */
	public int getMaxPH() {
		return maxPH;
	}

	/**
	 * Method to set maximum PH
	 * @param maxPH
	 *            the maxPH to set
	 */
	public void setMaxPH(int maxPH) {
		this.maxPH = maxPH;
	}

	/**
	 * Method to get soil status
	 * @return the Soil status
	 */
	public SoilStatus getSoilSoilStatus() {

		int sensorPH = this.soilSensor.getSensorPH();
	
		if (sensorPH >= minPH && sensorPH <= maxPH) {
			status = SoilStatus.Good;
		} else if (minPH > sensorPH && ((minPH - sensorPH) > 2)) {
				status = SoilStatus.NeedFertilizer;

		} else if (maxPH < sensorPH && ((sensorPH - maxPH) > 2)) {
				status = SoilStatus.NeedFertilizer;

		} else {
			status = SoilStatus.Degrading;

		}
		
		return status;
	}

	/**
	 * Method to get soil sensor 
	 * @return the soilSensor
	 */
	public SoilSensor getSoilSensor() {
		return soilSensor;
	}

}
