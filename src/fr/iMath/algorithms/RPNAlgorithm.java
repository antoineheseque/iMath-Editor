package fr.iMath.algorithms;

import java.util.Stack;

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
	
	public static float evaluate(Stack<EquationObjectData> data, float xValue) {
		Stack<EquationObjectData> numbers = new Stack<EquationObjectData>();
		
		while(!data.isEmpty()) {
			EquationObjectData obj = data.remove(0);
			
			if(obj.getType() == EquationObjectType.NUMBER)
				numbers.add(obj);
			else if(obj.getType() == EquationObjectType.VARIABLE)
				numbers.add(new EquationObjectData(xValue));
			else if(obj.getType() == EquationObjectType.FUNCTION) {
				EquationObjectData a = numbers.pop();
				numbers.add(new EquationObjectData(getResult((Function)obj.getObject(), (Float)a.getObject())));
			}
			else {
				Float b = (Float) numbers.pop().getObject();
				Float a = (Float) numbers.pop().getObject();
				
				numbers.add(new EquationObjectData(getResult((Operator)obj.getObject(), a, b)));
			}
		}
		
		return (Float)numbers.pop().getObject();
	}
	
	private static float getResult(Operator operator, Float a, Float b) {
		switch(operator) {
		case PLUS:
			return Math.add(a, b);
		case MINUS:
			return Math.substract(a, b);
		case MULTIPLY:
			return Math.multiply(a, b);
		case DIVIDE:
			return Math.divide(a, b);
		default:
			return 0;
		}
	}
	
	private static float getResult(Function function, Float a) {
		switch(function) {
		case SIN:
			return Trigonometry.sin(a);
		case COS:
			return Trigonometry.cos(a);
		case TAN:
			return Trigonometry.tan(a);
		case SINC:
			return Trigonometry.sinc(a);
		case LN:
			return Math.ln(a);
		case EXP:
			return Math.exp(a);
		case SQRT:
			return Math.sqrt(a);
		case PI:
			return Trigonometry.pi();
		}
		return 0f;
	}
}
