package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*

There are n coins in a line. (Assume n is even). Two players take turns to take a coin from one of the ends
of the line until there are no more coins left. The player with the larger amount of money wins.
1. Would you rather go first or second? Does it matter?
2. Assume that you go first, describe an algorithm to compute the maximum amount of money you can win.

*/

public class CoinsInALine {

    /*
       Solution 1 =>
       Going first will guarantee that you will not lose. By following the strategy below,
       you will always win the game (or get a possible tie).
            1. Count the sum of all coins that are odd-numbered. (Call this X)
            2. Count the sum of all coins that are even-numbered. (Call this Y)
            3. If X > Y, take the left-most coin first. Choose all odd-numbered coins in subsequent moves.
            4. If X < Y, take the right-most coin first. Choose all even-numbered coins in subsequent moves.
            5. If X == Y, you will guarantee to get a tie if you stick with taking only even- numbered/odd-numbered
               coins.
     */

    /*
       Solution 2 =>
       Note: Not the same as one, counter example => { 3, 2, 2, 3, 1, 2 },
       You pick the coin at index 0, opponent picks index 1/5, you pick index 5/2, opponent has to pick 2/4, you pick 3

       DP Logic => f(A, m, n)
       Assume we pick A[m], if opponent is equally smart he will pick
       max(A[m+1] + f(A, m+2, n), A[n] + f(A, m+1, n-1)), so we will get min(f(A, m+2, n), f(A, m+1, n-1))
         .
            If we pick A[m] => A[m] + min(f(A, m+2, n), f(A, m+1, n-1))
       .   .

       Assume we pick A[n], if opponent is equally smart he will pick
       max(A[m] + f(A, m+1, n-1), A[n-1] + f(A, m, n-2)), so we will get min(f(A, m+1, n-1), f(A, m, n-2))
         .
            If we pick A[n] => A[n] + min(f(A, m+1, n-1), f(A, m, n-2))
       .   .


       Thus, f(A, m, n) => Max(  A[m] + min(f(A, m+2, n), f(A, m+1, n-1)),  A[n] + min(f(A, m+1, n-1), f(A, m, n-2)) )

     */

    // Time O(n2), Space O(n2)
    public int maxMoney(int[] A) {
        int length = A.length;
        int[][] cache = new int[length][length];

        for(int i = 0; i < length; ++i)  {

            // m and n as per the above explanations
            int m = 0;
            int n = i;
            while(m < length && n < length) {

                // Pick A[m]
                int a = 0;
                if(m + 2 < length) a = cache[m+2][n];

                int b = 0;
                if(m + 1 < length && n - 1 >= 0) b = cache[m+1][n-1];

                // Pick A[n]
                // reuse b
                int c = 0;
                if(n - 2 >= 0) c = cache[m][n-2];

                cache[m][n] = Math.max(A[m] + Math.min(a, b),
                                        A[n] + Math.min(b, c));

                ++m;
                ++n;
            }
        }

        return cache[0][length-1];
    }

    public static void main(String[] args) {
        CoinsInALine c = new CoinsInALine();
        int[] A = new int[]{3, 2, 2, 3, 1, 2};

        System.out.println(c.maxMoney(A));

    }


}
