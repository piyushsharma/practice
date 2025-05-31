package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*

Given weights and values of n items,
put these items in a knapsack of capacity W to get the maximum total value in the knapsack.

In other words, given two integer arrays val[0..n-1] and wt[0..n-1]
which represent values and weights associated with n items respectively.

Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[]
such that sum of the weights of this subset is smaller than or equal to W.
You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

*/

public class IntegerKnapsack {

    // Note: this is 0-1 knapsack, we cannot simply keep adding the same item to maximize value
    // we can pick it only once

    public int knapsack(int[] val, int[] wt,  int W) {

        int N = wt.length;
        int[][] dp = new int[N + 1][W + 1];

        for(int i = 0; i <= N; ++i) {
            for(int curCapacity = 0; curCapacity <= W; ++curCapacity) {

                // curCapacity == 0 => curCapacity is 0, so cannot pick anything
                // i == 0 => base case, we will consider this index as 0 weight
                if (i == 0 || curCapacity == 0) {
                    dp[i][curCapacity] = 0;

                } else if (wt[i-1] > curCapacity) {
                    // current curCapacity is less than the index being considered
                    dp[i][curCapacity] = dp[i-1][curCapacity];

                } else {
                    // either pick this item or we don't, maximize the value
                    dp[i][curCapacity] = Math.max( dp[i-1][curCapacity - wt[i-1]] + val[i - 1],
                                                   dp[i-1][curCapacity] );
                }
            }
        }

        return dp[N][W];
    }


    // either consider the element or do not consider (0/1 property)
    // O(2 ^ n) as we are going through each subset of items
    public int knapsackRecursion(int[] val, int[] wt,  int W) {
        return knapsack(val, wt, W, wt.length - 1);
    }

    private int knapsack(int[] val, int[] wt, int W, int index) {
        if(index == 0 || W == 0) return 0;

        if(wt[index] > W) {
            return knapsack(val, wt, W, index - 1);
        }

        // either consider the item or don't
        return Math.max(val[index] + knapsack(val, wt, W - wt[index], index - 1),
                        knapsack(val, wt, W, index - 1));
    }

    // when we can pick the same item multiple times
    public int knapsackRepeatItems(int[] val, int[] wt,  int W) {

        int[] dp = new int[W + 1];

        for(int i = 0; i <= W; ++i) {
            for(int j = 0; j < wt.length; ++j) {

                if(i + wt[j] <= W) {
                    dp[i + wt[j]] = Math.max(dp[i + wt[j]], dp[i] + val[j]);
                }
            }
        }
        return dp[W];
    }

    public static void main(String[] args) {

        IntegerKnapsack integerKnapsack = new IntegerKnapsack();

        int[] val = new int[]{6,10,12};
        int[] wt = new int[]{1,2,3};

        System.out.println(integerKnapsack.knapsack(val, wt, 5));
    }

}
