package com.dsandalgos.array;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a sorted array,
remove the duplicates in place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.
For example,
    Given input array nums = [1,1,2],
    Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
    It doesn't matter what you leave beyond the new length.
*/

public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        while(j < nums.length) {
            if(nums[i] != nums[j]) {
                ++i;
                nums[i] = nums[j];
            }
            ++j;
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] x = new int[4];
        x[0] = 1;
        x[1] = 1;
        x[2] = 2;
        x[3] = 2;

        new RemoveDuplicatesFromSortedArray().removeDuplicates(x);

    }

}
