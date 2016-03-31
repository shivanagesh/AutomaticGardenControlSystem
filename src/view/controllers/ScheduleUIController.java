/**
 * 
 */
package view.controllers;

import java.util.ArrayList;
import java.util.logging.Level;
import Base.Base;
import gardenControl.GardenController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Label;;

/**
 * <h1>Schedule UI</h1>
 * <p> This control Schedule window in UI, it have all kinds of interaction events and other functions related to schudule window</p>
 * @author Shivanagesh Chandra Feb 24, 2016 ScheduleUIController.java
 */
public class ScheduleUIController {

	/**
	 * GardenController it hold the object garden controller
	 */
	private GardenController gardenController;

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox sc_start_time;

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox sc_end_time;

	@FXML
	private TextField sc_min_temp;

	@FXML
	private TextField sc_max_temp;

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox sc_water_start;

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox sc_water_end;

	@SuppressWarnings("rawtypes")
	@FXML
	private ChoiceBox sc_water_pressure;

	@FXML
	private Label error_message;

	@FXML
	private Button save;
	
	private int ZoneId;

	/**
	 * Method is used to set  Garden Controller 
	 *@param gardenController Instance of Garden Controller 
	 */

	public void setGardenController(GardenController gardenController) {
		this.gardenController = gardenController;
	}

	@FXML
	void initialize() {
		
	}

