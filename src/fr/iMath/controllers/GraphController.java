package fr.iMath.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

public class GraphController implements Initializable {	
	
	@FXML
	private ScatterChart<Float, Float> result;
	
	@SuppressWarnings("unchecked")
	public void showGraph(Series<Float, Float> values) {
		result.getData().clear();
		result.getData().addAll(values);
		result.getXAxis().setLabel("x");
		result.getYAxis().setLabel("f(x)");
		result.setTitle(values.getName());
	}
	
	@SuppressWarnings("unchecked")
	public void showGraph(Series<Float, Float> valuesX, Series<Float, Float> valuesY) {
		result.getData().clear();
		Series<Float, Float> values = new Series<Float,Float>();
		for(XYChart.Data<Float,Float> val : valuesX.getData()) {
			values.getData().add(new Data<Float,Float>(val.getYValue(), valuesY.getData().get(valuesX.getData().indexOf(val)).getYValue()));
		}
		result.getData().addAll(values);

		result.getXAxis().setLabel("f(x)");
		result.getYAxis().setLabel("g(x)");
		result.setTitle(valuesX.getName() + " | " + valuesY.getName());
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//result.setCreateSymbols(false);
	}
}
