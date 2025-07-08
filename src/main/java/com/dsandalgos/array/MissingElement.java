package com.dsandalgos.array;

public class MissingElement {

    public int missingElement(int[] nums, int k) {
        int n = nums.length;

        for(int i = 1; i < n; ++i) {
            int missedInGap = nums[i] - nums[i - 1] - 1;
            if(missedInGap >= k) {
                return nums[i - 1] + k;
            }
            k -= missedInGap;
        }

        return nums[n-1] + k;
    }


    public int missingElementV2(int[] nums, int k) {
        if(nums.length == 0) return k;

        int prev = nums[0];
        int i = 1;
        while(k > 0 && i < nums.length) {

            int next = nums[i];
            if(prev + k < next) {
                return prev + k;
            }
            k -= (next - prev - 1);

            ++i;
            prev = next;
        }

        return prev + k;

    }
}
