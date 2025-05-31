package com.dsandalgos.math;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/* Determine whether an integer is a palindrome. Do this without extra space.
 */

/* Hints:
Could negative integers be palindromes? (ie, -1) => NO
If you are thinking of converting the integer to string, note the restriction of using extra space.
You could also try reversing an integer. However, if you have solved the problem "Reverse Integer",
you know that the reversed integer might overflow. How would you handle such case?

There is a more generic way of solving this problem.
*/

public class PalindromeNumber {

    /* More efficient */
    public boolean isPalindrome(int x) {

        // negatives are not palindromes
        if ( x < 0 ) return false;

        int y = x;
        int left = 1;
        // get power of ten number to get first digit of the number
        while (y > 9) {
            y = y/10;
            left *= 10;
        }

        int right = 1;
        // check digits of number in mirror (left-most with rightmost) until middle is reached
        while (left > right) {
            if ((x / right) % 10 != (x / left) % 10) {
                return false;
            }
            right = right * 10;
            left = left /10;
        }
        return true;
    }


    public boolean isPalindromeCleanerSolution(int x) {

        // negatives are not palindromes
        if ( x < 0 ) return false;

        int msdDiv = 1;
        // get power of ten to get first digit of the number
        while (x/msdDiv >= 10) {
            msdDiv = msdDiv * 10;
        }

        // check digits of number in mirror (left-most with rightmost) while middle reached
        // msdDiv == 1 means we have only one digit left in x
        while (x > 0 && msdDiv != 1) {

            int msdigit = x / msdDiv;
            x = x % msdDiv;
            msdDiv = msdDiv / 10; // note we divide by 10 here because we removed msd from x

            int lsdigit = x % 10;
            x = x / 10;

            msdDiv = msdDiv / 10; // note we divide by 10 again because we removed lsd from x

            if(lsdigit != msdigit) return false;
        }
        return true;
    }


    public static int divideByPowerOfTen(int x, int powerOfTen) {
        int y = 1;
        for(int i = 1; i <= powerOfTen; ++i)
            y = 10*y;

        return x/y;
    }

    public boolean isPalindromeV2(int x) {
        int count = 0;
        int temp = x;

        if(x < 0)
            return false;

        while(temp != 0) {
            temp = temp/10;
            ++count;
        }

        for(int i = 0; i < count/2; ++i) {
            int ldigit = divideByPowerOfTen(x, count - 1 - i) % 10;
            int rdigit = divideByPowerOfTen(x, i) % 10;

            if(ldigit != rdigit) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome(999929999));
        System.out.println(new PalindromeNumber().isPalindrome(999));
        System.out.println(new PalindromeNumber().isPalindrome(11299));
        System.out.println(new PalindromeNumber().isPalindrome(899));
        System.out.println(new PalindromeNumber().isPalindrome(1001));
        System.out.println(new PalindromeNumber().isPalindrome(1971221791));
    }

}

