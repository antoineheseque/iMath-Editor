package fr.iMath;

import fr.iMath.objects.Equation;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class. This is where everything begin!
 * @author HESEQUE Antoine
 * @author JEANNIN Louis
 */
public class Main extends Application{
	private static String string = "-3(-x+2-8)5^-5(5*2)3";

	/**
	 * Main function
	 * @param args arguments given right after executing the file.
	 */
	public static void main(String[] args) {
		System.setProperty("file.encoding", "UTF-8");
		
		Equation function = new Equation(string);
		Float value = function.evaluate(10);
		
		// Test RPN Algorithm
		/*Stack<EquationObjectData> data = new Stack<EquationObjectData>();
		data.add(new EquationObjectData(5));
		data.add(new EquationObjectData("x"));
		data.add(new EquationObjectData(Operator.PLUS));
		Float value = RPNAlgorithm.evaluate(data, 10);*/
		
		System.out.println("[iMath] f(10) = " + value);
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
