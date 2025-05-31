package com.dsandalgos.tophundred;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time.
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.
*/

public class UniquePaths {

    // recursion bottom up (going from m-1, n-1 to 0, 0)
    public int uniquePathsRecurBottomUp(int m, int n) {
        // you cannot have zero rows or zero columns (in this case m and n are not index)
        if(m == 0 || n == 0) return 0;

        return uniquePathsRecursion(m-1, n-1);
    }


    public int uniquePathsRecursion(int m, int n) {
        // if we are at zeroeth row or zeroeth column, return 1
        if(m == 0 || n == 0) return 1;

        return uniquePathsRecursion(m - 1, n) + uniquePathsRecursion(m, n - 1);
    }

    // recursion top down (going from 0, 0, to m-1, n-1)
    public int uniquePathsRecurTopDown(int m, int n) {
        return uniquePathsRecursion(m, n, 0, 0);
    }

    private int uniquePathsRecursion(int m, int n, int mCur, int nCur) {
        if(mCur >= m || nCur >= n)
            return 0;

        if(mCur == m-1 && nCur == n-1)
            return 1;

        return uniquePathsRecursion(m, n, mCur + 1, nCur) + uniquePathsRecursion(m, n, mCur, nCur + 1);
    }


    // dp (backtrack) top down (0, 0 to m-1, n-1)
    public int uniquePathsTopDownBT(int m, int n) {
        if(m <= 0 || n <= 0)
            return 0;

        int[][] cache = new int[m][n] ;

        for(int i = 0; i < m; ++i) {
            cache[i][0] = 1;
        }
        for(int i = 0; i < n; ++i) {
            cache[0][i] = 1;
        }

        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {
                cache[i][j] = cache[i-1][j] + cache[i][j-1];
            }
        }
        return cache[m-1][n-1];
    }

/*
       0
     _________________________________________
  0 |  21 |  15 |  10 |  6  |  3  |  1  |  0  |
    |_____|_____|_____|_____|_____|_____|_____|
    |  6  |   5 |  4  |  3  |  2  |  1  |  0  |
    |_____|_____|_____|_____|_____|_____|_____|
    |  1  |  1  |  1  |  1  |  1  | Fin |  1  |   <- This is the only initialization required, this makes sure that
    |_____|_____|_____|_____|_____|__1__|_____|      all the other row = m-1,  and column = n-1 have 1 stored in them
    |  0  |  0  |  0  |  0  |  0  |  0  |  0  |
    |_____|_____|_____|_____|_____|_____|_____|
*/
    // dp (backtrack) bottom up (m-1, n-1 to 0, 0)
    public int uniquePaths(int m, int n) {
        if(m <= 0 || n <= 0)
            return 0;

        // since we will be doing i+1, j+1 in the loop, we allot a bigger cache
        int[][] cache = new int[m+1][n+1];
        cache[m-1][n] = 1; // this is the position

        for(int i = m-1; i >= 0; --i) {
            for(int j = n-1; j >= 0; --j) {
                cache[i][j] = cache[i+1][j] + cache[i][j+1];
            }
        }
        return cache[0][0];
    }


    public static void main(String[] args) {
        UniquePaths u = new UniquePaths();

        for(int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                System.out.printf("%d ", u.uniquePaths(i, j));
                System.out.printf("%d ", u.uniquePathsRecurTopDown(i, j));
                System.out.printf("%d \n", u.uniquePathsRecurBottomUp(i, j));
            }
        }
    }

}
