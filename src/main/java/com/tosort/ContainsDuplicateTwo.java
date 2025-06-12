package com.tosort;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an array of integers and an integer k,
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j]
and the difference between i and j is at most k.
*/

public class ContainsDuplicateTwo {

    public boolean containsNearbyDuplicate(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i = 0; i < nums.length; ++i) {

            Integer val = map.get(nums[i]);
            if(val != null && i - val <= k)  {
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsDuplicateTwo c = new ContainsDuplicateTwo();
        int[] test = {-1, -1};
        c.containsNearbyDuplicate(test, 1);
    }

}
