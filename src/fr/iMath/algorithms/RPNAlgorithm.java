package fr.iMath.algorithms;

import java.util.Stack;

import fr.iMath.objects.EquationObjectData;
import fr.iMath.objects.EquationObjectType;
import fr.iMath.objects.Function;
import fr.iMath.objects.Operator;
import fr.iMath.mathematics.Math;

/**
 * Application of the Reverse Polish Algorithm
 * @author HESEQUE Antoine
 */
public class RPNAlgorithm {
	
	public static float evaluate(Stack<EquationObjectData> data, float xValue) {
		Stack<EquationObjectData> numbers = new Stack<EquationObjectData>();
		
		while(!data.isEmpty()) {
			EquationObjectData obj = data.remove(0);
			System.out.println(obj.getType());
			
			if(obj.getType() == EquationObjectType.NUMBER)
				numbers.add(obj);
			else if(obj.getType() == EquationObjectType.VARIABLE)
				numbers.add(new EquationObjectData(xValue));
			else if(obj.getType() == EquationObjectType.FUNCTION) {
				EquationObjectData a = numbers.pop();
				numbers.add(new EquationObjectData(getResult((Function)obj.getObject(), (Float)a.getObject())));
			}
			else {
				EquationObjectData a = numbers.pop();
				EquationObjectData b = numbers.pop();
				
				numbers.add(new EquationObjectData(getResult((Operator)obj.getObject(), (Float)a.getObject(), (Float)b.getObject())));
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
			System.out.println("Not yet implemented");
		case COS:
			System.out.println("Not yet implemented");
		case TAN:
			System.out.println("Not yet implemented");
		}
		return 0f;
	}
}
