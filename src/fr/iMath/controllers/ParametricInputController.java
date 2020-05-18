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
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.stage.Stage;
import fr.iMath.objects.*;

/**
 * JavaFX: Show the parametric UI menu
 */
public class ParametricInputController implements Initializable {

	@FXML
	private	TextField from, to, linspace, evaluateValue, equationArea, equationArea2;

	@FXML
	private Label result, error;
	
	private Reflection reflection = null;
	
	private Equation equationX = null;
	private Equation equationY = null;
	private String equationStringX = "";
	private String equationStringY = "";

	/**
	 * Initialise the menu with default values
	 * @param location URl
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
		result.setText("f(x)= | g(x)= ");
	}

	/**
	 * Calculate a single value when the button is pressed
	 * @param event Click event
	 */
	public void evaluate(ActionEvent event) {
		if(equationArea.getText() != "") {
			checkEquation();
			try {
				float value = Float.parseFloat(evaluateValue.getText());
				float eval1 = equationX.evaluate(value);
				float eval2 = equationY.evaluate(value);
				result.setText("f(" + evaluateValue.getText() +") = " + eval1 + " | g(" + evaluateValue.getText() + ") = " + eval2);
				System.out.println(eval1 + " | " + eval2);
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}	
		}
	}

	/**
	 * Show the help website
	 * @param event ClickEvent
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
	 * Show a graph when the user click on the "Show Graph" button
	 * @param event Click event
	 */
	public void showGraph(ActionEvent event) {
		if(!equationArea.getText().isEmpty() && !equationArea2.getText().isEmpty()) {
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
			        controller.showGraph(equationX, equationY, min, max, nbrValues);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}			
		}
	}

	/**
	 * Open the single equation menu when the associated button is pressed.
	 * @param event Click event
	 */
	public void affineSwitch(ActionEvent event) {		
		try{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/scenes/InputUI.fxml"));
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
		if(equationArea.getText() != equationStringX || equationArea.getText() != equationStringY) {
			equationStringX = equationArea.getText();
			equationStringY = equationArea2.getText();
			equationX = new Equation(equationStringX, "f(x)=");
			equationY = new Equation(equationStringY, "g(x)=");
		}
	}
}
