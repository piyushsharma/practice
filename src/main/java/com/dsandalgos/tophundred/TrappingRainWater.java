package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma on 2/20/15.
 */

/*
Problem Statement:
Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it is able to trap after raining.

For example,
Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

Image at => https://oj.leetcode.com/problems/trapping-rain-water/
*/

public class TrappingRainWater {
    public static int trap(int[] height) {
        int size = height.length;
        if (size < 2) {
            return 0;
        }
        int width = 1;

        int[] maxLeft = new int[size];
        int[] maxRight = new int[size];

        /* For the first bar, setting max left as height[0] */
        int max = height[0];
        for (int i = 0; i < size; ++i) {
            if (height[i] > max) {
                max = height[i];
            }
            maxLeft[i] = max;
        }

        /* For the last bar, setting max right as height[size - 1] */
        max = height[size - 1];
        for (int i = size - 1; i >= 0; --i) {
            if (height[i] > max) {
                max = height[i];
            }
            maxRight[i] = max;
        }

        int ret = 0;
        for (int i = 1; i < size - 1; i++) {
            int trap = (Math.min(maxLeft[i], maxRight[i]) - height[i]) * width;
            ret += trap;
        }
        return ret;
    }

    private static void printArr(int[] A) {
        for (int i = 0; i < A.length; ++i) {
            System.out.format("A[%d] = %d ", i, A[i]);
        }
        System.out.format("\n");
    }


    public static void main (String args[]) {
//        int[] test = {2,3,4,0,5,6,7,0,1};
//        int [] test = {0,1,0,2,1,0,1,3,2,1,2,1};
        int [] test = {2,0,2};
        System.out.println(trap(test));
    }
}
