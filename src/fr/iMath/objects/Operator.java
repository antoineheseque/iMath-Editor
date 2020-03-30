package fr.iMath.objects;

/**
 * Operator class.
 * @author HESEQUE Antoine
 */
public enum Operator {
	LEFTPARENTHESIS("(",-1,Assoc.NONE),
	RIGHTPARENTHESIS(")",-2,Assoc.NONE),
	PLUS("+",0,Assoc.LEFT_ASSOC),
	MINUS("-",0,Assoc.LEFT_ASSOC),
	MULTIPLY("*",1,Assoc.LEFT_ASSOC),
	DIVIDE("/",1,Assoc.LEFT_ASSOC),
	POWER("^",2,Assoc.RIGHT_ASSOC);
	
	/**
	 * Operator name given as a String
	 */
	private String operator;
	
	/**
	 * The priority for this operator. Used to execute the ShuntingYard Algorithm
	 * @see fr.iMath.algorithms.ShuntingYardAlgorithm
	 */
	private int precedence;
	
	/**
	 * The associativity for this Operator. Can be LEFT_ASSOC or RIGHT_ASSOC
	 */
	private Assoc associativity;
	 
	/**
	 * Create the operator automatically when using (for instance) {@code Operator a = Operator.ADD;}  
	 * @param operator Operator in String format
	 * @param precedence Priority of the operator
	 * @param associativity Associativity of the operator
	 */
    Operator(String operator, int precedence, Assoc associativity) {
        this.operator = operator;
        this.precedence = precedence;
        this.associativity = associativity;
    }
    
    /**
     * Return the operator as a String.
     * @return the operator
     */
    public String getOperator() {
        return operator;
    }
    
    /**
     * Return the priority of the operator.
     * @return the priority
     */
    public int getPrecedence() {
        return precedence;
    }
    
    /**
     * Return the associativity of the operator.
     * @return the associativity
     */
    public Assoc getAssociativity() {
        return associativity;
    }
}
