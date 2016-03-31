/**
 * 
 */
package gardenControl;

import java.util.ArrayList;

import java.util.List;
import java.util.logging.Level;

import Base.Base;
import devices.Cooler;
import devices.Heater;
import fertilization.FertilizationController;
import plant.FlowerPlant;
import plant.FruitPlant;
import plant.Plant;
import sensors.FireSensor;
import sensors.PestSensor;
import sensors.RainSensor;
import sensors.SoilSensor;
import sensors.TemperatureSensor;
import temperatureController.TemperatureController;
import temperatureController.TemperatureStatus;
import view.controllers.SystemUIController;
import wateringSystem.SprinklerImple;
import wateringSystem.WaterController;
import wateringSystem.WaterPressure;
import wateringSystem.WaterSystemStatus;
import wateringSystem.Watering;

/**
 * <h1>This is controller for Garden functionality</h1>
 * <p>
 * This is controller for Garden functionality
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 15, 2016 2016 GardenController.java
 */
public class GardenController implements Runnable {

	/**
	 * zones, Garden is divided zone where each zone contains of similar kind of
	 * tables
	 */
	private List<Zone> zones = new ArrayList<Zone>();
	/**
	 * uiController, This Main UI for entire garden management system, this UI
	 * contains, currently shows only one zone in future we can expand to
	 * multiple zones.
	 */
	private SystemUIController uiController;
	/**
	 * This is time in garden system. which counts time in minutes, Every 24
	 * minutes as one day.
	 */
	private Timer timer;
	/**
	 * This Thread which runs continuously to monitor over all system like
	 * temperature control, Soil controlling, fire sensor checking, rain sensor
	 * checking.
	 */
	private Thread threadGC;

	/**
	 * This represent currentZone, which zone currently monitored by garden
	 * controller. This public it can be accessed directly
	 */
	public int currentZone;

	/**
	 * Default constructor, Which is used for loading initial values
	 */

	public GardenController() {
		timer = new Timer();
		Base.getLogger().log(Level.INFO, "System : Garden initiated");
		this.threadGC = new Thread(this);

	}

	/**
	 * Parameterized constructor, which is used load initial values, initialize
	 * UI controller of garden
	 * 
	 * @param uiController
	 *            UI of Garden management
	 */

	public GardenController(SystemUIController uiController) {
		timer = new Timer();
		Base.getLogger().log(Level.INFO, "System :  Garden initiated", "System");
		Base.getLogger().log(Level.INFO, "System : System UI initiated", "System");
		this.uiController = uiController;
		this.threadGC = new Thread(this);

		loadInitialValues();
	}

	/**
	 * Method creates only zone, adds all controller and sensors to garden.
	 */
	public void loadInitialValues() {
		// Create zone
		Zone zone = new Zone();
		this.addZone(zone);
		currentZone = zone.getId();

		// Schedule
		Schedule s1 = new Schedule();
		s1.setScheduleStartTime(1);
		s1.setScheduleEndTime(3);
		s1.setMaxTemperature(75);
		s1.setMinTemperature(65);
		s1.setWaterPressure(WaterPressure.HIGH);
		List<Integer> waterTiming = new ArrayList<Integer>();
		waterTiming.add(0);
		waterTiming.add(2);
		s1.addWaterTimings(waterTiming);
		zone.addSchedule(s1);

		// Fertilizer controller
		FertilizationController fc = new FertilizationController();
		zone.addFertilizerController(fc);

		SoilSensor ss = new SoilSensor();
		fc.addSoilSensor(ss);
		fc.setMaxPH(8);
		fc.setMinPH(6);
		fc.getSoilSensor().setSensorPH(6);

		// temperature Control
		TemperatureController tc = new TemperatureController();
		zone.addTemperatuerController(tc);
		TemperatureSensor ts = new TemperatureSensor();
		tc.addTemperatureSensor(ts);
		Heater h = new Heater();
		Cooler c = new Cooler();
		tc.addCooler(c);
		tc.addHeater(h);
		tc.setTempeartureRange(50, 80);
		tc.getTemperatureSensor().setSensorTemperature(20);

		// Water Control and plants
		WaterController wc = new WaterController();
		zone.addWaterController(wc);
		RainSensor rs = new RainSensor();
		wc.addRainSensor(rs);
		Watering w = new Watering();
		wc.addWaterSystem(w);
		for (int i = 0; i < 10; i++) {
			Plant p = new FruitPlant("Mango", zone.getId(), 30);
			SprinklerImple sp = new SprinklerImple(zone.getId(), p);
			wc.addSprinkler(sp);
			zone.addPlant(p);
		}
		for (int i = 0; i < 10; i++) {
			Plant p = new FlowerPlant("Rose", zone.getId(), 2);
			SprinklerImple sp = new SprinklerImple(zone.getId(), p);
			wc.addSprinkler(sp);
			zone.addPlant(p);
		}

		// Fire sensor
		FireSensor fs = new FireSensor();
		zone.addFireSensor(fs);
		threadGC.start();

		// Pest Sensor
		PestSensor ps = new PestSensor();
		zone.addPestSensor(ps);
	}

