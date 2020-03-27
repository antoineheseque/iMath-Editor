package fr.iMath.objects;

/**
 * Operator
 * @author antoi
 *
 */
public enum Operator {
	LEFTPARENTHESIS("(",-1,Assoc.NONE),
	RIGHTPARENTHESIS(")",-2,Assoc.NONE),
	PLUS("+",0,Assoc.LEFT_ASSOC),
	MINUS("-",0,Assoc.LEFT_ASSOC),
	MULTIPLY("*",1,Assoc.LEFT_ASSOC),
	DIVIDE("/",1,Assoc.LEFT_ASSOC),
	POWER("^",2,Assoc.RIGHT_ASSOC),
	NEGATE("~",2,Assoc.LEFT_ASSOC);
	
	private String operator;
	private int priority;
	private Assoc associativity;
	 
    Operator(String operator, int priority, Assoc associativity) {
        this.operator = operator;
        this.priority = priority;
        this.associativity = associativity;
    }

    public String getOperator() {
        return operator;
    }
    
    public int getPriority() {
        return priority;
    }
    
    public Assoc getAssociativity() {
        return associativity;
    }
}
