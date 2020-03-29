package fr.iMath;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class. This is where everything begin!
 * @author HESEQUE Antoine
 * @author JEANNIN Louis
 */
public class Main extends Application{

	/**
	 * Main function
	 * @param args arguments given right after executing the file.
	 */
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		launch(args);
	}

	/**
	 * Load the InputUI with JavaFX
	 */
	@Override
	public void start(Stage stage) throws Exception {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource("resources/scenes/InputUI.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("[v1.0.1] iMath : NodeEditor");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
