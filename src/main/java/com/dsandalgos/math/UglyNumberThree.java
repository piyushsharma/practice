package com.dsandalgos.math;

/**
 * Write a program to find the n-th ugly number.
 *
 * Ugly numbers are positive integers which are divisible by a or b or c.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 3, a = 2, b = 3, c = 5
 * Output: 4
 * Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
 * Example 2:
 *
 * Input: n = 4, a = 2, b = 3, c = 4
 * Output: 6
 * Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 12... The 4th is 6.
 * Example 3:
 *
 * Input: n = 5, a = 2, b = 11, c = 13
 * Output: 10
 * Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.
 * Example 4:
 *
 * Input: n = 1000000000, a = 2, b = 217983653, c = 336916467
 * Output: 1999999984
 *
 *
 * Constraints:
 *
 * 1 <= n, a, b, c <= 10^9
 * 1 <= a * b * c <= 10^18
 * It's guaranteed that the result will be in range [1, 2 * 10^9]
 *
 */


public class UglyNumberThree {


    /**
     *
     *   Think of a venn diagram. 3 circles -- each representing all number divisible by a specific divisor
     *   = (num / a) + (num / b) + (num / c)
     *
     *   The overlaps between the circles are the lcm of two numbers at a time
     *
     *   Thus, the common parts of the circle become (num / lcm (a, b)) + (num / lcm (b, c)) + (num / lcm (c, a))
     *
     *   When we subtract all overlaps from the circle -- we need to add back the area common to all 3 circles.
     *
     *   Therefore, the final solution becomes:
     *
     *   (num / a) + (num / b) + (num / c)
     *   - ( (num / lcm (a, b)) + (num / lcm (b, c)) + (num / lcm (c, a)) )
     *   + (num / lcm (a, b, c))
     *
     */


    /**
     *  GCD: Greatest common divisor of two numbers is the greatest factor present in both
     *  for eg. two numbers => 15 (factors = 1, 3, 5, 15) AND 9 (factors = 1, 3, 9) => GCD(15, 9) = 3
     *
     *  LCM: Lowest common multiple is the smallest number multiple of both a and b
     *  LCM (a, b) =  ( a * b ) / GCD (a, b)
     */



    private long gcd(long a, long b) {
        if(b < a) {
            return gcd(b, a);
        }

        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }


    private long lcm(long a, long b) {
        long y = gcd(a, b);
        return (a * b) / y;
    }


    private long countAllDivisors(int n, long a, long b, long c) {

        long x = (n / a) + (n / b) + (n / c);
        long y = (n / lcm(a, b)) + (n / lcm(b, c)) + (n / lcm(c, a));

        long lcmAll = lcm(lcm(a, b), c);
        long z = (n / lcmAll);

        return x - y + z;
    }

    public int nthUglyNumber(int n, int a, int b, int c) {

        int low = 0;
        int high = Integer.MAX_VALUE;
        int result = -1;

        while(low <= high) {

            int mid = low + (high - low)/2;
            long totalDivisors = countAllDivisors(mid, a, b, c);
            if(totalDivisors >= n) {
                // we want to keep reducing high till we find the lowest result -
                // because there are other numbers that may not be exactly divisible by a or b or c that show up
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }



}
