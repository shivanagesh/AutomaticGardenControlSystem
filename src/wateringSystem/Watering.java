/**
 * 
 */
package wateringSystem;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import Base.Base;

/**
 * <h1>Water Module</h1>
 * 
 * @author Shivanagesh Chandra Feb 17, 2016 2016 Watering.java
 */
public class Watering implements Runnable {
	private Thread Watering = null;
	private List<Sprinkler> sprinklers;
	private boolean status;
	private int scheduleDuration;
	private WaterSystemStatus systemStatus;
	private WaterPressure waterPressure;

	public Watering() {
		this.status = false;
		this.scheduleDuration = 0;
		Watering = new Thread(this);
		systemStatus = WaterSystemStatus.IDLE;

	}

	public void run() {
		Base.getLogger().log(Level.INFO, "System : Watering System turned on");
		while (!Watering.isInterrupted() && status) {
			try {
				if (scheduleDuration != 0) {
					for (Sprinkler sprinkler : sprinklers) {
						sprinkler.turnOn();
					}
					Thread.sleep(TimeUnit.MINUTES.toMillis(scheduleDuration));
					status = false;
					

				}
				scheduleDuration = 0;
			} catch (Exception ex) {
				Base.getLogger().log(Level.SEVERE, ex.getMessage());
			}
		}
		for (Sprinkler sprinkler : sprinklers) {
			sprinkler.turnOff();

		}
		if(systemStatus != WaterSystemStatus.RAINING)
			systemStatus = WaterSystemStatus.IDLE;

		Base.getLogger().log(Level.INFO, "System : Watering System turned off");

	}

	public boolean getStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setSprinklerConfig(List<Sprinkler> sprinkler) {
		this.sprinklers = sprinkler;
	}

	public void turnOn() {

		this.status = true;
		if (Watering.isAlive()) {
			Watering.run();
		} else {
			Watering = new Thread(this);
			Watering.start();
		}
		systemStatus = WaterSystemStatus.WATERING;
	}

	public void trunOff() {
		this.status = false;
		if (Watering != null) {
			Watering.interrupt();
		} else {
			Base.getLogger().log(Level.INFO, "System : Watering System turned off");
		}
	}

	/**
	 * @param time in Minutes
	 */
	public void schedule(int time) {
		Base.getLogger().log(Level.INFO, "System : Watering System schedule turned on");
		this.scheduleDuration = time;
		this.turnOn();

	}

	public void raining() {
		if (status) {
			Base.getLogger().log(Level.INFO, "System : Raining");
			systemStatus = WaterSystemStatus.RAINING;
			this.status = false;
			if (Watering != null) {
				Watering.interrupt();
			} else {
				Base.getLogger().log(Level.INFO, "Watering System turned off");
			}
		}else{
			Base.getLogger().log(Level.INFO, "System : Raining");
			systemStatus = WaterSystemStatus.RAINING;
		}

	}

	public void rainingStoped() {
		Base.getLogger().log(Level.INFO, "System : Raining Stoped");
		systemStatus = WaterSystemStatus.IDLE;
	}

	/**
	 * @return the systemStatus
	 */
	public WaterSystemStatus getSystemStatus() {
		return systemStatus;
	}

	/**
	 * @return the waterPressure
	 */
	public WaterPressure getWaterPressure() {
		return waterPressure;
	}

	/**
	 * @param waterPressure the waterPressure to set
	 */
	public void setWaterPressure(WaterPressure waterPressure) {
		this.waterPressure = waterPressure;
	}

}


