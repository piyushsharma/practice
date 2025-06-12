package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an array of n positive integers and a positive integer s,
find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.

For example, given the array [2,3,1,2,4,3] and s = 7,
the subarray [4,3] has the minimal length under the problem constraint.

click to show more practice.

More practice:
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */


public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int s, int[] nums) {
        int minLength = 0;
        int sum = 0;
        int start = 0;
        int end = 0;

        while(end < nums.length) {
            while(sum < s && end < nums.length) {
                sum += nums[end];
                ++end;
            }
            while(sum >= s && start < nums.length) {
                sum -= nums[start];
                ++start;
            }
            // both end and start are move one more time than we need to make sum
            if(minLength == 0 || (end - start + 1) < minLength) {
                System.out.printf("%d, %d\n", start, end);
                minLength = end - start + 1;
            }
        }
        if (minLength > nums.length)
            return 0;
        return minLength;
    }

    // O(n^2) solution
    public int minSubArrayLenA(int s, int[] nums) {
        int minLength = nums.length;
        int sum, j;
        for(int i = 0; i < nums.length; ++i) {
            sum = 0;
            j = i;
            while(j < nums.length) {
                sum += nums[j];
                if (sum >= s) break;
                ++j;
            }
            if(sum >= s && (j - i + 1) < minLength) {
                minLength = j - i + 1;
            }
        }
        return minLength;
    }

    public static void main(String[] args) {
        int[] a = new int[]{2, 3, 1, 2, 4, 3, 2};
        MinimumSizeSubarraySum m = new MinimumSizeSubarraySum();
        System.out.println(m.minSubArrayLen(7, a));
    }

}
