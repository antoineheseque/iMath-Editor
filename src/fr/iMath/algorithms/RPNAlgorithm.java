package fr.iMath.algorithms;

import java.util.HashMap;
import java.util.Stack;
import java.util.function.BiFunction;

import fr.iMath.objects.EquationObjectData;
import fr.iMath.objects.EquationObjectType;
import fr.iMath.objects.Function;
import fr.iMath.objects.Operator;
import fr.iMath.mathematics.Math;
import fr.iMath.mathematics.Trigonometry;

/**
 * Application of the Reverse Polish Algorithm
 * @author HESEQUE Antoine
 */
public class RPNAlgorithm {
	
	private static HashMap<Operator, BiFunction<Float, Float, Float>> operators;
	private static HashMap<Function, java.util.function.Function<Float, Float>> functions;
	static {
		operators = new HashMap<Operator, BiFunction<Float, Float, Float>>();
		functions = new HashMap<Function, java.util.function.Function<Float, Float>>();
		
		operators.put(Operator.PLUS, (a,b) -> Math.add(a, b));
		operators.put(Operator.MINUS, (a,b) -> Math.substract(a, b));
		operators.put(Operator.MULTIPLY, (a,b) -> Math.multiply(a, b));
		operators.put(Operator.DIVIDE, (a,b) -> Math.divide(a, b));
		operators.put(Operator.POWER, (a,b) -> Math.power(a, b));
		
		functions.put(Function.COS, (a) -> Trigonometry.cos(a));
		functions.put(Function.SIN, (a) -> Trigonometry.sin(a));
		functions.put(Function.TAN, (a) -> Trigonometry.tan(a));
		functions.put(Function.LN, (a) -> Math.ln(a));
		functions.put(Function.SQRT, (a) -> Math.sqrt(a));
		functions.put(Function.EXP, (a) -> Math.exp(a));
		functions.put(Function.SINC, (a) -> Trigonometry.sinc(a));
	}
	
	public static float evaluate(Stack<EquationObjectData> data, float xValue) {
		Stack<EquationObjectData> numbers = new Stack<EquationObjectData>();
		
		while(!data.isEmpty()) {
			EquationObjectData obj = data.remove(0);
			
			if(obj.getType() == EquationObjectType.NUMBER)
				numbers.add(obj);
			else if(obj.getType() == EquationObjectType.VARIABLE)
				numbers.add(new EquationObjectData(xValue));
			else if(obj.getType() == EquationObjectType.FUNCTION) {
				float a = (float) numbers.pop().getObject();
				
				numbers.add(new EquationObjectData(functions.get(obj.getObject()).apply(a)));
				//numbers.add(new EquationObjectData(getResult((Function)obj.getObject(), a)));
			}
			else {
				float b = (float) numbers.pop().getObject();
				float a = (float) numbers.pop().getObject();
				
				numbers.add(new EquationObjectData(operators.get(obj.getObject()).apply(a,b)));
				//numbers.add(new EquationObjectData(getResult((Operator)obj.getObject(), a, b)));
			}
		}
		
		return (float)numbers.pop().getObject();
	}
}
