package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */

/*

Given an unsorted array of integers, find the length of longest increasing subsequence.

For example,
Given [10, 9, 2, 5, 3, 7, 101, 18],
The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
Note that there may be more than one LIS combination, it is only necessary for you to return the length.

Your algorithm should run in O(n^2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
*/


public class LongestIncreasingSubsequence {


    // DP solution

    /*
        Let nums[0..n-1] be the input array and L(i) be the length of the LIS till index i
        such that nums[i] is part of LIS and nums[i] is the last element in LIS,

        then L(i) can be recursively written as:
            L(i) = { 1 + Max ( L(j) ) } where j < i and nums[j] < nums[i] and if there is no such j then L(i) = 1

        To get LIS of a given array, we need to return max(L(i)) where 0 < i < n
    */
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n < 2) return n;

        int[] lisDPCache = new int[n];
        // initialize each element as 1, since lisDPCache would be at least 1 (just that element)
        for(int i = 0; i < n; ++i) lisDPCache[i] = 1;

        for(int i = 1; i < n; ++i) {

            // check how many elements are less than i, before i
            // L(i) = 1 + Max( L(j) ) where j < i && nums[j] < nums[i]
            // so keep updating max of L(j) for all j < i && nums[j] < nums[i]
            for(int j = 0; j < i; ++j) {
                if(nums[j] < nums[i]) {
                    lisDPCache[i] = Math.max(lisDPCache[i], lisDPCache[j] + 1);
                }
            }
        }

        int result = lisDPCache[0];
        for(int lisValue : lisDPCache) {
            if(result < lisValue) result = lisValue;
        }
        return result;
    }


    // recursive solution, look at all possibilities
    // complexity => O(2 ^ n)
    public int lengthOfLISRecursive(int[] nums) {
        int n = nums.length;
        // need at least 2 elements
        if(n < 2) return n;

        return lengthOfLIS(nums, 1, nums[0], 1);
    }

    private int lengthOfLIS(int[] nums, int nextIndex, int prevValue, int count) {

        if(nextIndex >= nums.length) return count;

        // include element at nextIndex in the subsequence
        if(prevValue < nums[nextIndex]) {

            // we consider both including the element at nextIndex in the solution
            // or excluding it
            int includeCount = lengthOfLIS(nums, nextIndex + 1, nums[nextIndex], count + 1);
            int excludeCount = lengthOfLIS(nums, nextIndex + 1, prevValue, count);

            return Math.max(includeCount, excludeCount);
        }

        // prevValue >= nums[nextIndex], since prevValue is greater, two possibilities =>
        // either we start a new subsequence starting at nextIndex
        // or we skip this element completely
        int skipCurElement = lengthOfLIS(nums, nextIndex + 1, prevValue, count);
        int startNewSubsequence = lengthOfLIS(nums, nextIndex + 1, nums[nextIndex], 1);

        return Math.max(skipCurElement, startNewSubsequence);
    }


    public static void main(String[] args) {
        LongestIncreasingSubsequence l = new LongestIncreasingSubsequence();

//        int[] test = new int[]{10,9,2,5,3,7,101,18};
        int[] test = new int[]{1,3,6,7,9,4,10,5,6};

        System.out.println(l.lengthOfLISRecursive(test));
        System.out.println(l.lengthOfLIS(test));
    }

}
