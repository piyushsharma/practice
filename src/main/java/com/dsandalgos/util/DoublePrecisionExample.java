package com.dsandalgos.util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Comprehensive examples of how to limit double values to a certain decimal size in Java
 */
public class DoublePrecisionExample {
    
    public static void main(String[] args) {
        double originalValue = 3.14159265359;
        int decimalPlaces = 2;
        
        System.out.println("Original value: " + originalValue);
        System.out.println("Target decimal places: " + decimalPlaces);
        System.out.println();
        
        // Method 1: Using BigDecimal (Recommended for precision)
        System.out.println("Method 1: BigDecimal (Recommended)");
        double result1 = roundWithBigDecimal(originalValue, decimalPlaces);
        System.out.println("Result: " + result1);
        System.out.println();
        
        // Method 2: Using Math.round() with multiplication
        System.out.println("Method 2: Math.round() with multiplication");
        double result2 = roundWithMathRound(originalValue, decimalPlaces);
        System.out.println("Result: " + result2);
        System.out.println();
        
        // Method 3: Using DecimalFormat
        System.out.println("Method 3: DecimalFormat");
        String result3 = formatWithDecimalFormat(originalValue, decimalPlaces);
        System.out.println("Result: " + result3);
        System.out.println();
        
        // Method 4: Using String.format()
        System.out.println("Method 4: String.format()");
        String result4 = formatWithStringFormat(originalValue, decimalPlaces);
        System.out.println("Result: " + result4);
        System.out.println();
        
        // Method 5: Using printf (for display only)
        System.out.println("Method 5: printf (display only)");
        System.out.printf("Result: %.2f%n", originalValue);
        System.out.println();
        
        // Demonstrate different rounding modes
        System.out.println("Different rounding modes with BigDecimal:");
        demonstrateRoundingModes(originalValue, decimalPlaces);
    }
    
    /**
     * Method 1: Using BigDecimal (Most precise and recommended)
     * This method avoids floating-point precision issues
     */
    public static double roundWithBigDecimal(double value, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    /**
     * Method 2: Using Math.round() with multiplication
     * Simple but may have precision issues with very large numbers
     */
    public static double roundWithMathRound(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(value * scale) / scale;
    }
    
    /**
     * Method 3: Using DecimalFormat
     * Returns a formatted string, good for display purposes
     */
    public static String formatWithDecimalFormat(double value, int decimalPlaces) {
        StringBuilder pattern = new StringBuilder("#.");
        for (int i = 0; i < decimalPlaces; i++) {
            pattern.append("0");
        }
        DecimalFormat df = new DecimalFormat(pattern.toString());
        return df.format(value);
    }
    
    /**
     * Method 4: Using String.format()
     * Returns a formatted string, good for display purposes
     */
    public static String formatWithStringFormat(double value, int decimalPlaces) {
        return String.format("%." + decimalPlaces + "f", value);
    }
    
    /**
     * Demonstrates different rounding modes available in BigDecimal
     */
    public static void demonstrateRoundingModes(double value, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        
        System.out.println("Original: " + bd);
        System.out.println("HALF_UP: " + bd.setScale(decimalPlaces, RoundingMode.HALF_UP));
        System.out.println("HALF_DOWN: " + bd.setScale(decimalPlaces, RoundingMode.HALF_DOWN));
        System.out.println("HALF_EVEN: " + bd.setScale(decimalPlaces, RoundingMode.HALF_EVEN));
        System.out.println("UP: " + bd.setScale(decimalPlaces, RoundingMode.UP));
        System.out.println("DOWN: " + bd.setScale(decimalPlaces, RoundingMode.DOWN));
        System.out.println("CEILING: " + bd.setScale(decimalPlaces, RoundingMode.CEILING));
        System.out.println("FLOOR: " + bd.setScale(decimalPlaces, RoundingMode.FLOOR));
    }
    
    /**
     * Utility method similar to the one found in your GenericDateUtil class
     * but adapted for double values
     */
    public static double round(double value, int decimalPlaces) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
} 