	/**
	 * Method to get the zone
	 * 
	 * @param id,
	 *            id of zone
	 * @return zone of given id
	 */
	public Zone getZone(int id) {
		for (Zone zone : zones) {
			if (zone.getId() == id) {
				return zone;
			}
		}
		return null;
	}

	/**
	 * Method add the zone
	 * 
	 * @param zone
	 *            add the zone to garden
	 */
	public void addZone(Zone zone) {
		this.zones.add(zone);
	}

	/**
	 * Remove the zone to garden
	 * 
	 * @param zone
	 *            Remove the specific zone
	 */
	public void removeZone(Zone zone) {
		for (Zone tem_zone : zones) {

			if (tem_zone.getId() == zone.getId()) {
				zones.remove(zone);
			}

		}
	}

	/**
	 * Run method which monitor garden system
	 */
	public void run() {
		Base.getLogger().log(Level.INFO, " System: Process runing started");
		while (true) {
			for (Zone zone : zones) {
				uiController.setZoneid(zone.getId());
				uiController.setDaysSatus(Integer.toString(timer.getDays()));
				uiController.setTimeSatus(timer.currentTimeString());
				Base.getLogFormatter().setSystemTime(timer.currentTimeString());
				Base.getLogFormatter().setDay(Integer.toString(timer.getDays()));

				// Temperature control
				if (zone.temperatureControllerExists()) {
					TemperatureController tem = zone.getTemperatuerController();
					if (!(TemperatureStatus.InControl == tem.getTemperatureStatus())) {
						tem.controlTemperature();
					}
					int temepratureInFahrenheits = tem.getTemperatureSensor().getSensorTemperature();
					int temperatureInCelsius = (temepratureInFahrenheits - 32) * 5 / 9;
					try {
						uiController.setTemperatureStatus(
								(temepratureInFahrenheits + " F / " + temperatureInCelsius + " C"));
					} catch (Exception ex) {
						Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
						System.out.println((temepratureInFahrenheits + " F / " + temperatureInCelsius + " C"));
					}

				}

				// Fire detector
				if (zone.fireSensorExists()) {
					FireSensor fs = zone.getFireSensor();
					if (fs.isActive() && fs.isStatus() && fs.isAlarm()) {
						try {
							uiController.setFireSatus("Fire is detected");
						} catch (Exception ex) {
							Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
							System.out.println("Fire is detected");
						}
					} else if (fs.isActive()) {
						try {
							uiController.setFireSatus("Active");

						} catch (Exception ex) {
							Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
							System.out.println("Active");
						}
					} else {
						try {
							uiController.setFireSatus("Deactived");
						} catch (Exception ex) {
							Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
							System.out.println("Deactived");
						}

					}
				}

				// Fertilization checking
				if (zone.fertilizerControllerExists()) {
					FertilizationController fc = zone.getFertilizerController();
					try {
						uiController.setSoilStatus(fc.getSoilSoilStatus().toString());
					} catch (Exception ex) {
						Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
						System.out.println(fc.getSoilSoilStatus().toString());
					}

				}

				// WaterSystem Status
				if (zone.waterControllerExists()) {
					WaterController wc = zone.getWaterController();
					if (wc.getRainSensor().getStatus() && wc.getRainSensor().isActive()) {
						wc.getWaterSystem().raining();
					} else if (!(wc.getRainSensor().getStatus() && wc.getRainSensor().isActive())
							&& wc.getWaterSystem().getSystemStatus() == WaterSystemStatus.RAINING) {
						wc.getWaterSystem().rainingStoped();
					}
					try {
						uiController.setWaterSatus(wc.getWaterSystem().getSystemStatus().toString());
					} catch (Exception ex) {
						Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
						System.out.println(wc.getWaterSystem().getSystemStatus().toString());
					}

				}

				// Rain sensor detector
				if (zone.waterControllerExists()) {
					RainSensor rs = zone.getWaterController().getRainSensor();
					if (rs.isActive() && rs.getStatus()) {
						try {
							uiController.setRainSatus("Raining");
						} catch (Exception ex) {
							Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
							System.out.println("Raining");
						}
					} else if (rs.isActive()) {
						try {
							uiController.setRainSatus("Active");
						} catch (Exception ex) {
							Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
							System.out.println("Active");
						}
					} else {
						try {
							uiController.setRainSatus("Deactived");
						} catch (Exception ex) {
							Base.getLogger().log(Level.SEVERE, "System: UI not yet initialized");
							System.out.println("Deactived");
						}
					}
				}

				// Schedules
				if (zone.schedulesExists()) {
					try {
						List<Schedule> s = zone.getSchedules();
						for (Schedule schedule : s) {

							if (timer.currentTime() >= schedule.getScheduleStartTime()
									&& timer.currentTime() < schedule.getScheduleEndTime()) {
								zone.getTemperatuerController().setScheduleRunning(true);
								uiController.setScheduleStatus("Running");

								for (List<Integer> timing : schedule.getWaterTimings()) {

									if (timing.get(0) == (int) timer.currentTime() && timer.getCurrentTimeInSec() < 3) {

										if (zone.waterControllerExists()) {
											if (zone.getWaterController().getWaterSystem()
													.getSystemStatus() != WaterSystemStatus.WATERING
													&& !zone.getWaterController().getRainSensor().getStatus()) {
												zone.getWaterController().getWaterSystem()
														.schedule(timing.get(1) - timing.get(0));
												Base.getLogger().log(Level.FINE, "System: Watering schedule deployed");
											} else {
												Base.getLogger().log(Level.SEVERE,
														"System: Collusion watering system busy");
											}
										}
									}
								}
								if (zone.temperatureControllerExists()) {
									TemperatureController tem = zone.getTemperatuerController();
									tem.setScheduleTempeartureRange(schedule.getMinTemperature(),
											schedule.getMaxTemperature());
								}

							} else {
								zone.getTemperatuerController().setScheduleRunning(false);
								uiController.setScheduleStatus("Not running");
							}

						}
					} catch (Exception ie) {
						ie.printStackTrace();
						Base.getLogger().log(Level.SEVERE, "System: Error while schduling watering");
					}
				}

				// Pest detection
				if (zone.pestSensorExists()) {
					PestSensor ps = zone.getPestSensor();
					if (ps.isActive() && ps.isStatus()) {
						uiController.setPestStatus("Need pestiside");
					} else if (ps.isActive() && !ps.isStatus()) {
						uiController.setPestStatus("No need");
					} else {
						uiController.setPestStatus("Sensor deactive");
					}
				}

				if (zone.getDays() + 1 == timer.getDays()) {
					System.out.println("day incremented");
					zone.setDays(timer.getDays());
					System.out.println(zone.getDays());
					// Plants growth
					if (zone.plantsExists()) {
						for (Plant plant : zone.getPlants()) {
							plant.increaseAge();
							plant.increaseAge();
							double growth = (double) plant.getAgeInDays() / (double) plant.getDaysTomaturity();
							double ngrowth = Math.round(growth * 100.0) / 100.0;
							plant.setGrowth(Math.round((ngrowth * 100.0) * 100.0) / 100.0);
						}
					}
				}

			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

		}

	}

}
