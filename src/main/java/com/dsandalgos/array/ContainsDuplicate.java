package com.dsandalgos.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an array of integers, find if the array contains any duplicates.
Your function should return true if any value appears at least twice in the array,
and it should return false if every element is distinct.
 */

public class ContainsDuplicate {

    public boolean containsDuplicateA(int[] nums) {
        Set<Integer> h = new HashSet<Integer>();

        for (int i = 0; i < nums.length; ++i) {
            if(h.contains(nums[i])) {
                return true;
            }
            h.add(nums[i]);
        }

        return false;
    }


    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; ++i) {
            if(nums[i-1] == nums[i]) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] a = new int[] {2,3,2};
        System.out.println(new ContainsDuplicate().containsDuplicate(a));
    }
}
