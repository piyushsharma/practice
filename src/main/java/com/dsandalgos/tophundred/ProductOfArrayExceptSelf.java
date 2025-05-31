package com.dsandalgos.tophundred;

/**
 * Given an array nums of n integers where n > 1,
 * return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
 *
 * Example:
 *
 * Input:  [1,2,3,4]
 * Output: [24,12,8,6]
 * Note: Please solve it without division and in O(n).
 *
 * Follow up:
 * Could you solve it with constant space complexity?
 * (The output array does not count as extra space for the purpose of space complexity analysis.)
 */


public class ProductOfArrayExceptSelf {

	public int[] productExceptSelf(int[] nums) {

		int[] leftProduct = new int[nums.length];
		leftProduct[0] = nums[0];
		for(int i = 1; i < nums.length; ++i) {
			leftProduct[i] = leftProduct[i-1]*nums[i];
		}

		int[] rightProduct = new int[nums.length];
		rightProduct[nums.length - 1] = nums[nums.length - 1];
		for(int i = nums.length - 2; i >= 0; --i) {
			rightProduct[i] = rightProduct[i+1]*nums[i];
		}

		int[] result = new int[nums.length];
		for(int i = 0; i < nums.length; ++i) {
			int left = i > 0 ? leftProduct[i-1] : 1;
			int right = i < nums.length - 1 ? rightProduct[i+1] : 1;

			result[i] = left * right;
		}


		return result;
	}


	public int[] productExceptFollowUp(int[] nums) {

		int[] result = new int[nums.length];
		result[0] = nums[0];
		for(int i = 1; i < nums.length; ++i) {
			result[i] = result[i-1] * nums[i];
		}

		int right = 1;
		for(int i = nums.length - 1; i >= 0; --i) {
			int left = i > 0 ? result[i-1] : 1;
			result[i] = left * right;
			right = right * nums[i];
		}
		return result;
	}

}
