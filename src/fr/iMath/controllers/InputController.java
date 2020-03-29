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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.stage.Stage;
import fr.iMath.objects.*;

public class InputController implements Initializable {	

	@FXML
	private	TextField from, to, linspace, evaluateValue;
	
	@FXML 
	private TextArea equationArea;

	@FXML
	private Label result;

	private Equation equation = null;
	private String equationString = "";

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Do stuff ...
	}
	
	public void showGraph(ActionEvent event) {
		if(equationArea.getText() != "") {
			checkEquation();
			
			try {
				float min = Float.parseFloat(from.getText());
				float max = Float.parseFloat(to.getText());
				int nbrValues = Integer.parseInt(linspace.getText());
				
				XYChart.Series<Float, Float> values = equation.getGraph(min, max, nbrValues);
				
				try{
					FXMLLoader loader = new FXMLLoader(getClass().getResource("../resources/scenes/GraphUI.fxml"));
					Parent parent = loader.load();
					
					Scene scene = new Scene(parent);
					Stage newWindow = new Stage();
					
					newWindow.setTitle("Graph");
			        newWindow.setScene(scene);
			        
			        newWindow.show();
			        
			        GraphController controller = loader.<GraphController>getController();
			        controller.showGraph(values);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}			
		}
    }
	
	public void evaluate(ActionEvent event) {
		if(equationArea.getText() != "") {
			// V�rifier les types from et to
			checkEquation();

			try {
				float value = Float.parseFloat(evaluateValue.getText());
				Reflection reflection = new Reflection();
				reflection.setBottomOpacity(0.0);
				reflection.setTopOpacity(0.5);
				reflection.setTopOffset(1);
				reflection.setFraction(0.7);
				result.setEffect(reflection);
				result.setText("f(" + evaluateValue.getText() +") = "+ String.valueOf(equation.evaluate(value)));
				System.out.println(equation.evaluate(value));
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}	
		}
	}
	
	public void help(ActionEvent event) {
		// Do stuff ...
    }
	
	private void checkEquation() {
		if(equationString != equationArea.getText()) {
			equationString = equationArea.getText();
			equation = new Equation(equationString);
		}
	}
}
