package com.dsandalgos.tophundred;

/**
 * Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
 *
 * Your algorithm's runtime complexity must be in the order of O(log n).
 *
 * If the target is not found in the array, return [-1, -1].
 *
 * Example 1:
 *
 * Input: nums = [5,7,7,8,8,10], target = 8
 * Output: [3,4]
 * Example 2:
 *
 * Input: nums = [5,7,7,8,8,10], target = 6
 * Output: [-1,-1]
 */

public class FindFirstAndLastElementInSortedArray {


    public int[] searchRange(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        int l = 0;
        int r = nums.length - 1;

        // first only find the low range
        while (l <= r && r >= 0) {
            int mid = l + (r - l)/2;

            if(nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                result[0] = mid;
                r = mid - 1;
            }
        }

        l = 0;
        r = nums.length - 1;

        // now find the high range
        while (l <= r) {
            int mid = l + (r - l)/2;

            if(nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                result[1] = mid;
                l = mid + 1;
            }
        }

        return result;
    }

}
