package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
the contiguous subarray [4,-1,2,1] has the largest sum = 6.

 */

public class MaximumSubArray {

    public int maxSubArrayKadane(int[] nums) {
        int maxEndingHere = 0;
        int maxSoFar = Integer.MIN_VALUE;

        int start = 0;
        int end = 0;
        int curS = 0;

        for(int i = 0; i < nums.length; ++i) {

            maxEndingHere += nums[i];

            if(maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                start = curS;
                end = i;
            }

            if(maxEndingHere < 0) {
                maxEndingHere = 0;
                curS = i + 1;
            }
        }

        return maxSoFar;
    }


    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0)
            return -1;

        int maxEndingHere = nums[0];
        int maxTotal = nums[0];

        for(int i = 1; i < nums.length; ++i) {
            maxEndingHere = Math.max(nums[i], maxEndingHere + nums[i]);

            // update total max if maxEndingHere is bigger than maxTotal
            maxTotal = Math.max(maxEndingHere, maxTotal);
        }
        return maxTotal;
    }


    public int maxSubArrayV2(int[] nums) {
        if(nums == null || nums.length == 0)
            return -1;

        int curSum = nums[0];
        int maxSum = nums[0];

        for(int i = 1; i < nums.length; ++i) {

            if(curSum < 0) {
                if(nums[i] > 0) {
                    curSum = nums[i];
                } else {
                    curSum = Math.max(curSum, nums[i]);
                }
            } else {
                curSum += nums[i];
            }
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
