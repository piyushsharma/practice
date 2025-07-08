package com.dsandalgos.math;

public class MaximumSwap {

    public int maximumSwap(int num) {
        // Convert num to string
        char[] s = Integer.toString(num).toCharArray();

        for (int i = 0; i < s.length; i++) {

            // Find max possible number to swap with
            int max = -1;
            for (int j = s.length - 1; j > i; j--) {
                if (s[j] > s[i]) {
                    max = (max == -1 || s[max] < s[j]) ? j : max;
                }
            }

            // If we found a max
            if (max != -1) {
                // Swap and return the result
                char temp = s[i];
                s[i] = s[max];
                s[max] = temp;
                return Integer.parseInt(new String(s));
            }
        }

        return num;
    }

    public int maximumSwapV2(int num) {
        int maxIndex = -1;
        int swap1 = -1;
        int swap2 = -1;
        char[] nums = Integer.toString(num).toCharArray();
        int n = nums.length;

        for(int i = n-1; i >= 0; --i) {
            if(maxIndex == -1 || nums[i] > nums[maxIndex]) {
                maxIndex = i;
            } else if(nums[i] < nums[maxIndex]) {
                swap1 = i;
                swap2 = maxIndex;
            }
        }
        if(swap1 != -1 && swap2 != -1) {
            char temp = nums[swap1];
            nums[swap1] = nums[swap2];
            nums[swap2] = temp;
        }
        return Integer.parseInt(new String(nums)) ;
    }
}
