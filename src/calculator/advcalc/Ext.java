package advcalc;

public class Ext {
    public static final double E = Math.E;

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double log(double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Logarithm undefined for non-positive values.");
        }
        return Math.log(value);
    }

    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial undefined for negative numbers.");
        }
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
