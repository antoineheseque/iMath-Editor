package fr.iMath.objects;

import java.util.List;
import java.util.Stack;

import fr.iMath.algorithms.InputAnalyzerAlgorithm;
import fr.iMath.algorithms.RPNAlgorithm;
import fr.iMath.algorithms.ShuntingYardAlgorithm;

public class Function {
	
	private Stack<EquationObjectData> data;
	
	public Function(String function) {
		
		System.out.println("[iMath] Creating the function f(x) = " + function);
		
		// Analyse the input
		InputAnalyzerAlgorithm f = new InputAnalyzerAlgorithm();
		List<EquationObjectData> list = f.analyse(function);
		
		// Use the Shunting-Yard Algorithm
		data = ShuntingYardAlgorithm.convert(list);
	}
	
	/**
	 * Evaluate the function with a specific x using the RPN Algorithm.
	 * @param xValue the x value
	 * @return a float
	 */
	public float evaluate(float xValue) {
		// Use the RPN Algorithm
		return RPNAlgorithm.evaluate(data, xValue);
	}
	
	/**
	 * Get values for a graph between min and max with nbrValues values.
	 * @param min The Minimum value
	 * @param max The Maximum value
	 * @param nbrValues The total nummber of values
	 * @return an array containing all the values
	 */
	public float[] getGraph(float min, float max, int nbrValues) {
		float[] values = new float[nbrValues];
		
		float step = (max-min) / nbrValues;
		
		for(int i = 0; i < nbrValues; i++) {
			values[i] = evaluate(i*step);
		}
		
		return values;
	}
}
