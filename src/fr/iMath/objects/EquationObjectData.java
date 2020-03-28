package fr.iMath.objects;

public class EquationObjectData {
	/**
	 * Contain the type of the equation object. Can be an operator, a variable or a number.
	 */
	private EquationObjectType type;
	private String variable;
	private float number;
	private Operator operator;
	
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
	
	public Object getObject() {
		switch(type) {
		case NUMBER:
			return number;
		case OPERATOR:
			return operator;
		case VARIABLE:
			return variable;
		default:
			return null;
		}
	}

	public Object getType() {
		return this.type;
	}
}
