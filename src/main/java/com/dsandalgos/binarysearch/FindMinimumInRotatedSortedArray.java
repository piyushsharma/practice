package com.dsandalgos.binarysearch;

/**
 * Created by Piyush Sharma
 */

/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

Find the minimum element.
You may assume no duplicate exists in the array.
*/


public class FindMinimumInRotatedSortedArray {

    // given that no duplicates
    public int findMinIterate(int[] nums) {

        int l = 0;
        int r = nums.length - 1;

        // if A[L] < A[R], no rotation, so return A[L] immediately
        // because if we rotated even once, A[L] > A[R] for sure
        // In that case, lets break the array at mid
        // We already assumed A[L] > A[R], now =>
        // if A[mid] > A[R]; A[L]...A[mid], all are greater than A[R], so the minimum must be in A[mid + 1].. A[R]
        // else, A[mid] <= A[R], the minimum must be in A[L] to A[mid]

        while(l < r && nums[l] >= nums[r]) {
            int mid = l + (r - l)/2;

            if(nums[mid] > nums[r]) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return nums[l];
    }

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int low, int high) {
        // no rotation left
        if(nums[low] <= nums[high]) {
            return nums[low];
        }

        int mid = low + (high - low)/2;

        // if mid is > high, nums[low] ... nums[mid] must all be > high,
        if(nums[mid] > nums[high]) {
            return findMin(nums, mid+1, high);
        } else {
            return findMin(nums, low, mid);
        }
    }

    public static void main(String[] args) {
        int[] test = new int[]{4, 5, 6, 7, 0, 1, 2};
        int[] test1 = new int[]{2,1};

        System.out.println(new FindMinimumInRotatedSortedArray().findMin(test1));

    }

}
