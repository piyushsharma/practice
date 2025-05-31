package com.dsandalgos.array;

import java.util.Arrays;

/**
 * Created by Piyush Sharma
 */

/*

Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
Return the sum of the three integers. You may assume that each input would have exactly one solution.

For example, given array S = {-1 2 1 -4}, and target = 1.
The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

*/


public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {

        // sort the array
        Arrays.sort(nums);

        Integer result = null;

        for(int i = 0; i < nums.length - 2; ++i) {
            // to avoid repeat calculations
            if (i == 0 || nums[i - 1] < nums[i]) {

                int start = i + 1;
                int end = nums.length - 1;

                while(start < end) {
                    int sum = nums[i] + nums[start] + nums[end];

                    if (result == null || Math.abs(target - sum) < Math.abs(target - result)) {
                        result = sum;
                    }

                    if(sum < target) {
                        ++start;
                    } else {
                        --end;
                    }
                }

            }
        }
        return result;
    }


    public static void main(String[] args) {
        ThreeSumClosest t = new ThreeSumClosest();

        System.out.println(t.threeSumClosest(new int[]{1,1,1,0}, -100)); // 2
        System.out.println(t.threeSumClosest(new int[]{1,1,1,1}, 0)); // 3
        System.out.println(t.threeSumClosest(new int[]{-1,-4,1,2}, 1)); // 2
        System.out.println(t.threeSumClosest(new int[]{0,2,1,-3}, 1)); // 0
        System.out.println(t.threeSumClosest(new int[]{0,0,0}, 1)); // 0

        int[] x = new int[]{13,2,0,-14,-20,19,8,-5,-13,-3,20,15,20,5,13,14,-17,-7,12,-6,0,20,-19,-1,-15,-2,8,-2,-9,
                13,0,-3,-18,-9,-9,-19,17,-14,-19,-4,-16,2,0,9,5,-7,-4,20,18,9,0,12,-1,10,-17,-11,16,-13,-14,-3,
                0,2,-18,2,8,20,-15,3,-13,-12,-2,-19,11,11,-10,1,1,-10,-2,12,0,17,-19,-7,8,-19,-17,5,-5,-10,8,0,
                -12,4,19,2,0,12,14,-9,15,7,0,-16,-5,16,-12,0,2,-16,14,18,12,13,5,0,5,6};
        System.out.println(t.threeSumClosest(x, -59));  // -58
    }

}
