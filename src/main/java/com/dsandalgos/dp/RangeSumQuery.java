package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.

Example:
Given nums = [-2, 0, 3, -5, 2, -1]
sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3

Note:
You may assume that the array does not change.
There are many calls to sumRange function.

*/

public class RangeSumQuery {

    int[] sums;

    public RangeSumQuery(int[] nums) {
        this.sums = new int[nums.length + 1];
        for(int i = 0; i < nums.length; ++i) {
            sums[i+1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }

    public static void main(String[] args) {
        RangeSumQuery numArray = new RangeSumQuery(new int[]{1,2,3,4,5});
        System.out.println(numArray.sumRange(0, 1));
        System.out.println(numArray.sumRange(1, 2));
        System.out.println(numArray.sumRange(1, 2));
        System.out.println(numArray.sumRange(1, 2));
        System.out.println(numArray.sumRange(1, 2));
        System.out.println(numArray.sumRange(1, 2));
    }
}
