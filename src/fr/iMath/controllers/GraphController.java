package fr.iMath.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;

public class GraphController implements Initializable {	
	
	@FXML
	private LineChart<Float, Float> result;
	
	@SuppressWarnings("unchecked")
	public void showGraph(Series<Float, Float> values) {
		result.getData().clear();
		result.getData().addAll(values);
		result.setTitle(values.getName());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		result.getXAxis().setLabel("x");
		result.getYAxis().setLabel("f(x)");
	}
}
