package com.dsandalgos.string;

/**
 * Created by Piyush Sharma on 2/19/15.
 */

/* Problem Statement:

Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true

Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up
front before implementing one.

*/

public class ValidNumber {

//    http://www.vogella.com/tutorials/JavaRegularExpressions/article.html
    public boolean isNumber(String s) {
        s = s.trim();
        if(s.length() > 0) {
            if (s.charAt(0) == '+' || s.charAt(0) == '-') {
                s = s.substring(1);
            }
        }

        // Part 1 => start with "\\d+(\\.)?" or "\.\d+"
        // Part 2 => Any number of digits after that (\\d*)
        // Part 3 => optional e to the power of some positve/negative number sequence
        String pattern1 = "^(\\d+(\\.)?|\\.\\d+)\\d*(e(\\+|-)?\\d+)?";
        return s.matches(pattern1);

    }

    // Optional Leading whitespaces
    // Optional Plus (+) or minus (–) sign
    // Number:
    //      Whole
    //      Decimal (1., .1, 0.1 are all valid)
    //      Exponent (1e10)
    // Optional trailing whitespace
    public boolean isNumberWithoutRegex(String s) {

        int i = 0; int len = s.length();

        // handle leading white spaces
        while(i < len && s.charAt(i) == ' ') ++i;

        // handle optional +/-
        if (i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;

        boolean isNumber = false;

        // parse whole number / part before the decimal point
        while(i < len && (s.charAt(i) - '0') >= 0 && (s.charAt(i) - '0') <= 9) {
            ++i;
            isNumber = true;
        }

        // parse decimal point and digits after that point
        if(i < len && s.charAt(i) == '.') {
            ++i;
            // fraction part
            while(i < len && (s.charAt(i) - '0') >= 0 && (s.charAt(i) - '0') <= 9) {
                ++i;
                isNumber = true;
            }
        }

        // add isNumber to the if condition because we cannot have just e or .e
        // there has to be e to the power of something
        if(isNumber && i < len && s.charAt(i) == 'e') {
            ++i;
            isNumber = false; // since we need something after e as well
            // handle optional + / - after e
            if (i < len && (s.charAt(i) == '+' || s.charAt(i) == '-')) i++;

            // parse digits after e
            while(i < len && (s.charAt(i) - '0') >= 0 && (s.charAt(i) - '0') <= 9) {
                ++i;
                isNumber = true;
            }
        }

        // handle trailing white spaces
        while(i < len && s.charAt(i) == ' ') ++i;

        return isNumber && i == len;
    }


    /* Done by a lot of trial and error since could not cover all corner cases from the definition
       The regex's used can definitely be made a lot better and merged into fewer cases as well.
     */
    public boolean isNumberV2(String s) {
        s = s.trim();
        if(s.length() > 0) {
            if (s.charAt(0) == '+' || s.charAt(0) == '-') {
                s = s.substring(1);
            }
        }
        boolean isNumber = false;
        if(s.matches("\\d+")) {
            return true;
        }
        if(s.matches("\\d*\\.\\d+") || s.matches("\\d+\\.\\d*")) {
            return true;
        }
        if(s.matches("\\d+e\\+?-?\\d+") || s.matches("\\d+\\.e-?\\d+") || s.matches("\\d*\\.\\d+e\\+?-?\\d+")) {
            return true;
        }
        return isNumber;
    }

    public static void main(String args[]) {

        ValidNumber v = new ValidNumber();

        System.out.println(v.isNumber("+-959440"));   // should be false
        System.out.println(v.isNumber("959440.94f"));   // should be false
        System.out.println(v.isNumber("84656e656D"));   // should be false
        System.out.println(v.isNumber("e9"));   // should be false
        System.out.println(v.isNumber("."));   // should be false
        System.out.println(v.isNumber("0e"));   // should be false
        System.out.println(v.isNumber(".e"));   // should be false
        System.out.println(v.isNumber(".e1"));   // should be false
        System.out.println(v.isNumber(".0e"));   // should be false

        System.out.println(v.isNumber("+959440"));   // should be true
        System.out.println(v.isNumber("0.8"));   // should be true
        System.out.println(v.isNumber(".1"));   // should be true
        System.out.println(v.isNumber("0."));   // should be true
        System.out.println(v.isNumber("3."));   // should be true
        System.out.println(v.isNumber("-1."));   // should be true
        System.out.println(v.isNumber("2e0"));   // should return true
        System.out.println(v.isNumber("46.e3"));   // should be true
        System.out.println(v.isNumber("005047e+6"));   // should be true
        System.out.println(v.isNumber(".2e81"));   // should be true
        System.out.println(v.isNumber("32.e-80123"));   // should be true
        System.out.println(v.isNumber("005047e+6"));   // should be true
        System.out.println(v.isNumber(".005047e+6"));   // should be true
        System.out.println(v.isNumber("166e-02767"));   // should be true
        System.out.println(v.isNumber("32.e-80123")); // should be true
    }
}
