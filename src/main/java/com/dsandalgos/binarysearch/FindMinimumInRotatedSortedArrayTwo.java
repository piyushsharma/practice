package com.dsandalgos.binarysearch;

/**
 * Created by Piyush Sharma
 */

/*
Follow up for "Find Minimum in Rotated Sorted Array":
What if duplicates are allowed?

Would this affect the run-time complexity? How and why?
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.

The array may contain duplicates.
*/


public class FindMinimumInRotatedSortedArrayTwo {

    // duplicates are allowed
    // worst case => O(n)
    public int findMin(int[] nums) {

        int l = 0;
        int r = nums.length - 1;

        // if A[L] < A[R], no rotation, so return A[L] immediately
        // because if we rotated even once, A[L] >= A[R] for sure
        // In that case, lets break the array at mid
        // We already assumed A[L] > A[R], now =>
        // if A[mid] > A[R]; A[L]...A[mid], all are greater than A[R], so the minimum must be in A[mid + 1].. A[R]
        // else, A[mid] < A[R], the minimum must be in A[L] to A[mid]
        // but when A[mid] == A[L] == A[R], we cannot be sure which side to go
        // [1, 1, 1, 0, 1] or [1, 0, 1, 1, 1]). In this case, we could not discard either subarrays
        // Worst Case O(n)

        while(l < r && nums[l] >= nums[r]) {
            int mid = l + (r - l)/2;

            if(nums[mid] > nums[r]) {
                l = mid + 1;
            } else if (nums[mid] < nums[r]) {
                r = mid;
            } else {
                l = l + 1; // i.e. nums[r] == nums[mid], so not sure where to go
            }
        }

        return nums[l];
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArrayTwo f  = new FindMinimumInRotatedSortedArrayTwo();

        int[] A = new int[]{1, 0, 1, 1, 1};
        System.out.println(f.findMin(A));

    }

}
