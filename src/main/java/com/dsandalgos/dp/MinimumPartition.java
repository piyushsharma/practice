package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */


/*

Given a set of integers, the task is to divide it into two sets S1 and S2 such that the absolute difference between
their sums is minimum.

If there is a set S with n elements, then if we assume Subset1 has m elements,
Subset2 must have n-m elements and the value of abs(sum(Subset1) – sum(Subset2)) should be minimum.

Example:
Input:  arr[] = {1, 6, 11, 5}
Output: 1
Explanation:
Subset1 = {1, 5, 6}, sum of Subset1 = 12
Subset2 = {11}, sum of Subset2 = 11

*/

public class MinimumPartition {

    // O(n * totalSum) // depends on the input size ; pseude polynomial

    /*
    The task is to divide the set into two parts.
    We will consider the following factors for dividing it.
    Let
      dp[n+1][sum+1] = {1 if some subset from 1st to i'th has a sum
                          equal to j
                       0 otherwise}

        i ranges from {1..n}
        j ranges from {0..(sum of all elements)}

    So
        dp[n+1][sum+1]  will be 1 if
        1) The sum j is achieved including i'th item
        2) The sum j is achieved excluding i'th item.

    Let sum of all the elements be S.

    To find Minimum sum difference, w have to find j such
    that Min{sum - j*2  : dp[n][j]  == 1 }
        where j varies from 0 to sum/2

    The idea is, sum of S1 is j and it should be closest
    to sum/2, i.e., 2*j should be closest to sum.

     */
    public int minPartitionDP(int[] arr) {

        int totalSum = 0;
        int sum2 = 0;
        for(int item : arr) {
            totalSum += item;
        }

        int n = arr.length;

        // cache
        boolean[][] dp = new boolean[n + 1][totalSum + 1];

        // set true for 0 sum
        for(int i = 0; i <= n; ++i) dp[i][0] = true;

        // set false for 0 elements and > 0 sum
        for(int j = 1; j <= totalSum; ++j) dp[0][j] = false;

        for(int i = 1; i <= n; ++i) {
            for(int j = 1; j <= totalSum; ++j) {

                // exclude ith element
                dp[i][j] = dp[i-1][j];

                // include ith element
                if(arr[i - 1] <= j) {
                    dp[i][j] = dp[i][j] | dp[i-1][j - arr[i-1]];
                }
            }
        }

        // Initialize difference of two sums.
        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j]
        // is true where j loops from totalSum/2 t0 0
        for (int j= totalSum/2; j >= 0; --j) {
            if (dp[n][j] == true) {
                diff = totalSum - (2 * j);
                break;
            }
        }
        return diff;
    }


    // O(2^n) => looking at 2 values for each call, tree fanout recursion
    public int minPartition(int[] arr) {
        int totalSum = 0;
        int sum2 = 0;
        for(int item : arr) {
            totalSum += item;
        }

        return minPartition(arr, arr.length - 1, totalSum, sum2);
    }

    private int minPartition(int[] arr, int i, int totalSum, int sum2) {

        // at the end of the set
        // sum of partition one = totalSum - sum2;
        // sum of partition two = sum2;
        // return absolute diff between partitionOne and partitioTwo
        if(i < 0) {
            return Math.abs((totalSum - sum2) - sum2);
        }

        return Math.min(minPartition(arr, i - 1, totalSum, sum2 + arr[i]),
                minPartition(arr, i - 1, totalSum, sum2));
    }


    public static void main(String[] args) {
        MinimumPartition m = new MinimumPartition();

        System.out.println(m.minPartitionDP(new int[]{1, 6, 5, 11}));
        System.out.println(m.minPartition(new int[]{3, 1, 4, 2, 2, 1}));

    }


}
