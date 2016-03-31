/**
 * 
 */
package view.controllers;

import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import Base.Base;
import gardenControl.GardenController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import plant.Plant;
import wateringSystem.WaterPressure;
import wateringSystem.WaterSystemStatus;

/**
 * <h1>System UI</h1>
 * <p>
 * This main UI controller for system, It contains functionality which manages
 * user interactions
 * <p>
 * 
 * @author Shivanagesh Chandra Feb 22, 2016 SystemUIController.java
 */
public class SystemUIController {

	@FXML
	private Label water_system_status;
	@FXML
	private Label zone_temperature;
	@FXML
	private Label fire_sensor_status;
	@FXML
	private Label rain_sensor_status;
	@FXML
	private Label soil_status;
	@FXML
	private Label schedule_status;

	@FXML
	private Button zone_watering;
	@FXML
	private Button zone_cooler;
	@FXML
	private Button zone_fire_sensor;
	@FXML
	private Button zone_rain_sensor;
	@FXML
	private Button zone_heater;
	@FXML
	private Button schedule;

	@FXML
	private Label days;

	@FXML
	private Label time;

	@FXML
	private Label pesticide;

	@FXML
	private GridPane plants_grid;

	@FXML
	private Button degrade_soil;

	/**
	 * Temperature, PH value and pressure selection
	 */
	@FXML
	private TextField min_temp;
	@FXML
	private TextField max_temp;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox min_ph;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox max_ph;
	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox water_pressure;

	@FXML
	private Label update_message;

	/**
	 * Status holding variable 
	 */
	private String waterSatus;
	/**
	 * Status holding variable 
	 */
	private String fireSatus;
	/**
	 * Status holding variable 
	 */
	private String rainSatus;
	/**
	 * Status holding variable 
	 */
	private String daysStatus;
	/**
	 * Status holding variable 
	 */
	private String timeStatus;
	/**
	 * Status holding variable 
	 */
	private String soilStatus;
	/**
	 * zone id holding variable 
	 */
	private int Zoneid;
	/**
	 * Status holding variable 
	 */
	private String temperatureStatus;
	/**
	 * Status holding variable 
	 */
	private String pestStatus;
	/**
	 * Status holding variable 
	 */
	private String scheduleStatus;

	private GardenController gardenController;

	/**
	 * System Randomness
	 */
	@FXML
	private RadioButton make_cold;

	@FXML
	private RadioButton make_hot;

	@FXML
	private CheckBox insert_pest;

	@FXML
	private CheckBox make_fire;

	@FXML
	private CheckBox make_rain;

	private Map<Integer, Label> plantGrowth = new HashMap<Integer, Label>();

	@FXML
	void initialize() {
		loadInitialsAndRunUpdate();
	}

	public void setGardenController(GardenController gardenController) {
		this.gardenController = gardenController;
		this.Zoneid = gardenController.currentZone;
	}

	public void addPlants() {
		List<Plant> plants = this.gardenController.getZone(Zoneid).getPlants();
		int row = 1;
		for (Plant plant : plants) {
			Label l = new Label(Double.toString(plant.getGrowth()));
			plantGrowth.put(plant.getId(), l);
			this.plants_grid.addRow(row, new Label(Integer.toString(plant.getId())), new Label(plant.getName()), l);
			row++;
		}

	}

	/**
	 * Method update days status in System UI
	 * 
	 * @param days Days of gardening system
	 */
	public void setDaysSatus(String days) {
		this.daysStatus = days;

	}

	/**
	 * Method update time status in System UI
	 * 
	 * @param time time of gardening system
	 */
	public void setTimeSatus(String time) {
		this.timeStatus = time;

	}

	/**
	 * Method update watering system status in System UI
	 * 
	 * @param watering_status Watering status of garden 
	 */
	public void setWateringSatus(String watering_status) {
		water_system_status.setText(watering_status);

	}

