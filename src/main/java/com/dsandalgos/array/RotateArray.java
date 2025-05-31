package com.dsandalgos.array;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Rotate an array of n elements to the right by k steps.
For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].

Note:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
*/

public class RotateArray {

    /*
        The basic idea is that, for example, nums = [1,2,3,4,5,6,7] and k = 3,
        first we reverse [1,2,3,4], it becomes[4,3,2,1]; full array => [4,3,2,1,5,6,7]
        then we reverse[5,6,7], it becomes[7,6,5]; full array => [4,3,2,1,7,6,5]
        finally we reverse the array as a whole, from [4,3,2,1,7,6,5] => [5,6,7,1,2,3,4].
    */
    public void rotate(int[] nums, int k) {
        if(nums.length <= 1)
            return;

        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while(start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            ++start;
            --end;
        }
    }


    public void rotateOne(int[] nums, int k) {
        if(nums.length <= 1)
            return;

        k = k % nums.length;
        int[] temp = new int[nums.length];
        int j = 0;
        for(int i = nums.length - k; i < nums.length; ++i) {
            temp[j] = nums[i];
            ++j;
        }
        for(int i = 0; i < nums.length - k; ++i) {
            temp[j] = nums[i];
            ++j;
        }

        for(int i = 0; i < nums.length; ++i) {
            nums[i] = temp[i];
        }
    }

    public static void main(String[] args) {
        int[] x = {1,2,3,4,5,6,7};
        new RotateArray().rotate(x, 3);

        for(int i : x) {
            System.out.printf("%d, ", i);
        }
    }

}
