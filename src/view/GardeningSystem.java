/**
 * 
 */
package view;

import java.io.IOException;

import gardenControl.GardenController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import view.controllers.SystemUIController;

/**
 * <h1>Which start application by java fx, create stage and then scene</h1>
 * <p>
 * Which start application by java fx, create stage and then scene
 * </p>
 * 
 * @author Shivanagesh Chandra Feb 22, 2016 GardeningSystem.java
 */

public class GardeningSystem extends Application {

	private Stage mainStatge;

	@Override
	public void start(Stage primaryStage) {
		this.mainStatge = primaryStage;
		this.mainStatge.setTitle("Gardening Management");

		initRootLayout();

	}

	/**
	 * Initializes the root layout.
	 */
	public void initRootLayout() {
		try {
			// Load root layout from fxml file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(GardeningSystem.class.getResource("System.fxml"));
			AnchorPane rootLayout = loader.load();
			SystemUIController sc = loader.getController();

			GardenController gc = new GardenController(sc);
			sc.setGardenController(gc);
			sc.addPlants();
			sc.loadControllerPanelValues();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			mainStatge.setScene(scene);
			mainStatge.show();
			mainStatge.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent t) {
					Platform.exit();
					System.exit(0);

				}
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the main stage.
	 * 
	 * @return Main stage
	 */
	public Stage getPrimaryStage() {
		return mainStatge;
	}

	public static void main(String[] args) {
		launch(args);
	}
}