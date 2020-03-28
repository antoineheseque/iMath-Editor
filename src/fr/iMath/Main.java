package fr.iMath;

import fr.iMath.algorithms.InputAnalyzerAlgorithm;
import fr.iMath.objects.EquationObjectData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

/**
 * Main class. This is where everything begin!
 * @author HESEQUE Antoine
 * @author JEANNIN Louis
 */
public class Main extends Application{
	private static String function = "1x2x+x+9999+(1+yeee)2^2x*sin(2x)";

	/**
	 * Main function
	 * @param args arguments given right after executing the file.
	 */
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		InputAnalyzerAlgorithm f = new InputAnalyzerAlgorithm();
		List<EquationObjectData> list = f.analyse(function);
		launch(args);
	}

	/**
	 * Load the InputUI with JavaFX
	 */
	@Override
	public void start(Stage stage) throws Exception {
		/*try {
			Parent parent = FXMLLoader.load(getClass().getResource("/scenes/InputUI.fxml"));
			Scene scene = new Scene(parent);
			stage.setScene(scene);
			stage.setTitle("[v1.0.1] iMath : NodeEditor");
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
	}
}
