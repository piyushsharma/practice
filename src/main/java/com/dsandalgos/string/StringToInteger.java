package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
    Implement atoi to convert a string to an integer.
    Requirements for atoi:
        The function first discards as many whitespace characters as necessary
        until the first non-whitespace character is found.
        Then, starting from this character,
        takes an optional initial plus or minus sign
        followed by as many numerical digits as possible,
        and interprets them as a numerical value.

        The string can contain additional characters
        after those that form the integral number,
        which are ignored and have no effect on the behavior of this function.

        If the first sequence of non-whitespace
        characters in str is not a valid integral number,
        or if no such sequence exists because either str is empty
        or it contains only whitespace characters, no conversion is performed.

        If no valid conversion could be performed, a zero value is returned.
        If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
*/
public class StringToInteger {


    public int myAtoi(String str) {

        int len = str.length();
        int i = 0;
        while(i < len && str.charAt(i) == ' ') { ++i; }

        int sign = 1;
        if(i < len && str.charAt(i) == '+') {
            ++i;
        } else if(i < len && str.charAt(i) == '-') {
            sign = -1;
            ++i;
        }

        int maxDiv10 = Integer.MAX_VALUE / 10;

        int result = 0;
        while(i < len) {

            int val = str.charAt(i) - '0';
            if (val < 0 || val > 9) {
                break;  // return whatever we have parsed so far
            }

            // if result is > maxDiv10 at this point
            // it means we did result = some number greater than 2147483647 * 10 + value on the last iteration itself
            // so on this iteration there is a definite overflow
            // also, if result == 2147483647, i.e. we did result = 214748364 * 10 + 7 in last iteration, if the
            // current val >= 8, we will do 2147483647 * 10 + 8 and make it overflow
            if(result > maxDiv10 || result == maxDiv10 && val >= 8) {
                return sign == -1 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = result * 10 + val;
            ++i;
        }
        return sign * result;
    }


    public int myAtoiUsingDouble(String str) {
        int len = str.length();
        boolean negative = false;
        int i = 0;
        while(i < len) {
            if(str.charAt(i) == ' ') {
                ++i;
                continue;
            }
            break;
        }

        if(i < len && str.charAt(i) == '+') {
            ++i;
        } else if(i < len && str.charAt(i) == '-') {
            negative = true;
            ++i;
        }

        double result = 0;
        while(i < len) {

            int val = str.charAt(i) - '0';
            if (val < 0 || val > 9) {
                break;
            }

            // negative underflow
            if(negative && -result <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }

            // positive overflow
            if(result >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }

            result = result * 10 + val;
            ++i;
        }
        if(negative) {
            result = -result;
        }

        return (int)result;
    }

    public int myAtoiv2(String str) {

        /* trim white spaces from front */
        char[] a = str.trim().toCharArray();
        int length = a.length;
        if(length == 0) {
            return 0;
        }

        int base = 10;
        boolean negative = false;
        int i = 0;
        double result = 0;

        if (a[i] == '-') {
            negative = true;
            ++i;
        } else if (a[i] == '+') {
            ++i;
        }

        if(Character.isDigit(a[i])) {
            result = Character.digit(a[i], base);
            ++i;
        } else {
            return 0;
        }

        while(i < length) {
            /* Break at the first encounter of a non integer value in the string */
            if(!Character.isDigit(a[i])) {
                break;
            }

            result = result * 10 + Character.digit(a[i], base);
            if(negative && -result <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            if(result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            ++i;
        }

        return negative ? (int)-result : (int)result;
    }

    public static void main(String[] args) {
        StringToInteger s = new StringToInteger();

        System.out.println(s.myAtoi("      "));
        System.out.println("=====================================");
        System.out.println(s.myAtoi("  +1asa")); // Expected 1
        System.out.println("=====================================");
        System.out.println(s.myAtoi("   -2sda"));
        System.out.println("=====================================");
        System.out.println(s.myAtoi("2147483647 "));
        System.out.println("=====================================");
        System.out.println(s.myAtoi("-2147483648 "));
        System.out.println("=====================================");
        System.out.println(s.myAtoi("-2147483649 "));
        System.out.println("=====================================");
        System.out.println(s.myAtoi("21474836477 "));
        System.out.println("=====================================");
        System.out.println(s.myAtoi(" -0012a42 ")); // Expected -12
        System.out.println("=====================================");
        System.out.println(s.myAtoi("    10522545459")); // Expected 2147483647
        System.out.println("=====================================");
        System.out.println(s.myAtoi(" +-13  ")); // Expected 0
    }

}
