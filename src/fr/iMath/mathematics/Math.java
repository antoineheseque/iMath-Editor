package fr.iMath.mathematics;
/**
 * Basic mathematics equations
 * @author HESEQUE Antoine
 * @author JEANNIN Louis
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
		if(b == 0){
			System.out.println("Division par 0");
			return 0;
		}
		else
			return a/b;
	}

	/**
	 * Power function.
	 * @param a First number
	 * @param b Second number
	 * @return pow(a,b)
	 */
	public static float power(float a, float b) { System.out.println(a + "^" + b); return (float)java.lang.Math.pow(a,b); }

	/**
	 * Power function.
	 * @param a number
	 * @return ln(a)
	 */
	public static float ln(float a) { return (float)java.lang.Math.log(a); }

	/**
	 * Sqrt function.
	 * @param a First number
	 * @return sqrt(a)
	 */
	public static float sqrt(float a) { return (float)java.lang.Math.sqrt(a); }

	/**
	 * Exponential function function.
	 * @param a First number
	 * @return exp(a)
	 */
	public static float exp(float a) { return (float)java.lang.Math.exp(a); }

}
