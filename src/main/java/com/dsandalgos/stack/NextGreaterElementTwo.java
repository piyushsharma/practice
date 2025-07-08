package com.dsandalgos.stack;

import java.util.Stack;

public class NextGreaterElementTwo {

    // O(N)
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int n = nums.length;
        int[] result = new int[n];

        for(int i = n-1; i >= 0; --i) {
            stack.push(nums[i]);
        }

        for(int i = n-1; i >= 0; --i) {
            while(!stack.isEmpty() && stack.peek() <= nums[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return result;
    }


    // BF Elegant => O(n^2)
    public int[] nextGreaterElementsBFElegant(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i < n; ++i) {
            result[i] = -1;
            for (int j = 1; j < n; ++j) {
                if (nums[(i + j) % n] > nums[i]) {
                    result[i] = nums[(i + j) % n];
                    break;
                }
            }
        }
        return result;
    }

    // Brute Force O(n^2)
    public int[] nextGreaterElementsBF(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        for(int i = 0; i < n; ++i) {

            int val = nums[i];
            boolean found = false;

            for(int j = i + 1; j < n; ++j) {
                if(nums[j] > val) {
                    result[i] = nums[j];
                    found = true;
                    break;
                }
            }

            if(!found) {
                for(int k = 0; k < i; ++k) {
                    if(nums[k] > val) {
                        result[i] = nums[k];
                        found = true;
                        break;
                    }
                }
            }
            if(!found) result[i] = -1;
        }
        return result;
    }
}
