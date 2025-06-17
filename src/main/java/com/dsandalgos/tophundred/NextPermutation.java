package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */

/*
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place, do not allocate extra memory.

Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

*/


public class NextPermutation {

    /**
     * First, we observe that for any given sequence that is in descending order,
     * no next larger permutation is possible. For example, no next permutation is possible for the following array:
     * [9, 5, 4, 3, 1]
     * We need to find the first pair of two successive numbers a[i] and a[i-1], from the right,
     * which satisfy a[i] > a[i-1]. Now, no rearrangements to the right of a[i-1] can create a larger permutation
     * since that subarray consists of numbers in descending order.
     *
     * Thus, we need to rearrange the numbers to the right of a[i-1]a[i−1] including itself.
     *
     * Now, what kind of rearrangement will produce the next larger number?
     * We want to create the permutation just larger than the current one.
     * Therefore, we need to replace the number a[i-1] with the number which is just larger than itself
     * among the numbers lying to its right section, say a[j].
     *
     * We swap the numbers a[i-1] and a[j]. We now have the correct number at index i-1.
     * But still the current permutation isn't the permutation that we are looking for.
     * We need the smallest permutation that can be formed by using the numbers only to the right of a[i-1].
     * Therefore, we need to place those numbers in ascending order to get their smallest permutation.
     *
     * But, recall that while scanning the numbers from the right,
     * we simply kept decrementing the index until we found the pair a[i] and a[i-1] where, a[i] > a[i-1].
     * Thus, all numbers to the right of a[i-1] were already sorted in descending order.
     * Furthermore, swapping a[i-1] and a[j] didn't change that order.
     * Therefore, we simply need to reverse the numbers following a[i-1] to get the next smallest lexicographic permutation.
     *
     * @param nums
     */


    public void nextPermutation(int[] nums) {
        if(nums.length < 2) return;

        int end = nums.length - 1;

        // we want next lexicographic order, so start from the end and check for
        // the first occurrence where we see nums[i-1] < nums[i]
        int i;
        for(i = end; i > 0; --i) {
            if(nums[i] > nums[i-1]) break;
        }

        if(i != 0) {
            // find the closest number to the right of i - 1
            int j;
            for(j = end; j >= i; --j) {
                if(nums[j] > nums[i-1]) break;
            }
            swap(nums, i-1, j);
        }

        reverse(nums, i, end);
    }


    public void nextPermutationV2(int[] nums) {
        if(nums.length < 2) return;

        int end = nums.length - 1;
        int i = end;

        while(i > 0 && nums[i-1] >= nums[i]) {
            --i;
        }

        if(i == 0) {
            reverse(nums, i, end);
            return;
        }

        int j = end;
        while(j > 0 && nums[j] <= nums[i-1]) {
            --j;
        }
        swap(nums, i-1, j);
        reverse(nums, i, end);
    }

    public void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public void reverse(int[] a, int start, int end) {
        while (start < end) {
            swap(a, start, end);
            ++start;
            --end;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};

        for(int i = 0; i < 1; ++i) {
            new NextPermutation().nextPermutation(nums);
            for (int num : nums) {
                System.out.print(num);
                System.out.print(" ");
            }
            System.out.println("");
        }

    }
}
