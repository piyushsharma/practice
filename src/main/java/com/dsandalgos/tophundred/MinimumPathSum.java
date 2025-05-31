package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Piyush Sharma
 */

/*
Given a m x n grid filled with non-negative numbers,
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
*/


public class MinimumPathSum {



    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        dp[0][0] = grid[0][0];
        for(int i = 1; i < m; ++i) {
            dp[i][0] = grid[i][0] + dp[i-1][0];
        }
        for(int j = 1; j < n; ++j) {
            dp[0][j] = grid[0][j] + dp[0][j-1];
        }

        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }

        return dp[m-1][n-1];
    }


    public int minPathSumBackTrack(int[][] grid) {

        List<Integer> sumList = new ArrayList<>();
        minPathSum(grid, 0, 0, 0, sumList);

        int result = sumList.get(0);
        for(Integer sum : sumList) {
            if (sum < result) result = sum;
        }

        return result;
    }

    private void minPathSum(int[][] grid, int m, int n, int curSum, List<Integer> sumList) {

        if(m >= grid.length || n >= grid[0].length) return;

        if(m == grid.length - 1 && n == grid[0].length - 1) {
            sumList.add(curSum + grid[m][n]);
        }

        minPathSum(grid, m+1, n, curSum + grid[m][n], sumList);
        minPathSum(grid, m, n+1, curSum + grid[m][n], sumList);
    }

    public static void main(String[] args) {

        MinimumPathSum m = new MinimumPathSum();
        int[][] grid = new int[][]{{0,1,2,3}};

        grid = new int[][]{{0,1,2,3}, {0,0,0,2}};

        System.out.println(m.minPathSum(grid));
    }




}
