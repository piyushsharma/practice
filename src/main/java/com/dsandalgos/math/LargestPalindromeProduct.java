package com.dsandalgos.math;

public class LargestPalindromeProduct {

    public int largestPalindrome(int n) {
        if(n == 1) {
            return 9;
        }

        long max = (long) (Math.pow(10, n) - 1);
        long min = max / 10;


        for(long nDigitNumber = max; nDigitNumber > min; --nDigitNumber) {

            long possiblePalindrome = Long.valueOf(nDigitNumber + new StringBuilder().append(nDigitNumber).reverse().toString());

            /**
             * Go through all palindrome numbers with 2n digits in a descending order
             * For each possible palindrome number, check if it can be divided by a factor with n digits
             * If so, possible palindrome number % factor should also be a number which has n digits
             * As it's in descending order, the first number that meets this check is the largest palindrome number
             */

            /**
             * Say => n = a * b
             * If both a and b were greater than the square root of n, then a * b would be greater than n.
             * So at least one of those factors must be less than or equal to the square root of n,
             * and if we can't find any factors less than or equal to the square root, n must be prime.
             */

            // we can use the above fact to say that
            // if factor < sqrt(possiblePalindrome)
            //      break;
            // or run the loop until factor >= sqrt(possiblePalindrome) i.e. (factor*factor) > possiblePalindrome
            for(long factor = max; (factor * factor) >= possiblePalindrome; --factor) {

                if(possiblePalindrome % factor == 0) {
                    System.out.println("Palindrome " + possiblePalindrome + ", number1 = " + factor + ", number2 = " + possiblePalindrome/factor);
                    return (int) (possiblePalindrome % 1337);
                }

            }
        }

        return 0;
    }




    public static void main(String[] args) {
        for(int i = 1; i < 9; ++i) {

            System.out.println("N = " + i + ", answer = " + new LargestPalindromeProduct().largestPalindrome(i));
            System.out.println("=======================================================");

        }


    }



}
