package com.dsandalgos.array;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/*
Given an array and a value, remove all instances of that value in place and return the new length.
The order of elements can be changed. It doesn't matter what you leave beyond the new length.
*/

public class RemoveElement {

    public int removeElement(int[] nums, int val) {

        int j = 0;
        for(int i = 0; i < nums.length; ++i) {
            if(nums[i] == val) {
                continue;
            }
            nums[j] = nums[i];
            ++j;
        }

        return j;
    }

    public static void main(String[] args) {
        int[] x = {1,2,3,2,5,2,7};
        int temp = new RemoveElement().removeElement(x, 2);

        for(int i : x) {
            System.out.printf("%d, ", i);
        }
        System.out.println();
        System.out.println(temp);
    }

}
