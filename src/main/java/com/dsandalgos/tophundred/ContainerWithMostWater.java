package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */

/*
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai).
n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0).
Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
*/


public class ContainerWithMostWater {

    // O(n)
    public int maxArea(int[] height) {
        int n = height.length;

        // The intuition behind this approach is that the area formed between the
        // lines will always be limited by the height of the shorter line.
        // Further, the farther the lines, the more will be the area obtained.

        // We take two pointers, one at the beginning and
        // one at the end of the array constituting the length of the lines.
        // Further, we maintain a variable maxarea to store the maximum area obtained till now.
        // At every step, we find out the area formed between them,
        // update maxarea  and move the pointer pointing to the shorter line towards the other end by one step.

        int maxArea = 0;
        int left = 0;
        int right = n - 1;
        while (left < right) {

            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = area > maxArea ? area : maxArea;

            if(height[left] < height[right]) {
                ++left;
            } else {
                --right;
            }
        }

        return maxArea;
    }

    // brute force O(n2)
    public int maxAreaBruteForce(int[] height) {
        int n = height.length;

        int maxArea = 0;
        for(int i = 0; i < n; ++i) {
            for(int j = i + 1; j < n; ++j) {
                int area = Math.min(height[i], height[j]) * (j - i);
                maxArea = area > maxArea ? area : maxArea;
            }
        }
        return maxArea;
    }

}
