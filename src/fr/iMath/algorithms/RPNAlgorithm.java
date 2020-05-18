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

	/**
	 * Association between operators and their associated functions.
	 */
	private static final HashMap<Operator, BiFunction<Float, Float, Float>> operators;

	/**
	 * Association between functions and their associated functions.
	 */
	private static final HashMap<Function, java.util.function.Function<Float, Float>> functions;
	static {
		operators = new HashMap<>();
		functions = new HashMap<>();
		
		operators.put(Operator.PLUS, Math::add);
		operators.put(Operator.MINUS, Math::substract);
		operators.put(Operator.MULTIPLY, Math::multiply);
		operators.put(Operator.DIVIDE, Math::divide);
		operators.put(Operator.POWER, Math::power);
		
		functions.put(Function.COS, Trigonometry::cos);
		functions.put(Function.SIN, Trigonometry::sin);
		functions.put(Function.TAN, Trigonometry::tan);
		functions.put(Function.LN, Math::ln);
		functions.put(Function.SQRT, Math::sqrt);
		functions.put(Function.EXP, Math::exp);
		functions.put(Function.SINC, Trigonometry::sinc);
	}

	/**
	 * Evaluate the function with the RPN Algorithm
	 * @param data stack of equations in reverse mode
	 * @param xValue value of x
	 * @return The value of f(x) (float)
	 */
	public static float evaluate(Stack<EquationObjectData> data, float xValue) {
		Stack<EquationObjectData> numbers = new Stack<>();

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
