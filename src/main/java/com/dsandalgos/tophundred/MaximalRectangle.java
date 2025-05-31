package com.dsandalgos.tophundred;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Piyush Sharma
 */

/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

=> Return 6

*/


public class MaximalRectangle {

    /**
     * Imagine an algorithm where for each point we computed a rectangle by doing the following:
     *
     * 1) Finding the maximum height of the rectangle by iterating upwards until a 0 is reached
     * 2) Finding the maximum width of the rectangle by iterating outwards left and right until a height
     * that doesn't accommodate the maximum height of the rectangle
     *
     *
     *
     * Given a maximal rectangle with height h, left bound l, and right bound r,
     * there must be a point on the interval [l, r] on the rectangle's base
     * where the number of consecutive ones (height) above the point is <=h.
     *
     * If this point exists, then the rectangle defined by the point in the above manner will be the maximal rectangle,
     * as it will reach height h iterating upward and then expand to the bounds of [l, r]
     * as all heights within those bounds must accommodate h for the rectangle to exist.
     *
     * If this point does not exist, then the rectangle cannot be maximum,
     * as you would be able to create a bigger rectangle by simply increasing the height of original rectangle,
     * since all heights on the interval [l, r] would be greater than h.
     *
     * As a res for each point you only need to compute h, l, and r - the height, left bound,
     * and right bound of the rectangle it defines.
     *
     * Using dynamic programming, we can use the h, l, and r of each point in the previous row
     * to compute the h, l, and r for every point in the next row in linear time.
     *
     * Algorithm
     *
     * Given row matrix[i], we keep track of the h, l, and r
     * of each point in the row by defining three arrays - height, left, and right.
     *
     * height[j] will correspond to the height of matrix[i][j], and so on and so forth with the other arrays.
     *
     * The question now becomes how to update each array.
     *
     * Height:
     * This one is easy. h is defined as the number of continuous ones in a line from our point.
     *      new_height[j] = old_height[j] + 1 if row[j] == '1' else 0
     *
     * Left:
     * Consider what causes changes to the left bound of our rectangle. Since all instances of zeros occurring
     * in the row above the current one have already been factored into the current version of left,
     * the only thing that affects our left is if we encounter a zero in our current row. As a res we can define:
     *      new_left[j] = max(old_left[j], cur_left)
     *
     *      cur_left is one greater than rightmost occurrence of zero we have encountered.
     *      When we "expand" the rectangle to the left, we know it can't expand past that point,
     *      otherwise it'll run into the zero.
     *
     * Right:
     * Here we can reuse our reasoning in left and define:
     *      new_right[j] = min(old_right[j], cur_right)
     *
     *      cur_right is the leftmost occurrence of zero we have encountered.
     *      For the sake of simplicity, we don't decrement cur_right by one (like how we increment cur_left)
     *      so we can compute the area of the rectangle with
     *      height[j] * (right[j] - left[j]) instead of height[j] * (right[j] + 1 - left[j]).
     *
     * This means that technically the base of the rectangle is defined by the half-open interval [l, r)
     * instead of the closed interval [l, r], and right is really one greater than right boundary.
     *
     * Although the algorithm will still work if we don't do this with right,
     * doing it this way makes the area calculation a little cleaner.
     *
     * Note that to keep track of our cur_right correctly, we must iterate from right to left,
     * so this is what is done when updating right.
     *
     * With our left, right, and height arrays appropriately updated,
     * all that there is left to do is compute the area of each rectangle.
     *
     * Since we know the bounds and height of rectangle j,
     * we can trivially compute it's area with height[j] * (right[j] - left[j]),
     * and change our max_area if we find that rectangle j's area is greater.
     *
     * Time complexity : O(NM). In each iteration over N we iterate over M a constant number of times.
     * Space complexity : O(M). M is the length of the additional arrays we keep.
     *
     */
    public int maximalRectangleDP(char[][] matrix) {

        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;

        int[] left = new int[n];
        int[] right = new int[n];
        int[] height = new int[n];

        Arrays.fill(right, n);
        int maxArea = 0;

        for(int i = 0; i < m; ++i) {

            for (int j = 0; j < n; ++j) {
                height[j] = matrix[i][j] == '1' ? height[j] + 1 : 0;
            }

            int curLeft = 0;
            for (int j = 0; j < n; ++j) {
                left[j] = matrix[i][j] == '1' ? Math.max(left[j], curLeft) : 0;
                curLeft = matrix[i][j] == '1' ? curLeft : j + 1;
            }

            int curRight = 0;
            for (int j = n-1; j >= 0; --j) {
                right[j] = matrix[i][j] == '1' ? Math.min(right[j], curRight) : n;
                curRight = matrix[i][j] == '1' ? curRight : j;
            }

            // update area
            for(int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, (right[j] - left[j]) * height[j]);
            }
        }

