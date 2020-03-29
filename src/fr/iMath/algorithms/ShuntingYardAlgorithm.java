package fr.iMath.algorithms;

import java.util.List;
import java.util.Stack;

import fr.iMath.objects.Assoc;
import fr.iMath.objects.EquationObjectData;
import fr.iMath.objects.EquationObjectType;
import fr.iMath.objects.Function;
import fr.iMath.objects.Operator;

/**
 * Application of the Shunting-Yard algorithm
 * @author HESEQUE Antoine
 * 
 * @see <a href="https://en.wikipedia.org/wiki/Shunting-yard_algorithm">ShuntingYard algorithm</a> from Wikipedia
 */
public class ShuntingYardAlgorithm {
	
	public static Stack<EquationObjectData> convert(List<EquationObjectData> list) {
		Stack<EquationObjectData> output = new Stack<EquationObjectData>();
		Stack<EquationObjectData> operators = new Stack<EquationObjectData>();
		
		while(!list.isEmpty()) {
			EquationObjectData token = list.remove(0);
			
			if(token.getType() == EquationObjectType.NUMBER) {
				System.out.println((Float)token.getObject());
				output.add(token);
			}
			else if(token.getType() == EquationObjectType.VARIABLE) {
				System.out.println((String)token.getObject());
				output.add(token);
			}
			else if(token.getType() == EquationObjectType.FUNCTION) {
				System.out.println((Function)token.getObject());
				operators.add(token);
			}
			else{
				Operator ope = (Operator) token.getObject();
				if(ope != Operator.LEFTPARENTHESIS && ope != Operator.RIGHTPARENTHESIS) {
					while(!operators.isEmpty() && 
							(((operators.peek().getType() == EquationObjectType.FUNCTION)
							|| ((Operator)(operators.peek().getObject())).getPrecedence() > ope.getPrecedence())
							|| ((((Operator)(operators.peek().getObject())).getPrecedence() == ope.getPrecedence()) && ope.getAssociativity() == Assoc.LEFT_ASSOC)
							) && ((Operator)(operators.peek().getObject()) != Operator.LEFTPARENTHESIS))
					{
						output.add(operators.pop());
					}
					operators.add(token);
				}
				else if((Operator) token.getObject() == Operator.LEFTPARENTHESIS) {
					operators.add(token);
				}
				else {
					while((Operator)(operators.peek().getObject()) != Operator.LEFTPARENTHESIS) {
						output.add(operators.pop());
					}
					if(((Operator)operators.peek().getObject()) == Operator.LEFTPARENTHESIS) {
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
}
