package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right.
 *
 * You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * Return the max sliding window.
 *
 * Example:
 *
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 *
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.
 */


public class SlidingWindowMaximum {

    public int[] maxSlidingWindowDP(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k == 1) {
            return nums;
        }

        int n = nums.length;

        int[] left = new int[n];
        for(int i = 0; i < n; ++i) {
            if((i % k) == 0) {
                left[i] = nums[i];
            } else {
                left[i] = Math.max(left[i-1], nums[i]);
            }
        }

        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for(int i = n - 2; i >= 0; --i) {
            if(((i + 1) % k) == 0) {
                right[i] = nums[i];
            } else {
                right[i] = Math.max(right[i+1], nums[i]);
            }
        }

        int[] result = new int[n - k + 1];
        for(int i = 0; i < n - k + 1; ++i) {
            result[i] = Math.max(right[i], left[i + k - 1]);
        }

        return result;
    }

    public int[] maxSlidingWindow(int[] nums, int k) {

        if(nums == null || nums.length == 0 || k == 1) {
            return nums;
        }

        int[] result = new int[nums.length - k + 1];
        int resultIndex = 0;

        int left = 0;
        int right = k - 1;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a1, a2) -> a2 - a1);

        int i = 0;
        while(i < nums.length && right < nums.length) {

            while(i >= left && i <= right) {
                pq.add(nums[i]);
                ++i;
            }
            result[resultIndex] = pq.peek();
            ++resultIndex;

            pq.remove(nums[left]);
            ++left;
            ++right;
        }
        return result;
    }

    public static void main(String[] args) {

        int[] test = new int[]{1,3,-1,-3,5,3,6,7};

        int[] result = new SlidingWindowMaximum().maxSlidingWindowDP(test, 3);

        for(int r : result) {
            System.out.println(r);
        }

    }

}
