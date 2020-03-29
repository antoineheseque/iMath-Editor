package fr.iMath.mathematics;

/**
 * Basic mathematics equations
 * @author HESEQUE Antoine
 *
 */
public class Math {
	
	/**
	 * Add two floats.
	 * @param a First number
	 * @param b Second number
	 * @return a+b
	 */
	public static float add(float a, float b) { return a+b; }
	
	/**
	 * Substract two floats.
	 * @param a First number
	 * @param b Second number
	 * @return b-a
	 */
	public static float substract(float a, float b) { return b-a; }
	
	/**
	 * Multiply two floats.
	 * @param a First number
	 * @param b Second number
	 * @return a*b
	 */
	public static float multiply(float a, float b) { return a*b; }
	
	/**
	 * Divide two floats.
	 * @param a First number
	 * @param b Second number
	 * @return a/b
	 */
	public static float divide(float a, float b) { 
		if(b!=0)
			return a/b;
		return 0;
	}
}
