package com.dsandalgos.binarysearch;

/**
 * Created by Piyush Sharma
 */

/*
Given a sorted array of integers, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].
*/

public class SearchRange {


    // Worst case O(logn)
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

    // Worst case O(n)
    public int[] searchRangeV2(int[] nums, int target) {
        int[] result = new int[]{-1, -1};

        int r = nums.length - 1;
        int l = 0;

        while (l <= r) {
            int mid = l + (r - l)/2;

            if(nums[mid] < target) {
                l = mid + 1;
            } else if (nums[mid] > target) {
                r = mid - 1;
            } else {
                int temp = mid;
                while(mid >= 0 && nums[mid] == target) {
                    --mid;
                }
                result[0] = mid + 1;
                mid = temp;
                while(mid < nums.length && nums[mid] == target) {
                    ++mid;
                }
                result[1] = mid - 1;
                break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] t = new int[]{5,7,7,8,8,10};

        int[] result = new SearchRange().searchRange(t, 8);

        System.out.printf("%d %d", result[0], result[1]);
    }
}
