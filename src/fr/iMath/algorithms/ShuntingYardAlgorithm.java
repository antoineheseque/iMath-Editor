package fr.iMath.algorithms;

import java.util.List;
import java.util.Stack;

import fr.iMath.objects.Assoc;
import fr.iMath.objects.EquationObjectData;
import fr.iMath.objects.EquationObjectType;
import fr.iMath.objects.Operator;

/**
 * Application of the Shunting-Yard algorithm
 * @author HESEQUE Antoine
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">ShuntingYard algorithm</a> from Wikipedia
 */
public class ShuntingYardAlgorithm {

	/**
	 * Application of the Shunting-Yard algorithm
	 * @param list List of operators/functions which needs to be ordered
	 * @return ordered list of operators/functions
	 */
	public static Stack<EquationObjectData> convert(List<EquationObjectData> list) {
		Stack<EquationObjectData> output = new Stack<>();
		Stack<EquationObjectData> operators = new Stack<>();
		
		while(!list.isEmpty()) {
			EquationObjectData token = list.remove(0);
			if(token.getType() == EquationObjectType.NUMBER) {
				System.out.println("Push number " + token.getObject() + " to output.");
				output.add(token);
			}
			else if(token.getType() == EquationObjectType.VARIABLE) {
				System.out.println("Push variable " + token.getObject() + " to output.");
				output.add(token);
			}
			else if(token.getType() == EquationObjectType.FUNCTION) {
				System.out.println("Push function " + token.getObject() + " to operators.");
				operators.add(token);
			}
			else{
				Operator ope = (Operator) token.getObject();
				if(ope != Operator.LEFTPARENTHESIS && ope != Operator.RIGHTPARENTHESIS) {
					if(!operators.isEmpty())
						System.out.println("[TYPE] " + operators.peek().getType());
					while(!operators.isEmpty() && 
							(((operators.peek().getType() == EquationObjectType.FUNCTION)
							|| ((Operator)(operators.peek().getObject())).getPrecedence() > ope.getPrecedence())
							|| ((((Operator)(operators.peek().getObject())).getPrecedence() == ope.getPrecedence()) && ope.getAssociativity() == Assoc.LEFT_ASSOC)
							) && (!isLeftParenthesis(operators.peek()) || (operators.peek().getType() == EquationObjectType.FUNCTION)))
					{
						System.out.println("Push operator " + operators.peek().getObject() + " to output.");
						output.add(operators.pop());
					}
					System.out.println("Push token " + token.getObject() + " to operators.");
					operators.add(token);
				}
				else if(isLeftParenthesis(token)) {
					System.out.println("Push operator " + token.getObject() + " to operators.");
					operators.add(token);
				}
				else {
					System.out.println("Right parenthesis detected.");
					while(!isLeftParenthesis(operators.peek())) {
						System.out.println("Push operator " + operators.peek().getObject() + " to output.");
						output.add(operators.pop());
					}
					if(isLeftParenthesis(operators.peek())) {
						System.out.println("Discard left parenthesis.");
						operators.pop(); // Discard it.
					}
				}
			}
		}
		
		// When there is no more tokens
		while(!operators.isEmpty()) {
			output.add(operators.pop());
		}
		
		return output;
	}

	/**
	 * Return true if the EquationObjectData is a left parenthesis.
	 * @param d EquationObjectData
	 * @return true if the EquationObjectData is a left parenthesis.
	 */
	private static boolean isLeftParenthesis(EquationObjectData d) {
		return (d.getType() == EquationObjectType.OPERATOR) && (d.getObject() == Operator.LEFTPARENTHESIS);
	}
}
