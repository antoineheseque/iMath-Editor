package fr.iMath.objects;

import java.util.List;
import java.util.Stack;

import fr.iMath.algorithms.InputAnalyzerAlgorithm;
import fr.iMath.algorithms.RPNAlgorithm;
import fr.iMath.algorithms.ShuntingYardAlgorithm;
import javafx.scene.chart.XYChart;

public class Equation {
	
	private Stack<EquationObjectData> data;
	private String function = "";
	private String prefix = "f(x)=";
	
	public Equation(String function, String prefix) {
		this.function = function;
		this.prefix = prefix;
		System.out.println("[iMath] Creating the function f(x) = " + function);
		
		// Analyse the input
		InputAnalyzerAlgorithm f = new InputAnalyzerAlgorithm();
		List<EquationObjectData> list = f.analyse(function);
		
		// Use the Shunting-Yard Algorithm
		data = ShuntingYardAlgorithm.convert(list);
		
		System.out.println("------[ Shunting-Yard Algorithm ]--------");
		for(EquationObjectData d : data){
			System.out.println(d.getObject());
		}
		System.out.println("--------------");
	}
	
	/**
	 * Evaluate the function with a specific x using the RPN Algorithm.
	 * @param xValue the x value
	 * @return a float
	 */
	public float evaluate(float xValue) {
		// Use the RPN Algorithm
		@SuppressWarnings("unchecked")
		Stack<EquationObjectData> stack = (Stack<EquationObjectData>)data.clone();
		float value = RPNAlgorithm.evaluate(stack, xValue);
		stack = null;
		return value;
	}
	
	/**
	 * Get values for a graph between min and max with nbrValues values.
	 * @param min The Minimum value
	 * @param max The Maximum value
	 * @param nbrValues The total number of values
	 * @return an array containing all the values
	 */
	public XYChart.Series<Float, Float> getGraph(float min, float max, int nbrValues) {
		XYChart.Series<Float, Float> values = new XYChart.Series<>();
		values.setName(prefix + function);
		float step = (max-min) / nbrValues;
		
		for(int i = 0; i < nbrValues; i++) {
			float pos = min+i*step;
			values.getData().add(new XYChart.Data<>(pos,evaluate(pos)));
		}
		
		return values;
	}
}
