package fr.iMath.objects;

import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

import fr.iMath.algorithms.InputAnalyzerAlgorithm;
import fr.iMath.algorithms.RPNAlgorithm;
import fr.iMath.algorithms.ShuntingYardAlgorithm;
import javafx.application.Platform;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

/**
 * Equation class
 */
public class Equation {
	/**
	 *  All the ordered equation data
	 **/
	private Stack<EquationObjectData> data;
	private String function = "";
	private String prefix = "f(x)=";

	/**
	 * Equation constructor
	 * @param function string function we need to convert
	 * @param prefix prefix of the function (f(x), g(x), ...)
	 */
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
	
	public String getName() {
		return prefix+function;
	}
}
