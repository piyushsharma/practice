package com.dsandalgos.dp;
/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/*
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
 */


public class UniquePathsTwo {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[][] cache = new int[m][n];

        // relying on the fact that cache is initialized as zeroes, so not zeroing the remaining entries after break
        for(int i = 0; i < m; ++i) {
            // Note: As soon as we see a obstacle going down the 0th column, we cannot proceed below that
            if(obstacleGrid[i][0] == 1)
                break;
            cache[i][0] = 1;
        }
        for(int j = 0; j < n; ++j) {
            // Note: As soon as we see a obstacle going across the 0th row, we cannot proceed below that
            if(obstacleGrid[0][j] == 1)
                break;
            cache[0][j] = 1;
        }

        for(int i = 1; i < m; ++i) {
            for(int j = 1; j < n; ++j) {

                if(obstacleGrid[i][j] == 1) {
                    cache[i][j] = 0;
                } else {
                    cache[i][j] = cache[i-1][j] + cache[i][j-1];
                }
            }

        }

        return cache[m-1][n-1];
    }

    // reusing obstacle grid as cache
    public int uniquePathsWithObstaclesV2(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // base condition: if start is blocked, cannot proceed anywhere.
        if(obstacleGrid[0][0] == 1)
            return 0;

        int i = 0;
        for(i = 0; i < m; ++i) {
            // Note: As soon as we see a obstacle going down the 0th column, we cannot proceed below that
            if(obstacleGrid[i][0] == 1) {
                obstacleGrid[i][0] = 0;
                break;
            }
            obstacleGrid[i][0] = 1;
        }
        // if we encountered break, all other obstacles should be zeroed out as we cannot reach that point
        while(i < m) {
            obstacleGrid[i][0] = 0;
            ++i;
        }

        int j = 1;
        // start j with 1, as obstacleGrid[0][0] must have already been written with the right value above
        for(j = 1; j < n; ++j) {
            // Note: As soon as we see a obstacle going across the 0th row, we cannot proceed below that
            if(obstacleGrid[0][j] == 1) {
                obstacleGrid[0][j] = 0;
                break;
            }
            obstacleGrid[0][j] = 1;
        }
        // if we encountered break, all other obstacles should be zeroed out as we cannot reach that point
        while(j < n) {
            obstacleGrid[0][j] = 0;
            ++j;
        }

        for(i = 1; i < m; ++i) {
            for(j = 1; j < n; ++j) {

                if(obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i-1][j] + obstacleGrid[i][j-1];
                }
            }

        }

        return obstacleGrid[m-1][n-1];
    }


    // recursion
    public int uniquePathsWithObstaclesRecursion(int[][] obstacleGrid) {
        int m = obstacleGrid.length - 1;
        int n = obstacleGrid[0].length - 1;

        return uniquePathsWithObstaclesR(m, n, obstacleGrid);
    }

    private int uniquePathsWithObstaclesR(int m, int n, int[][] obstacleGrid) {
        if(m < 0 || n < 0)
            return 0;

        if(obstacleGrid[m][n] == 1)
            return 0;

        if(m == 0 && n == 0)
            return 1;

        return uniquePathsWithObstaclesR(m-1, n, obstacleGrid) + uniquePathsWithObstaclesR(m, n-1, obstacleGrid);
    }


    public static void main(String[] args) {
        UniquePathsTwo u = new UniquePathsTwo();

        int[][] test1 = {{0,0,0}, {0,1,0}, {0,0,0}} ;
        System.out.println(u.uniquePathsWithObstacles(test1));
        int[][] test1v2 = {{0,0,0}, {0,1,0}, {0,0,0}} ;
        System.out.println(u.uniquePathsWithObstaclesV2(test1v2));
        int[][] testR1 = {{0,0,0}, {0,1,0}, {0,0,0}} ;
        System.out.println(u.uniquePathsWithObstaclesRecursion(testR1));

        System.out.println("-----------------------------------------");

        int[][] test2 = {{1,0}} ;
        System.out.println(u.uniquePathsWithObstacles(test2));
        int[][] test2v2 = {{1,0}} ;
        System.out.println(u.uniquePathsWithObstaclesV2(test2v2));
        int[][] testR2 = {{1,0}} ;
        System.out.println(u.uniquePathsWithObstaclesRecursion(testR2));

        System.out.println("-----------------------------------------");

        int[][] test3 = {{0,1,0,0,1}};
        System.out.println(u.uniquePathsWithObstacles(test3));
        int[][] test3v2 = {{0,1,0,0,1}};
        System.out.println(u.uniquePathsWithObstaclesV2(test3v2));
        int[][] testR3 = {{0,1,0,0,1}};
        System.out.println(u.uniquePathsWithObstaclesRecursion(testR3));

        System.out.println("-----------------------------------------");

        int[][] test4 = {{0,0},{1,1},{0,0}};
        System.out.println(u.uniquePathsWithObstacles(test4));
        int[][] test4v2 = {{0,0},{1,1},{0,0}};
        System.out.println(u.uniquePathsWithObstaclesV2(test4v2));
        int[][] testR4 = {{0,0},{1,1},{0,0}};
        System.out.println(u.uniquePathsWithObstaclesRecursion(testR4));
    }
}
