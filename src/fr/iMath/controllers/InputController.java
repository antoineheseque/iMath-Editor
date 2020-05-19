package fr.iMath.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.stage.Stage;
import fr.iMath.objects.*;

/**
 * JavaFX: Show the single equation UI menu
 */
public class InputController implements Initializable {	

	@FXML
	private	TextField from, to, linspace, evaluateValue, equationArea;

	@FXML
	private Label result, error;

	private Equation equation = null;
	private String equationString = "";
	
	private Reflection reflection = null;

	/**
	 * Initialise with the default values
	 * @param location URL
	 * @param resources ResourceBundle
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reflection = new Reflection();
		reflection.setBottomOpacity(0.0);
		reflection.setTopOpacity(0.5);
		reflection.setTopOffset(1);
		reflection.setFraction(0.7);
		result.setEffect(reflection);
	}

	/**
	 * Show a graph when the user click on the "Show Graph" button
	 * @param event Click event
	 */
	public void showGraph(ActionEvent event) {
		if(!equationArea.getText().isEmpty()) {
			checkEquation();
			
			try {
				float min = Float.parseFloat(from.getText());
				float max = Float.parseFloat(to.getText());
				int nbrValues = Integer.parseInt(linspace.getText());
				
				try{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/scenes/GraphUI.fxml"));
					Parent parent = loader.load();
					
					Scene scene = new Scene(parent);
					Stage newWindow = new Stage();
					
					newWindow.setTitle("Graph");
			        newWindow.setScene(scene);
			        
			        newWindow.show();

			        GraphController controller = loader.getController();
			        
			        controller.showGraph(equation, min, max, nbrValues);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		else
			error.setText("Attention !!!\nVeuillez entrer une équation\n");
    }

	/**
	 * Evaluate the function
	 * @param event Click event
	 */
	public void evaluate(ActionEvent event) {
		if(!equationArea.getText().isEmpty()) {
			checkEquation();
			if(equationArea.getText().equals("1+1")){ //Easter egg
				try{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/scenes/WebUI.fxml"));
					Parent parent = loader.load();
					Scene scene = new Scene(parent);
					Stage newWindow = new Stage();

					newWindow.setTitle("1+1");
					newWindow.setScene(scene);

					newWindow.show();

					WebController controller = loader.getController();
					controller.showWeb("https://www.youtube.com/watch?v=KaasJ44O5lI");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				float value = Float.parseFloat(evaluateValue.getText());
				
				float eval = equation.evaluate(value);
				result.setText("f(" + evaluateValue.getText() +") = " + eval);
				System.out.println(eval);
			}
			catch(Exception e){
				// Maybe the equation was without an x ?
				float eval = equation.evaluate(0);
				result.setText("x = " + eval);
			}	
		}
	}

	/**
	 * Open the help website
	 * @param event Click event
	 */
	public void help(ActionEvent event) {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/scenes/WebUI.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Stage newWindow = new Stage();

			newWindow.setTitle("Aide");
			newWindow.setScene(scene);

			newWindow.show();

			WebController controller = loader.getController();
			controller.showWeb("http://imath.antoineh.tech/"); //https://github.com/antoineheseque/iMath-NodeEditor/blob/master/README.md
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /**
	 * Open the parametric equation menu when the associated button is pressed.
	 * @param event Click event
	 */
	public void parametricSwitch(ActionEvent event) {
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/scenes/ParametricInputUI.fxml"));
			Parent parent = loader.load();
			
			Scene scene = new Scene(parent);
			Stage stage = (Stage) equationArea.getScene().getWindow();
	        stage.setScene(scene);
	        //newWindow.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save and create the equation when needed.
	 */
	private void checkEquation() {
		if(!equationString.equals(equationArea.getText())) {
			equationString = equationArea.getText();
			equation = new Equation(equationString, "f(x)=");
		}
	}
}
