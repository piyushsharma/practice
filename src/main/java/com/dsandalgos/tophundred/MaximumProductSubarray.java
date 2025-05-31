package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */

/*
Find the contiguous subarray within an array (containing at least one number) which has the largest product.

For example, given the array [2,3,-2,4],
the contiguous subarray [2,3] has the largest product = 6.
 */

public class MaximumProductSubarray {

    // for maximum sum subarray f(n) = max(nums[n], f(n-1) + nums[n])

    // but here, since multiplying two negatives becomes a positive
    // we also maintain the smallest contiguous product because if that is negative, it might multiply with another
    // negative to make the largest product. So, we have
    // f(n) = max(nums[n], f(n-1) * nums[n], g(n-1) * nums[n])
    // g(n) = min(nums[n], f(n-1) * nums[n], g(n-1) * nums[n])

    public int maxProduct(int[] nums) {
        int maxProductEndingHere = nums[0];
        int minProductEndingHere = nums[0];
        int maxProduct = nums[0];

        for(int i = 1; i < nums.length; ++i) {
            int mx = maxProductEndingHere;

            maxProductEndingHere = Math.max(nums[i]*maxProductEndingHere, Math.max(nums[i],
                    minProductEndingHere*nums[i]));

            minProductEndingHere = Math.min(nums[i]*mx, Math.min(nums[i],
                    minProductEndingHere*nums[i]));

            maxProduct = Math.max(maxProductEndingHere, maxProduct);
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        MaximumProductSubarray m = new MaximumProductSubarray();
        int[] nums = new int[]{2,3,-2,-2,-4};
        System.out.println(m.maxProduct(nums));
    }


}
