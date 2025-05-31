package com.dsandalgos.tophundred;
/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
You are a professional robber planning to rob houses along a street.
Each house has a certain amount of money stashed,
the only constraint stopping you from robbing each of them is that adjacent houses
have security system connected and it will automatically contact the police
if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house,
determine the maximum amount of money you can rob tonight without alerting the police.
*/


public class HouseRobber {

    // Cached (DP solution II)
    public int rob(int[] nums) {
        if(nums.length < 2) {
            return nums.length == 0 ? 0 : nums[0];
        }

        int[] cache = new int[nums.length];
        cache[0] = nums[0];
        cache[1] = Math.max(nums[0], nums[1]);

        for(int i = 2; i < nums.length; ++i) {
            cache[i] = Math.max(cache[i-1], cache[i-2] + nums[i]);
        }

        return cache[nums.length - 1];
    }


    // Cached (DP solution I)
    public int robDPOne(int[] nums) {
        int[] cache = new int[nums.length];
        for(int i = 0; i < cache.length; ++i) {
            cache[i] = -1;
        }
        return rob(nums, 0, nums.length-1, cache);
    }

    private int rob(int[] nums, int current, int end, int[] cache) {
        if(current > end)
            return 0;

        // include current house
        if (cache[current] == -1) {
            cache[current] = nums[current] + rob(nums, current + 2, end, cache);
        }

        int excludeCurrentHouse = 0;
        if(current + 1 <= end) {
            if (cache[current + 1] == -1) {
                cache[current + 1] = nums[current + 1] + rob(nums, current + 3, end, cache);
                excludeCurrentHouse = cache[current + 1];
            } else {
                excludeCurrentHouse = cache[current + 1];
            }
        }
        return Math.max(cache[current], excludeCurrentHouse);
    }


    // Recursion
    public int robRecursion(int[] nums) {
        return rob(nums, 0, nums.length - 1);
    }

    private int rob(int[] nums, int start, int end) {
        if(start > end)
            return 0;

        int one = nums[start] + rob(nums, start + 2, end);
        int two = 0;
        if(start + 1 <= end) {
            two = nums[start + 1] + rob(nums, start + 3, end);
        }
        return Math.max(one, two);
    }

    public static void main(String[] args) {
        HouseRobber h = new HouseRobber();
        int[] nums = new int[]{10,20,5,100,200,10};
        System.out.println(h.rob(nums));
        System.out.println(h.robRecursion(nums));
    }
}
