package fr.iMath.objects;

/**
 * Contain the type of the equation object. Can be an operator, a variable or a number.
 */
public class EquationObjectData {
	private EquationObjectType type;
	private String variable;
	private float number;
	private Operator operator;
	private Function function;
	
	public EquationObjectData(String variable) {
		this.type = EquationObjectType.VARIABLE;
		this.variable = variable;
	}
	
	public EquationObjectData(float number) {
		this.type = EquationObjectType.NUMBER;
		this.number = number;
	}
	
	public EquationObjectData(Operator operator) {
		this.type = EquationObjectType.OPERATOR;
		this.operator = operator;
	}
	
	public EquationObjectData(Function function) {
		this.type = EquationObjectType.FUNCTION;
		this.function = function;
	}
	
	public Object getObject() {
		switch(type) {
		case NUMBER:
			return number;
		case OPERATOR:
			return operator;
		case VARIABLE:
			return variable;
		case FUNCTION:
			return function;
		default:
			return null;
		}
	}

	public EquationObjectType getType() {
		return this.type;
	}
}
