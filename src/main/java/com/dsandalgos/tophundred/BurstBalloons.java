package com.dsandalgos.tophundred;

/**
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums.
 * You are asked to burst all the balloons.
 * If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins.
 * Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.
 *
 * Find the maximum coins you can collect by bursting the balloons wisely.
 *
 * Note:
 *
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * Example:
 *
 * Input: [3,1,5,8]
 * Output: 167
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 *              coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 */


public class BurstBalloons {

    public int maxCoins(int[] nums) {


        return maxCoins(nums, 0, nums.length - 1);
    }

    private int maxCoins(int[] nums, int index, int end) {
        if(index > end) {
            return 0;
        }

        int value = nums[index];

        // burst balloon at index
        int coinsAtIndex = (index - 1 < 0 ? 1 : nums[index - 1]) * value * ((index + 1) > end ? 1 : nums[index + 1]);


        // consumed = set it to 1 so that it cannot be consumed again
        nums[index] = 1;
        int collectedC = coinsAtIndex + maxCoins(nums, index + 1, end);


        // don't burst balloon at index
        nums[index] = value;
        int collectedE = maxCoins(nums, index + 1, end);

        return Math.max(collectedC, collectedE);
    }

    public static void main(String[] args) {

        int x = new BurstBalloons().maxCoins(new int[]{3,1,5,8});
        System.out.println(x);

    }
}