	@SuppressWarnings("unchecked")
	public void loadControllerPanelValues() {
		min_temp.setText(
				Integer.toString(this.gardenController.getZone(Zoneid).getTemperatuerController().getMinTemperatue()));
		max_temp.setText(
				Integer.toString(this.gardenController.getZone(Zoneid).getTemperatuerController().getMaxTemperature()));
		min_ph.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
		min_ph.setValue(this.gardenController.getZone(Zoneid).getFertilizerController().getMinPH());
		max_ph.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
		max_ph.setValue(this.gardenController.getZone(Zoneid).getFertilizerController().getMaxPH());
		water_pressure.setItems(FXCollections.observableArrayList("MEDIUM", "HIGH", "LOW"));
		water_pressure.setValue(gardenController.getZone(Zoneid).getFirstSchedule().getWaterPressure().toString());
		min_ph.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					update_message.setText("Changes not yet saved, Please click submit");
			}
		});
		max_ph.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					update_message.setText("Changes not yet saved, Please click submit");
			}
		});

		water_pressure.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					update_message.setText("Changes not yet saved, Please click submit");
			}
		});
		min_temp.textProperty().addListener(new ChangeListener<String>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String value, String new_value) {
				if (value != new_value)
					update_message.setText("Changes not yet saved, Please click submit");
			}
		});

		max_temp.textProperty().addListener(new ChangeListener<String>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, String value, String new_value) {
				if (value != new_value)
					update_message.setText("Changes not yet saved, Please click submit");
			}
		});
	}

	void loadInitialsAndRunUpdate() {
		Tooltip heater = new Tooltip();
		heater.setText(
				"Click button to change the status of heater Button text 'heater on' means heater currently in off state ");

		Tooltip cooler = new Tooltip();
		cooler.setText(
				"Click button to change the status of heater Button text 'cooler on' means cooler currently in off state ");

		Tooltip degradeSoilHelp = new Tooltip();
		degradeSoilHelp.setText("Click this button to drease soil ph by 1");
		zone_heater.setTooltip(heater);
		zone_cooler.setTooltip(cooler);
		degrade_soil.setTooltip(degradeSoilHelp);

		@SuppressWarnings("rawtypes")
		Task task = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				while (true) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							water_system_status.setText(waterSatus);
							if (gardenController.getZone(Zoneid).getTemperatuerController().getHeater().getStatus()) {
								zone_heater.setText("Heater off");
							} else {
								zone_heater.setText("Heater on");
							}
							if (gardenController.getZone(Zoneid).getTemperatuerController().getCooler().getStatus()) {
								zone_cooler.setText("Cooler off");
							} else {
								zone_cooler.setText("Cooler on");
							}
							if (waterSatus.equals("WATERING")) {
								zone_watering.setText("Watering off");
							} else {
								zone_watering.setText("Watering on");
							}
							days.setText(daysStatus);
							time.setText(timeStatus);
							soil_status.setText(soilStatus);
							rain_sensor_status.setText(rainSatus);
							fire_sensor_status.setText(fireSatus);
							zone_temperature.setText(temperatureStatus);
							pesticide.setText(pestStatus);
							schedule_status.setText(scheduleStatus);
							List<Plant> plants = gardenController.getZone(Zoneid).getPlants();
							for (Plant plant : plants) {
								// m.out.println(plant.getGrowth());
								plantGrowth.get(plant.getId()).setText(Double.toString(plant.getGrowth()));
							}

						}
					});
					Thread.sleep(500);
				}
			}
		};
		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	/**
	 * Method get watering system status in System UI
	 * 
	 * @return the water_system_status
	 */
	public String getWater_system_status() {
		return water_system_status.getText();
	}

	/**
	 * Method Get temperature in System UI
	 * 
	 * @return the zone_temperature
	 */
	public String getZone_temperature() {
		return zone_temperature.getText();
	}

	/**
	 * Method Set temperature in System UI
	 * 
	 * @param zone_temperature
	 *            the zone_temperature to set
	 */
	public void setZone_temperature(String zone_temperature) {
		this.zone_temperature.setText(zone_temperature);
	}

	/**
	 * Method Get Fire sensor status in System UI
	 * 
	 * @return the fire_sensor_status
	 */
	public String getFire_sensor_status() {
		return fire_sensor_status.getText();
	}

	/**
	 * Method Set Fire sensor status in System UI
	 * 
	 * @param fire_sensor_status
	 *            the fire_sensor_status to set
	 */
	public void setFire_sensor_status(String fire_sensor_status) {
		this.fire_sensor_status.setText(fire_sensor_status);
	}

	/**
	 * Method Get Rain sensor status in System UI
	 * 
	 * @return the rain_sensor_status
	 */
	public String getRain_sensor_status() {
		return rain_sensor_status.getText();
	}

	/**
	 * Method Set Rain sensor status in System UI
	 * 
	 * @param rain_sensor_status
	 *            the rain_sensor_status to set
	 */
	public void setRain_sensor_status(String rain_sensor_status) {
		this.rain_sensor_status.setText(rain_sensor_status);
	}

	/**
	 * Method Set Soil status in System UI
	 * 
	 * @return the soil_status
	 */
	public String getSoil_status() {
		return soil_status.getText();
	}

	/**
	 * Method Set Soil status in System UI
	 * 
	 * @param soil_status
	 *            the soil_status to set
	 */
	public void setSoil_status(String soil_status) {
		this.soil_status.setText(soil_status);
	}

	@FXML
	/**
	 * Method turn off/on Fire sensor
	 * 
	 * @param event
	 */
	void onZoneFireSensorButtonClicked(ActionEvent event) {

		if (zone_fire_sensor.getText().equals("Fire Senor off")) {
			zone_fire_sensor.setText("Fire Senor on");
			gardenController.getZone(Zoneid).getFireSensor().setActive(false);
			Base.getLogger().log(Level.INFO, "Interaction : User turned off Fire sensor");
			Base.getLogger().log(Level.INFO, "System : Fire sensor deatcivated");
		} else {
			zone_fire_sensor.setText("Fire Senor off");
			gardenController.getZone(Zoneid).getFireSensor().setActive(true);
			Base.getLogger().log(Level.INFO, "Interaction : User turned on Fire sensor");
			Base.getLogger().log(Level.INFO, "System : Fire sensor deatcivated");
		}
	}

	@FXML
	/**
	 * Method turn off/on Rain sensor
	 * 
	 * @param event
	 */
	void onZoneRainSensorButtonClicked(ActionEvent event) {

		if (zone_rain_sensor.getText().equals("Rain Senor off")) {
			zone_rain_sensor.setText("Rain Senor on");
			gardenController.getZone(Zoneid).getWaterController().getRainSensor().setActive(false);
			Base.getLogger().log(Level.INFO, "Interaction : User turned off rain sensor");
			Base.getLogger().log(Level.INFO, "System : Rain sensor deatcivated");

		} else {
			zone_rain_sensor.setText("Rain Senor off");
			gardenController.getZone(Zoneid).getWaterController().getRainSensor().setActive(true);
			Base.getLogger().log(Level.INFO, "Interaction : User turned on rain sensor");
			Base.getLogger().log(Level.INFO, "System : Rain sensor atcivated");

		}
	}

	@FXML
	/**
	 * Method turn off/on Heater sensor
	 * 
	 * @param event
	 */
	void onZoneHeaterButtonClicked(ActionEvent event) {

		if (zone_heater.getText().equals("Heater on")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Worning Dialog");
			alert.setHeaderText(null);
			alert.setContentText(
					"Please remember your overriding temperature control system \n, your need to turn off the device to get temeprature controler live");
			alert.getDialogPane().setPrefSize(480, 200);
			alert.showAndWait();
			zone_heater.setText("Heater off");
			zone_cooler.setText("Cooler on");
			gardenController.getZone(Zoneid).getTemperatuerController().setUserOveride(true);
			gardenController.getZone(Zoneid).getTemperatuerController().getCooler().turnOff();
			gardenController.getZone(Zoneid).getTemperatuerController().getHeater().turnOn();
			Base.getLogger().log(Level.INFO, "Interaction :  Manual override - User turned off Heater ");
		} else {
			zone_heater.setText("Heater on");
			gardenController.getZone(Zoneid).getTemperatuerController().getHeater().turnOff();
			gardenController.getZone(Zoneid).getTemperatuerController().setUserOveride(false);
			Base.getLogger().log(Level.INFO, "Interaction :  Manual override - User turned on Heater ");
		}
	}

	@FXML
	/**
	 * Method turn off/on Cooler sensor
	 * 
	 * @param event
	 */
	void onZoneCoolerButtonClicked(ActionEvent event) {

		if (zone_cooler.getText().equals("Cooler on")) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Worning Dialog");
			alert.setHeaderText(null);
			alert.setContentText(
					"Please remember your overriding temperature control system, your need to turn off the device to get temeprature controler live");
			alert.getDialogPane().setPrefSize(480, 200);
			alert.showAndWait();
			zone_cooler.setText("Cooler off");
			zone_heater.setText("Heater on");
			gardenController.getZone(Zoneid).getTemperatuerController().getHeater().turnOff();
			gardenController.getZone(Zoneid).getTemperatuerController().setUserOveride(true);
			gardenController.getZone(Zoneid).getTemperatuerController().getCooler().turnOn();
			Base.getLogger().log(Level.INFO, "Interaction : Manual override - User turned off Cooler ");
		} else {
			zone_cooler.setText("Cooler on");
			gardenController.getZone(Zoneid).getTemperatuerController().getCooler().turnOff();
			gardenController.getZone(Zoneid).getTemperatuerController().setUserOveride(false);
			Base.getLogger().log(Level.INFO, "Interaction : Manual override - User turned off Cooler ");
		}
	}

	@FXML
	/**
	 * 
	 * @param event
	 */
	void onMakeFireCheckBoxClicked(ActionEvent event) {
		if (make_fire.getText().equals("Fire")) {
			make_fire.setText("Fired");
			gardenController.getZone(Zoneid).getFireSensor().setStatus(true);
			;
			Base.getLogger().log(Level.INFO, "Randomnes : Fire occured");
		} else {
			make_fire.setText("Fire");
			gardenController.getZone(Zoneid).getFireSensor().cancelAlram();
		}
	}

	@FXML
	/**
	 * 
	 * @param event
	 */
	void onMakeRainCheckBoxClicked(ActionEvent event) {
		if (make_rain.getText().equals("Rain")) {
			make_rain.setText("Raining");
			gardenController.getZone(Zoneid).getWaterController().getRainSensor().setStatus(true);
			Base.getLogger().log(Level.INFO, "Randomnes : Raining");
		} else {
			make_rain.setText("Rain");
			gardenController.getZone(Zoneid).getWaterController().getRainSensor().setStatus(false);
		}
	}

	@FXML
	/**
	 * 
	 * @param event
	 */
	void onMakeColdRadioButtonClicked(ActionEvent event) {
		if (make_cold.getText().equals("Make it cold")) {
			make_cold.setText("cold");
			make_hot.setSelected(false);
			make_hot.setText("Make it hot");
			this.gardenController.getZone(Zoneid).getTemperatuerController().getTemperatureSensor()
					.setSensorTemperature(-20);
			Base.getLogger().log(Level.INFO, "Randomnes : Very cold");
		} else {
			make_cold.setText("Make it cold");
		}
	}

	@FXML
	/**
	 * 
	 * @param event
	 */
	void onMakeHotRadioButtonClicked(ActionEvent event) {
		if (make_hot.getText().equals("Make it hot")) {
			make_hot.setText("hot");
			make_cold.setSelected(false);
			make_cold.setText("Make it cold");
			this.gardenController.getZone(Zoneid).getTemperatuerController().getTemperatureSensor()
					.setSensorTemperature(120);
			Base.getLogger().log(Level.INFO, "Randomnes : Very Hot");
		} else {
			make_hot.setText("Make it hot");
		}
	}

	@FXML
	/**
	 * 
	 * @param event
	 */
	void onWaterButtonClicked(ActionEvent event) {
		if (zone_watering.getText().equals("Watering on")) {
			if (gardenController.getZone(Zoneid).getWaterController().getWaterSystem()
					.getSystemStatus() != WaterSystemStatus.RAINING) {
				zone_watering.setText("Watering off");
				gardenController.getZone(Zoneid).getWaterController().getWaterSystem().turnOn();
				Base.getLogger().log(Level.INFO, "Interaction : Manual override - User turned on watering  ");
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information Dialog");
				alert.setHeaderText(null);
				alert.setContentText("Water System is already on or Ranining");

				alert.showAndWait();
			}

			Base.getLogger().log(Level.INFO, "Randomnes : Very Hot");
		} else {
			zone_watering.setText("Watering on");
			gardenController.getZone(Zoneid).getWaterController().getWaterSystem().trunOff();
			Base.getLogger().log(Level.INFO, "Interaction : Manual override - User turned off watering  ");
		}
	}

	@FXML
	/**
	 * 
	 * @param event
	 */
	void onInsertPestCheckBoxClicked(ActionEvent event) {
		if (insert_pest.getText().equals("Insert Pest")) {
			gardenController.getZone(Zoneid).getPestSensor().setStatus(true);
			insert_pest.setText("Remove pest");
		} else {
			insert_pest.setText("Insert Pest");
			gardenController.getZone(Zoneid).getPestSensor().setStatus(false);
		}
	}

	@FXML
	/**
	 * 
	 * @param event
	 */
	void onScheduleButtonClick(ActionEvent event) {
		System.out.println("Called");
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(SystemUIController.class.getResource("../Schedule.fxml"));
			AnchorPane rootLayout = loader.load();
			ScheduleUIController sui = loader.getController();
			sui.setGardenController(gardenController);
			sui.setZoneId(Zoneid);
			sui.loadInitialValues();
			Scene scene = new Scene(rootLayout);
			stage.setScene(scene);
			stage.setTitle("Schedule Update");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the fireSatus
	 */
	public String getFireSatus() {
		return fireSatus;
	}

	/**
	 * @param fireSatus
	 *            the fireSatus to set
	 */
	public void setFireSatus(String fireSatus) {
		this.fireSatus = fireSatus;
	}

	/**
	 * @return the rainSatus
	 */
	public String getRainSatus() {
		return rainSatus;
	}

	/**
	 * @param rainSatus
	 *            the rainSatus to set
	 */
	public void setRainSatus(String rainSatus) {
		this.rainSatus = rainSatus;
	}

	/**
	 * @return the waterSatus
	 */
	public String getWaterSatus() {
		return waterSatus;
	}

	/**
	 * @param waterSatus
	 *            the waterSatus to set
	 */
	public void setWaterSatus(String waterSatus) {
		this.waterSatus = waterSatus;
	}

	/**
	 * @return the daysStatus
	 */
	public String getDaysStatus() {
		return daysStatus;
	}

	/**
	 * @param daysStatus
	 *            the daysStatus to set
	 */
	public void setDaysStatus(String daysStatus) {
		this.daysStatus = daysStatus;
	}

	/**
	 * @return the timeStatus
	 */
	public String getTimeStatus() {
		return timeStatus;
	}

	/**
	 * @param timeStatus
	 *            the timeStatus to set
	 */
	public void setTimeStatus(String timeStatus) {
		this.timeStatus = timeStatus;
	}

	/**
	 * @param soil_status
	 *            the soil_status to set
	 */
	public void setSoil_status(Label soil_status) {
		this.soil_status = soil_status;
	}

	/**
	 * @return the soilStatus
	 */
	public String getSoilStatus() {
		return soilStatus;
	}

	/**
	 * @param soilStatus
	 *            the soilStatus to set
	 */
	public void setSoilStatus(String soilStatus) {
		this.soilStatus = soilStatus;
	}

	/**
	 * @return the zoneid
	 */
	public int getZoneid() {
		return Zoneid;
	}

	/**
	 * @param zoneid
	 *            the zoneid to set
	 */
	public void setZoneid(int zoneid) {
		Zoneid = zoneid;
	}

	/**
	 * @return the temperatureStatus
	 */
	public String getTemperatureStatus() {
		return temperatureStatus;
	}

	/**
	 * @param temperatureStatus
	 *            the temperatureStatus to set
	 */
	public void setTemperatureStatus(String temperatureStatus) {
		this.temperatureStatus = temperatureStatus;
	}

	public void onDegradeSoilClicked() {
		int ph = this.gardenController.getZone(Zoneid).getFertilizerController().getSoilSensor().getSensorPH();
		ph = ph - 1;
		this.gardenController.getZone(Zoneid).getFertilizerController().getSoilSensor().setSensorPH(ph);
		Base.getLogger().log(Level.INFO, "Randomnes : Soil degrading");

	}

	/**
	 * @return the pestStatus
	 */
	public String getPestStatus() {
		return pestStatus;
	}

	/**
	 * @param pestStatus
	 *            the pestStatus to set
	 */
	public void setPestStatus(String pestStatus) {
		this.pestStatus = pestStatus;
	}

	@FXML
	/**
	 * Update the information
	 */
	public void onUpdateButtonClick(ActionEvent event) {
		if (getMin_temp() >= getMax_temp())
			setUpdate_message("Minimum Temperature can not be less zero same");
		else if (getMin_temp() < 0)
			setUpdate_message("Minimum Temperature can not be less zero");
		else if (getMax_temp() > 100)
			setUpdate_message("Maxmimum Temperature can not be greater than  \n 100");
		else if (getMin_ph() >= getMax_ph())
			setUpdate_message("Maxmimum PH can not be greater than \n Minimum PH or same");
		else {
			setUpdate_message("");
			gardenController.getZone(Zoneid).getTemperatuerController().setMinTemperatue(getMin_temp());
			gardenController.getZone(Zoneid).getTemperatuerController().setMaxTemperature(getMax_temp());
			gardenController.getZone(Zoneid).getFertilizerController().setMinPH(getMin_ph());
			gardenController.getZone(Zoneid).getFertilizerController().setMaxPH(getMax_ph());
			gardenController.getZone(Zoneid).getWaterController().getWaterSystem()
					.setWaterPressure(WaterPressure.valueOf(getWater_pressure()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Changes saved");

			alert.showAndWait();
		}
	}

	/**
	 * @return the min_temp
	 */
	public int getMin_temp() {
		return Integer.parseInt(min_temp.getText());
	}

	/**
	 * @param min_temp
	 *            the min_temp to set
	 */
	public void setMin_temp(String min_temp) {
		this.min_temp.setText(min_temp);
		;
	}

	/**
	 * @return the max_temp
	 */
	public int getMax_temp() {
		return Integer.parseInt(max_temp.getText());
	}

	/**
	 * @param max_temp
	 *            the max_temp to set
	 */
	public void setMax_temp(String max_temp) {
		this.max_temp.setText(max_temp);
	}

	/**
	 * @return the min_ph
	 */
	public int getMin_ph() {
		return (int) min_ph.getValue();
	}

	/**
	 * @param min_ph
	 *            the min_ph to set
	 */
	@SuppressWarnings("unchecked")
	public void setMin_ph(int min_ph) {
		this.min_ph.setValue(min_ph);
	}

	/**
	 * @return the max_ph
	 */
	public int getMax_ph() {
		return (int) max_ph.getValue();
	}

	/**
	 * @param max_ph
	 *            the max_ph to set
	 */
	@SuppressWarnings("unchecked")
	public void setMax_ph(int max_ph) {
		this.max_ph.setValue(max_ph);
	}

	/**
	 * @return the water_pressure
	 */

	public String getWater_pressure() {
		return water_pressure.getValue().toString();
	}

	/**
	 * @param water_pressure
	 *            the water_pressure to set
	 */
	@SuppressWarnings("unchecked")
	public void setWater_pressure(String water_pressure) {
		this.water_pressure.setValue(water_pressure);
		;
	}

	/**
	 * @return the update_message
	 */
	public Label getUpdate_message() {
		return update_message;
	}

	/**
	 * Method set the update message
	 * 
	 * @param message
	 *            the update_message to set
	 */
	public void setUpdate_message(String message) {
		this.update_message.setText(message);
		;
	}

	/**
	 * @return the scheduleStatusm
	 */
	public String getScheduleStatus() {
		return scheduleStatus;
	}

	/**
	 * @param scheduleStatus
	 *            the scheduleStatus to set
	 */
	public void setScheduleStatus(String scheduleStatus) {
		this.scheduleStatus = scheduleStatus;
	}

}
