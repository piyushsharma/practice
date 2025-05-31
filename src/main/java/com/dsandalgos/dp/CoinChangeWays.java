package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*

Given a value N, if we want to make change for N cents,
and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins,
how many ways can we make the change? The order of coins doesn’t matter.

For example, for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.

For N = 10 and S = {2, 5, 3, 6}, there are five solutions: {2,2,2,2,2}, {2,2,3,3}, {2,2,6}, {2,3,5} and {5,5}.
So the output should be 5.

*/


public class CoinChangeWays {

    // DP optimized for space
    // O(coins.length * N)
    public int coinChangeWays(int[] coins, int N) {

        int[] dp = new int[N + 1];
        // base case => there is one way to count 0, {} empty set counts as 1
        dp[0] = 1;

        // at each step keep adding the previous solutions to the answer
        for(int coinValue : coins) {
            for(int curAmount = coinValue; curAmount <= N; ++curAmount) {

                dp[curAmount] = dp[curAmount] + dp[curAmount - coinValue];
            }
        }

        return dp[N];
    }

    // DP
    // O(coins.length * N)
    public int coinChangeWaysDP(int[] coins, int N) {
        int i, j, x, y;
        int m = coins.length;

        // We need N+1 rows as the table is constructed in bottom up manner using
        // the base case 0 value case (N = 0)
        int[][] table = new int[N + 1][m];

        // Fill the entries for 0 value case (n = 0)
        // for zero sum, empty set, so set all columns to 1
        for(i = 0; i < m; ++i) table[0][i] = 1;

        // Fill rest of the table entries in bottom up manner
        for (i = 1; i <= N; ++i) {
            for(j = 0; j < m; ++j)  {

                // Count of solutions including coins[j]
                x = (i - coins[j] >= 0) ? table[i - coins[j]][j] : 0;

                // Count of solutions excluding coins[j]
                y = (j >= 1) ? table[i][j-1] : 0;

                // total count
                table[i][j] = x + y;
            }
        }
        return table[N][m-1];
    }


    public int coinChangeWaysRecursion(int[] coins, int N) {
        return coinChangeWays(coins, N, coins.length - 1);
    }

    // count for both possibilities => including the coin[index] towards N and excluding it
    private int coinChangeWays(int[] coins, int N, int index) {

        if(index < 0 || N < 0) return 0;

        if(N == 0) return 1;

        // exclude the coin or include it
        return coinChangeWays(coins, N, index - 1) + coinChangeWays(coins, N - coins[index], index);
    }


    public static void main(String[] args) {

        int arr[] = new int[]{2, 5, 3, 6};
        System.out.println(new CoinChangeWays().coinChangeWays(arr, 10));
        System.out.println(new CoinChangeWays().coinChangeWaysDP(arr, 10));
    }

}
