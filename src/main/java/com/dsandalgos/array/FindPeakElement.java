package com.dsandalgos.array;

/**
 * Created by Piyush Sharma
 */

/*
A peak element is an element that is greater than its neighbors.

Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that num[-1] = num[n] = -∞.

For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

*/


public class FindPeakElement {

    // O(n), We can do binary search to make it O(logn)

    public int findPeakElement(int[] nums) {
        if(nums.length == 1) return 0; // return index

        int prev = Integer.MIN_VALUE;

        for(int i = 0; i < nums.length; ++i) {
            int next;
            if(i == nums.length - 1) {
                next = Integer.MIN_VALUE;
            } else {
                next = nums[i + 1];
            }

            if(nums[i] > prev && nums[i] > next) return i;

            prev = nums[i];
        }
        // no peak found
        return -1;
    }

}
