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

public class ParametricInputController implements Initializable {

	@FXML
	private	TextField from, to, linspace, evaluateValue, equationArea, equationArea2;

	@FXML
	private Label result, error;
	
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

	public void evaluate(ActionEvent event) {

	}
	
	public void help(ActionEvent event) {
		// Do stuff ...
    }

	public void showGraph(ActionEvent event) {
		// Do stuff ...
	}

	public void affineSwitch(ActionEvent event) {
		// Do stuff ...
	}
}
