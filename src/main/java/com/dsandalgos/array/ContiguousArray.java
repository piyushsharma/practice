package com.dsandalgos.array;

import java.util.HashMap;
import java.util.Map;


/**
 * Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.
 *
 * Example 1:
 * Input: [0,1]
 * Output: 2
 * Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.
 * Example 2:
 * Input: [0,1,0]
 * Output: 2
 * Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.
 * Note: The length of the given binary array will not exceed 50,000.
 *
 */

public class ContiguousArray {

	public int findMaxLength(int[] nums) {

		if(nums == null || nums.length == 0) {
			return 0;
		}

		int result = 0;
		int counter = 0;

		Map<Integer, Integer> hM = new HashMap<Integer, Integer>();
		// set -1 where we see 0
		for (int i = 0; i < nums.length; ++i) {
			nums[i] = (nums[i] == 0) ? -1 : 1;
		}

		for (int i = 0; i < nums.length; i++) {
			counter += nums[i];

			// every time we reach counter 0, we have reached the current max,
			// since the sum from i = 0 to current i = 0, so this is the longest contiguous array so far
			if (counter == 0) {
				result = i + 1;
			}

			// If this counter is seen before, then update result if required
			if (hM.containsKey(counter)) {
				result = Math.max(result, i - hM.get(counter));
			} else {
				hM.put(counter, i);
			}
		}

		// Brute Force : O(n2)
//         for(int i = 0; i < nums.length; ++i) {
//             counter = nums[i] == 0 ? -1 : 1;
//             for(int j = i+1; j < nums.length; ++j) {
//                 if(nums[j] == 0) {
//                     counter -= 1;
//                 } else {
//                     counter += 1;
//                 }

//                 if(counter == 0) {
//                     result = Math.max(result, j - i + 1);
//                 }
//             }
//         }


		return result;
	}
}
