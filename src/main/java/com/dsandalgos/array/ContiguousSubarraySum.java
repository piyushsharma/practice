package com.dsandalgos.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
 *
 * A good subarray is a subarray where:
 *
 * its length is at least two, and
 * the sum of the elements of the subarray is a multiple of k.
 * Note that:
 *
 * A subarray is a contiguous part of the array.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 */


public class ContiguousSubarraySum {

    // sum of subarray starting at i + 1 and ending at j => prefix[j] - prefix[i];
    // sum of this subarray is divisible by k if (prefix[j] - prefix[i]) % k = 0;
    // i.e. prefix[j] % k = prefix[i] % k
    // find the two points where prefix[i] % k and prefix[j] % k are same and calculate the distance b/w them

    public boolean checkSubarraySum(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int prefixMod = 0;

        for(int i = 0; i < nums.length; i++){
            prefixMod = (prefixMod + nums[i]) % k;
            if(map.containsKey(prefixMod)) {
               if(i - map.get(prefixMod) > 1){
                   return true;
               }
            } else {
                map.put(prefixMod, i);
            }
        }

        return false;
    }



}
