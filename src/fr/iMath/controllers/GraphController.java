package fr.iMath.controllers;

import java.net.URL;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentLinkedQueue;

import fr.iMath.objects.Equation;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;

public class GraphController implements Initializable {	
	
	@FXML
	public ScatterChart<Float, Float> result;
	
	public void showGraph(Equation eq1, Equation eq2, float minX, float maxX, int nbrValues) {
		float step = (maxX-minX) / nbrValues;
		result.getData().clear();
		result.getXAxis().setLabel("f(x)");
		result.getYAxis().setLabel("g(x)");
		result.setTitle(eq1.getName() + " | " + eq2.getName());
		result.getData().add(new XYChart.Series<>());
		
		// Queue of data still waiting to be shown.
		Queue<Float[]> calculatedPoints = new ConcurrentLinkedQueue<>();
		
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() {
				int nbrToCalculate = Math.round((nbrValues / 1000)) + 1;
				for (int i = 0; i < nbrValues; i = i + nbrToCalculate) {
					// Stack some values together
					for (int j = 0; j < nbrToCalculate; j++) {
						float pos = minX + (i + j) * step;
						Float[] values = {0f, 0f};
						values[0] = eq1.evaluate(pos);
						values[1] = eq2.evaluate(pos);
						calculatedPoints.add(values);
					}

					// And show them
					try {
						Platform.runLater(() -> {
							while (!calculatedPoints.isEmpty()) {
								Float[] val = calculatedPoints.poll();
								result.getData().get(0).getData().add(new XYChart.Data<>(val[0], val[1]));
							}
						});
						Thread.sleep(5);
					} catch (Exception exc) {
						// should not be able to get here...
						throw new Error("Unexpected interruption");
					}
				}
				return null;
			}
		};
		new Thread(task).start();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//result.setCreateSymbols(false);
	}
	
	/**
	 * Get values for a graph between min and max with nbrValues values.
	 * @param min The Minimum value
	 * @param max The Maximum value
	 * @param nbrValues The total number of values
	 * @return an array containing all the values
	 */
	public void ShowGraph(Equation eq, float min, float max, int nbrValues) {
		//values.setName(prefix + function);
		float step = (max-min) / nbrValues;
		
		result.getData().clear();
		result.getXAxis().setLabel("x");
		result.getYAxis().setLabel("f(x)");
		result.setTitle(eq.getName());
		result.getData().add(new XYChart.Series<>());
		
		// Queue of data still waiting to be shown.
		Queue<Float[]> calculatedPoints = new ConcurrentLinkedQueue<>();
		
		Task<Void> task = new Task<>() {
			@Override
			protected Void call() {
				int nbrToCalculate = Math.round((nbrValues / 1000)) + 1;
				for (int i = 0; i < nbrValues; i = i + nbrToCalculate) {
					// Stack some values together
					for (int j = 0; j < nbrToCalculate; j++) {
						float pos = min + (i + j) * step;
						Float[] values = {pos, eq.evaluate(pos)};
						calculatedPoints.add(values);
					}

					// And show them
					try {
						Platform.runLater(() -> {
							while (!calculatedPoints.isEmpty()) {
								Float[] val = calculatedPoints.poll();
								result.getData().get(0).getData().add(new XYChart.Data<>(val[0], val[1]));
							}
						});
						Thread.sleep(5);
					} catch (Exception exc) {
						// should not be able to get here...
						throw new Error("Unexpected interruption");
					}
				}
				return null;
			}
		};
		new Thread(task).start();
	}
}
