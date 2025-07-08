package com.dsandalgos.array;

public class MonotonicArray {

    public boolean isMonotonic(int[] A) {
        boolean increasing = true;
        boolean decreasing = true;
        for (int i = 0; i < A.length - 1; ++i) {
            if (A[i] > A[i+1])
                increasing = false;
            if (A[i] < A[i+1])
                decreasing = false;
        }

        return increasing || decreasing;
    }

    public boolean isMonotonicV2(int[] nums) {
        if(nums.length <= 1) {
            return true;
        }

        int count = 0;
        for(int i = 1; i < nums.length; ++i) {
            if(nums[i] >= nums[i - 1]) {
                ++count;
            }
        }
        if(count == nums.length - 1) return true;

        count = 0;
        for(int i = nums.length - 2; i >=0; --i) {
            if(nums[i] >= nums[i + 1]) {
                ++count;
            }
        }
        if(count == nums.length - 1) return true;

        return false;
    }
}