	/**
	 * Loads initial values and set them previous schedule values 
	 */
	@SuppressWarnings("unchecked")
	void loadInitialValues() {
		sc_start_time.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
				16, 18, 19, 20, 21, 22, 23));
		
		sc_start_time.setValue((int)gardenController.getZone(ZoneId).getFirstSchedule().getScheduleStartTime());
		sc_start_time.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					setError_message("Changes not yet saved, Please click submit");
			}
		});
		
		sc_end_time.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16,
				18, 19, 20, 21, 22, 23));
		sc_end_time.setValue((int)gardenController.getZone(ZoneId).getFirstSchedule().getScheduleEndTime());
		
		sc_end_time.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					setError_message("Changes not yet saved, Please click submit");
			}
		});
		
		

		sc_water_start.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
				16, 18, 19, 20, 21, 22, 23));
		sc_water_start.setValue(gardenController.getZone(ZoneId).getFirstSchedule().getWaterTimings().get(0).get(0));

		sc_water_start.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					setError_message("Changes not yet saved, Please click submit");
			}
		});

		sc_water_end.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
				16, 18, 19, 20, 21, 22, 23));
		sc_water_end.setValue(gardenController.getZone(ZoneId).getFirstSchedule().getWaterTimings().get(0).get(1));
		sc_water_end.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					setError_message("Changes not yet saved, Please click submit");
			}
		});
		
		sc_water_pressure.setItems(FXCollections.observableArrayList("MEDIUM", "HIGH", "LOW"));
		sc_water_pressure.setValue(gardenController.getZone(ZoneId).getFirstSchedule().getWaterPressure().toString());
		sc_water_pressure.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number new_value) {
				if (value != new_value)
					setError_message("Changes not yet saved, Please click submit");
			}
		});
		sc_max_temp.setText(Integer.toString(gardenController.getZone(ZoneId).getFirstSchedule().getMaxTemperature()));
		sc_min_temp.setText(Integer.toString(gardenController.getZone(ZoneId).getFirstSchedule().getMinTemperature()));

	}

	/**
	 * @return the sc_start_time
	 */
	public int getSc_start_time() {
		return (int) sc_start_time.getValue();
	}

	/**
	 * @param sc_start_time
	 *            the sc_start_time to set
	 */
	@SuppressWarnings("unchecked")
	public void setSc_start_time(int sc_start_time) {
		this.sc_start_time.setValue(sc_start_time);
	}

	/**
	 * @return the sc_end_time
	 */
	public int getSc_end_time() {
		return (int) sc_end_time.getValue();
	}

	/**
	 * @param sc_end_time
	 *            the sc_end_time to set
	 */
	@SuppressWarnings("unchecked")
	public void setSc_end_time(int sc_end_time) {
		this.sc_end_time.setValue(sc_end_time);
	}

	/**
	 * @return the sc_min_temp
	 */
	public int getSc_min_temp() {
		return Integer.parseInt(sc_min_temp.getText());
	}

	/**
	 * @param sc_min_temp
	 *            the sc_min_temp to set
	 */
	public void setSc_min_temp(int sc_min_temp) {
		this.sc_min_temp.setText(sc_min_temp + "");
	}

	/**
	 * @return the sc_max_temp
	 */
	public int getSc_max_temp() {
		return Integer.parseInt(sc_max_temp.getText());
	}

	/**
	 * @param sc_max_temp
	 *            the sc_max_temp to set
	 */
	public void setSc_max_temp(TextField sc_max_temp) {
		this.sc_max_temp.setText(sc_max_temp + "");
	}

	/**
	 * @return the sc_water_start
	 */
	public int getSc_water_start() {
		return (int) sc_water_start.getValue();
	}

	/**
	 * @param sc_water_start
	 *            the sc_water_start to set
	 */
	@SuppressWarnings("unchecked")
	public void setSc_water_start(int sc_water_start) {
		this.sc_water_start.setValue(sc_water_start);
	}

	/**
	 * @return the sc_water_end
	 */
	public int getSc_water_end() {
		return (int) sc_water_end.getValue();
	}

	/**
	 * @param sc_water_end
	 *            the sc_water_end to set
	 */
	@SuppressWarnings("unchecked")
	public void setSc_water_end(int sc_water_end) {
		this.sc_water_end.setValue(sc_water_end);
	}

	/**
	 * @return the sc_water_pressure
	 */
	public String getSc_water_pressure() {
		return  (String) sc_water_pressure.getValue();
	}

	/**
	 * @param sc_water_pressure
	 *            the sc_water_pressure to set
	 */
	@SuppressWarnings("unchecked")
	public void setSc_water_pressure(int sc_water_pressure) {
		this.sc_water_pressure.setValue(sc_water_pressure);
	}

	/**
	 * Method get error message in System UI
	 * 
	 * @return the error message
	 */
	public String getError_message() {
		return error_message.getText();
	}

	/**
	 * Method Set error message in System UI
	 * 
	 * @param message
	 *            the error message to set
	 */
	public void setError_message(String message) {
		this.error_message.setText(message);
	}

	@FXML
	public void onChange() {
		this.setError_message("Changes not yet saved, Please click submit");
	}

	@FXML
	public void onSaveButtonClicked(ActionEvent event) {
		Stage stage = (Stage) save.getScene().getWindow();
		try {

			if (getSc_start_time() > getSc_end_time())
				setError_message("Start time can not be greater than end time");
			else if (getSc_min_temp() < 0)
				setError_message("Minimum Temperature can not be less zero");
			else if (getSc_max_temp() > 100)
				setError_message("Maxmimum Temperature can not be greater than  \n 100");
			else if (getSc_min_temp() >= getSc_max_temp())
				setError_message("Maxmimum Temperature can not be greater than \n Minimum temperature or same");
			else if (getSc_water_start() >= getSc_water_end())
				setError_message("Water start time can not be greater \n than water end timeor same");

			else{
			gardenController.getZone(ZoneId).getFirstSchedule().setMaxTemperature(getSc_max_temp());
			gardenController.getZone(ZoneId).getFirstSchedule().setMinTemperature(getSc_min_temp());
			gardenController.getZone(ZoneId).getFirstSchedule().setScheduleStartTime(getSc_start_time());
			gardenController.getZone(ZoneId).getFirstSchedule().setScheduleEndTime(getSc_end_time());
			gardenController.getZone(ZoneId).getFirstSchedule().setWaterPressure(getSc_water_pressure());
			ArrayList<Integer> newTimes = new ArrayList<Integer>();
			newTimes.add(getSc_water_start());
			newTimes.add(getSc_water_end());
			gardenController.getZone(ZoneId).getFirstSchedule().getWaterTimings().remove(0);
			gardenController.getZone(ZoneId).getFirstSchedule().getWaterTimings().add(newTimes);
			
			
			stage.close();}

		} catch (Exception ex) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText(
					"Schedule not saved some error is occured, Please fill the values properly then try again");

			alert.showAndWait();
			stage.close();
			ex.printStackTrace();
			Base.getLogger().log(Level.SEVERE, "System : Schedule not saved");
		}
	}

	/**
	 * @return the zoneId
	 */
	public int getZoneId() {
		return ZoneId;
	}

	/**
	 * @param zoneId the zoneId to s
	 */
	public void setZoneId(int zoneId) {
		ZoneId = zoneId;
	}

}
