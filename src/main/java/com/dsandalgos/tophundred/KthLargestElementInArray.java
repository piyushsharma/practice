package com.dsandalgos.tophundred;

import java.util.PriorityQueue;
import java.util.Random;

public class KthLargestElementInArray {

    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);

        for(int n : nums) {
            pq.add(n);
            if(pq.size() > k) {
                pq.remove();
            }
        }
        return pq.remove();
    }

    /**
     * Hoare's selection algorithm - average = O(N), worst case is O(N^2)
     *
     * Like quicksort -- partition into two sides, since no need to sort completely, average = O(N)
     *
     * Note: k largest = n-k smallest
     *
     * Algo:
     * 1) choose random pivot
     * 2) find perfect position of the pivot
     * 3) depening on n-k left or right of pivot's perfect position, repeat
     *
     */

    public int findKthLargestHoare(int[] nums, int k) {

        return findKthSmallest(nums, 0, nums.length - 1, nums.length - k);
    }

    private int findKthSmallest(int[] nums, int low, int high, int k) {
        // if only one element left, return
        if(low == high) {
            return nums[low];
        }

        Random random = new Random();
        int pivotIndex = low + random.nextInt(high - low);

        pivotIndex = partitionAtPivot(nums, low, high, pivotIndex);

        if(pivotIndex == k) {
            return nums[pivotIndex];
        }

        if(pivotIndex < k) {
            return findKthSmallest(nums, pivotIndex+1, high, k);
        } else {
            return findKthSmallest(nums, low, pivotIndex-1, k);
        }
    }

    private int partitionAtPivot(int[] nums, int low, int high, int pivotIndex) {

        int pivot = nums[pivotIndex];

        // 1. move number at pivot index to the end
        swap(nums, pivotIndex, high);

        // use to move numbers
        int ptrLessThanPivot = low;

        // 2. move all smaller elements to the left
        for(int i = low; i <= high; ++i) {

            if(nums[i] < pivot) {
                swap(nums, i, ptrLessThanPivot);
                ++ptrLessThanPivot;
            }
        }

        // 3. move pivot (stored at high) to its final place
        swap(nums, ptrLessThanPivot, high);

        return ptrLessThanPivot;
    }

    private void swap(int[] nums, int pivotIndex, int high) {
        int temp = nums[pivotIndex];
        nums[pivotIndex] = nums[high];
        nums[high] = temp;
    }


}
