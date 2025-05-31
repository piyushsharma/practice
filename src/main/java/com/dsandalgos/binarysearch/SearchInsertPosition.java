package com.dsandalgos.binarysearch;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a sorted array and a target value, return the index if the target is found.
If not, return the index where it would be if it were inserted in order.
You may assume no duplicates in the array.
Here are few examples.
[1,3,5,6], 5 → 2
[1,3,5,6], 2 → 1
[1,3,5,6], 7 → 4
[1,3,5,6], 0 → 0
*/

public class SearchInsertPosition {


    /*
    When the while loop ends, low must be equal to high and it is a valid index.
    Obviously, if A[low] is equal to target, we return low.
    If A[low] is greater than target, that means we are inserting target before A[low], so we return low.
    If A[low] is less than target, that means we insert target after A[low], so we return low + 1.
    */
    public int searchInsert(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while(low < high) {
            int mid = low  + (high - low)/2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid; // so that if target == nums[mid], high becomes mid, and eventually low == high
            }
        }
        return nums[low] < target ? low + 1 : low; // since low = mid + 1, which is where we will want to add target
    }

    public int searchInsertV2(int[] nums, int target) {
        int low = 0;
        if(nums.length == 0 || target <= nums[low])
            return 0;

        int high = nums.length - 1;
        if(target == nums[high])
            return high;
        else if(target > nums[high])
            return high + 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(target == nums[mid])
                return mid;

            if(target > nums[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        SearchInsertPosition s = new SearchInsertPosition();

        int[] array = {1,3,5,7,9};
        System.out.println(s.searchInsert(array, 10));

        array = new int[]{1,3,5,6};
        System.out.println(s.searchInsert(array, 5));

        array = new int[]{1,3,5,6};
        System.out.println(s.searchInsert(array, 2));

        array = new int[]{1,3,5,6};
        System.out.println(s.searchInsert(array, 7));

        array = new int[]{1,3,5,6};
        System.out.println(s.searchInsert(array, 0));

//        , 5 → 2
//                [1,3,5,6], 2 → 1
//                [1,3,5,6], 7 → 4
//                [1,3,5,6], 0 → 0
    }

}
