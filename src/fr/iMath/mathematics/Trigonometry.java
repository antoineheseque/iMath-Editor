package fr.iMath.mathematics;

import java.lang.Math;

public class Trigonometry {
	// Implement trigonometry math here ..
    /**
     * sinus function.
     * @param a number
     * @return sin(a)
     */
    public static float sin(float a) { return (float)Math.sin(a); }

    /**
     * cosinus function.
     * @param a number
     * @return sin(a)
     */
    public static float cos(float a) { return (float)Math.cos(a); }

    /**
     * tangent function.
     * @param a number
     * @return tan(a)
     */

    public static float tan(float a) { return (float)Math.tan(a); }


    /**
     * Return normalized cardinal sinus function.
     * @param a number
     * @return sinc(a)
     */

    public static float sinc(float a) {
        if(a != 0)return (float)(Math.sin(a)/a);
        else return (float)1;
    }

    /**
     * PI function.
     * @return pi
     */
    public static float pi() { return (float)Math.PI; }

}
