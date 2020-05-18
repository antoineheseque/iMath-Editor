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

public class InputController implements Initializable {	

	@FXML
	private	TextField from, to, linspace, evaluateValue, equationArea;

	@FXML
	private Label result, error;

	private Equation equation = null;
	private String equationString = "";
	
	private Reflection reflection = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		reflection = new Reflection();
		reflection.setBottomOpacity(0.0);
		reflection.setTopOpacity(0.5);
		reflection.setTopOffset(1);
		reflection.setFraction(0.7);
		result.setEffect(reflection);
	}
	
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
			        
			        //XYChart.Series<Float, Float> values = equation.getGraph(min, max, nbrValues);			        
			        GraphController controller = loader.<GraphController>getController();
			        
			        controller.ShowGraph(equation, min, max, nbrValues);
			        Thread t = new Thread() 
					{
						public void run() {
							System.out.println("Mon traitement");
						}
					};
					t.start();
			        //controller.showGraph(values);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
		}
		else
			error.setText("Attention !!!\nVeuillez entrez une équation\n");
    }
	
	public void evaluate(ActionEvent event) {
		if(!equationArea.getText().isEmpty()) {
			checkEquation();
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
	
	public void help(ActionEvent event) {
		// Do stuff ...
    }

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

	private void checkEquation() {
		if(equationString != equationArea.getText()) {
			equationString = equationArea.getText();
			equation = new Equation(equationString, "f(x)=");
		}
	}
}
