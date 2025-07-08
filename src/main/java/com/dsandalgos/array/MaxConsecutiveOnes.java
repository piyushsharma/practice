package com.dsandalgos.array;

public class MaxConsecutiveOnes {



    public int longestOnes(int[] nums, int k) {

        int left = 0;
        int curZeroes = 0;
        int result = 0;


        for (int right = 0; right < nums.length; right++) {

            int data = nums[right];
            if(data == 0) {
                ++curZeroes;
            }

            if(curZeroes > k) {
                if(nums[left] == 0) {
                    --curZeroes;
                }
                ++left;
            }

            result = Math.max(result, right - left + 1);
        }

        return result;

    }


}