        return maxArea;
    }


    /**
     *
     * We can compute the maximum width of a rectangle that ends at a given coordinate in constant time.
     * We do this by keeping track of the number of consecutive ones each square in each row.
     *
     * As we iterate over each row we update the maximum possible width at that point.
     * This is done using row[i] = row[i - 1] + 1 if row[i] == '1'.
     *
     * Once we know the maximum widths for each point above a given point,
     * we can compute the maximum rectangle with the lower right corner at that point in linear time.
     *
     * As we iterate up the column,
     * we know that the maximal width of a rectangle spanning from the original point to the current point
     * is the running minimum of each maximal width we have encountered.
     *
     * We define:
     * maxWidth = min(maxWidth, widthHere)
     * curArea = maxWidth * (currentRow - originalRow + 1)
     * maxArea = max(maxArea, curArea)
     *
     * Repeating this process for every point in our input gives us the global maximum.
     */

    // O(N^2 * M)
    public int maximalRectanglePartialDP(char[][] matrix) {

        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if(matrix[i][j] == '1') {
                    if (j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = dp[i][j-1] + 1;
                    }
                }
            }
        }

        int maxArea = 0;
        for(int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                int curWidth = dp[i][j];

                // compute maximum area with lower right corner at [i, j]
                // already know width, iterate rows to get max height
                for(int k = i; k >= 0; --k) {
                    curWidth = Math.min(curWidth, dp[k][j]);
                    maxArea = Math.max(maxArea, curWidth * (i - k + 1));
                }
            }
        }

        return maxArea;
    }



    /*
    for each row in 0 to N - 1
        for each column in that row
            if matrix[row][column] == 1
                if i == 0: heights[row][column]  = 1
                else heights[row][column]  = 1 + heights[row-1][column]

        find area for that row and update maximum area so far (refer the LargestRectangleInHistogram problem
     */

    public int maximalRectangle(char[][] matrix) {

        int m = matrix.length;
        int n = m == 0 ? 0 : matrix[0].length;

        int[][] heights = new int[m][n+1];
        int maxArea = 0;

        for(int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {

                if(matrix[i][j] == '1') {
                    heights[i][j] = i == 0 ? 1 : 1 + heights[i-1][j];
                } else {
                    heights[i][j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestAreaInHistogram(heights[i]));
        }

        return maxArea;
    }


    public int largestAreaInHistogram(int[] heights) {

        Stack<Integer> stack = new Stack<>();
        int maxRect = 0;
        int i = 0;
        while(i < heights.length) {

            if(stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i);
                ++i;

            } else {

                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxRect = Math.max(maxRect, height * width);
            }
        }

        while (stack.isEmpty()) {
            int height = heights[stack.pop()];
            int width = stack.isEmpty() ? i : i - stack.peek() - 1;
            maxRect = Math.max(maxRect, height * width);
        }

        return maxRect;
    }


    public static void main(String[] args) {
        MaximalRectangle m = new MaximalRectangle();

//        System.out.println(m.maximalRectangle(new char[][]{{'0','0', '1'}, {'0','0','1'}}));


        System.out.println(m.maximalRectangleDP(new char[][]{{'0','0', '1'}, {'0','0','1'}}));

    }


}
