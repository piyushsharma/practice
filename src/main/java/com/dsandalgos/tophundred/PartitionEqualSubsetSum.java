package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */

/*

Given a non-empty array containing only positive integers, find if the array can be partitioned
into two subsets such that the sum of elements in both subsets is equal.

Note:
Each of the array element will not exceed 100. The array size will not exceed 200.

Example 1:
Input: [1, 5, 11, 5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: [1, 2, 3, 5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/


public class PartitionEqualSubsetSum {

    // is only possible when the sum of the elements is not too big.
    // Since we store a 2D array of size (sum/2) * (n+1) and fill that
    // O(sum * n), space is also O(sum * n)
    public boolean canPartition(int[] nums) {
        // at least 2 elements
        if(nums.length < 2) return false;

        int sum = 0;
        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i];
        }
        // sum must be even to have a equal partition
        if(sum % 2 != 0) {
            return false;
        }

        // Now we need to find a subset with value = sum/2;
        sum = sum/2;
        int n = nums.length;
        boolean[][] subsetSum = new boolean[sum + 1][n + 1];


        /*
        Input Array: [1,2,3,4,5,6,7]

        subsetSum[i][j] = true if a subset of {nums[0], nums[1], ..nums[j-1]} has sum equal to i, otherwise false

         SET:   0       1       2       3           4           5               6               7
               {}      {1}    {1,2}  {1,2,3}    {1,2,3,4}   {1,2,3,4,5}  {1,2,3,4,5,6}  {1,2,3,4,5,6,7}
              _____________________________________________________________________________________________
 SUM:   0    | T        T       T       T           T          T                T               T
             |
        1    | F        T       T       T           T          T                T               T
             |
        2    | F        F       T       T           T          T                T               T
             |
        3    | F        F       T       T           T          T                T               T
             |
        4    | F        F       F       T           T          T                T               T
             |
        5    | F        F       F       T           T          T                T               T
             |
        6    | F        F       F       T           T          T                T               T
             |
        7    | F        F       F       F           T          T                T               T
             |
        8    | F        F       F       F           T          T                T               T
             |
        9    | F        F       F       F           T          T                T               T
             |
        10   | F        F       F       F           T          T                T               T
             |
        11   | F        F       F       F           F          T                T               T
             |
        12   | F        F       F       F           F          T                T               T
             |
        13   | F        F       F       F           F          T                T               T
             |
        14   | F        F       F       F           F          T                T               T
             |

        */

        // set the first row as true, because all sets have an empty set that sums to 0
        for(int i = 0; i <= n; ++i) subsetSum[0][i] = true;

        // set the first column as false because (except 0,0), because an empty set can only be true for sum = 0
        for(int i = 1; i <= sum; ++i) subsetSum[i][0] = false;

        for(int i = 1; i <= sum; ++i) {
            for(int j = 1; j <= n; ++j) {

                // if the sum was present in the previous set, it must be present in this set as well
                // if 1 is present in {1,2}, it will be present in all other sets when traversing this row,
                // because we are adding elements each time when we move the column
                subsetSum[i][j] = subsetSum[i][j-1];

                // i is the sum we are considering in this loop
                // note that if i < nums[j-1], whether or not we can make a subset of sum i
                // would have already been considered in the previous look before adding a new element
                // to the set, so subsetSum[i][j] = subsetSum[i][j-1] covers that case
                if(i >= nums[j-1]) {
                    // if i >= nums[j-1] we need to decide whether we want to check if we
                    // can get to the subset sum i by both considering / not considering the element
                    // do not consider the element || consider the element
                    subsetSum[i][j] = subsetSum[i][j-1] || subsetSum[ i - nums[j-1] ][j-1];

                }
            }
        }

        /*
        // uncomment this part to print table
        for (int i = 0; i <= sum; i++) {
            for (int j = 0; j <= n; j++)
                System.out.printf("%b    ", subsetSum[i][j]);
            System.out.printf("\n");
        }
        */

        return subsetSum[sum][n];
    }


    // O(2^n) worst case, since we might actually break down and look at each and every subset
    // check printAllSubsets
    public boolean canPartitionRecursive(int[] nums) {
        // at least 2 elements
        if(nums.length < 2) return false;

        int sum = 0;
        for(int i = 0; i < nums.length; ++i) {
            sum += nums[i];
        }
        // sum must be even to have a equal partition
        if(sum % 2 != 0) {
            return false;
        }

        // Now we need to find a subset with value = sum/2;

        /*
            Let canPartition(arr, n, sum/2) be the function that returns true if
            there is a subset of arr[0..n-1] with sum equal to sum/2

            The canPartition problem can be divided into two sub-problems
            a) canPartition() without considering last element (reducing n to n-1)
            b) canPartition() considering the last element (reducing sum/2 by arr[n-1] and n to n-1)
                If any of the above the above sub-problems return true, then return true.
                canPartition(arr, n, sum/2) = canPartition(arr, n-1, sum/2) || canPartition(arr, n-1, sum/2 - arr[n-1])
        */
        return canPartition(nums, nums.length - 1, sum/2);

    }

    private boolean canPartition(int[] nums, int index, int sum) {
        if(index < 0) return false;

        if(index == 0) return sum == 0;

        // An optimization:
        // if element at index - 1 is > sum, we don't need to consider that at all
        if(nums[index - 1] > sum) return canPartition(nums, index - 1, sum);

        // consider the last element
        // or do not consider the last element
        return canPartition(nums, index - 1, sum - nums[index]) ||  canPartition(nums, index - 1, sum);
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum p = new PartitionEqualSubsetSum();

        System.out.println(p.canPartition(new int[]{1}));
        System.out.println(p.canPartition(new int[]{1,3,4,4}));

        System.out.println(p.canPartition(new int[]{1,5,11,5,10,10}));
        System.out.println(p.canPartition(new int[]{1,1}));
        System.out.println(p.canPartition(new int[]{1,0,1}));
        System.out.println(p.canPartition(new int[]{1,2,5,11,5,10,10}));
        System.out.println(p.canPartition(new int[]{1,2,3,4,5,6,7}));
    }

}
