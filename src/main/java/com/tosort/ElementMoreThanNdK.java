package com.tosort;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Piyush Sharma on 8/26/16.
 */


/*

http://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/

Given an array of of size n and a number k, find all elements that appear MORE than n/k times
 */

public class ElementMoreThanNdK {

    // We could simply sort the array and then run through the sorted array to get the output =>
    // Complexity = O(nlogn)

    // This solution is O(nk)
    public void moreThanNdK(int[] nums, int k) {

        /* there can be a max of k-1 distinct elements that will appear more than n/k times in array of size n
           eg.
           1. n = 10, k = 2; n/k = 5 => there can be only 1 (k-1) element that can appear more than 5 times
           (at least 6) in an array of size 10;
           2. n = 20, k = 6; n/k = 3 => there can be a max of 5 (k-1) elements that can appear more than 3 times
           (at least 4) in an array of size 20.
         */

        // Step 1. Create a temporary array of size (k-1) to store elements and their counts
        // (The output elements are going to be among these k-1 elements) O(k)
        HashMap<Integer, Integer> possibleOutput = new HashMap<Integer, Integer>();

        // Step 2. Traverse through the input array and update possibleOutput
        // (add/remove an element or increase/decrease count) for every traversed element.
        // The map possibleOutput stores potential (k-1) candidates at every step. This step takes O(nk) time.
        for (int i = 0; i < nums.length; ++i) {

            Integer val = possibleOutput.get(nums[i]);
            if (val != null) {
                possibleOutput.put(nums[i], val + 1);

            } else if (possibleOutput.size() < (k - 1)) {
                possibleOutput.put(nums[i], 1);

            } else if (possibleOutput.size() == (k - 1)) {

                Iterator<Map.Entry<Integer, Integer>> iter = possibleOutput.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry<Integer, Integer> entry = iter.next();
                    int count = entry.getValue();
                    if (count == 1) {
                        iter.remove();
                    } else {
                        entry.setValue(count - 1);
                    }
                }
            }
        }

        // Step 3. Iterate through final (k-1) potential candidates (stored in possibleOutput),
        // check if it actually has count more than n/k. This step takes O(nk) time.
        Iterator<Map.Entry<Integer, Integer>> iter = possibleOutput.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            entry.setValue(0);
        }

        int maxOccurence = nums.length / k;
        for (int i = 0; i < nums.length; ++i) {

            Integer val = possibleOutput.get(nums[i]);
            if (val != null) {
                possibleOutput.put(nums[i], val + 1);

                if (val + 1 > maxOccurence) {
                    // Found a solution, add to whatever output list or print
                    System.out.printf("Number: %d ", nums[i]);
                    System.out.printf(", n/k = %d, Count: %d \n", maxOccurence, val + 1);
                    possibleOutput.remove(nums[i]);
                }
            }
        }

    }


    public static void main(String[] args) {
        ElementMoreThanNdK o = new ElementMoreThanNdK();

        System.out.println("First Test");
        int arr1[] = {4, 5, 6, 7, 8, 4, 4};
        int k = 3;

        o.moreThanNdK(arr1, k);

        System.out.println("Second Test");
        int arr2[] = {4, 2, 2, 7};
        k = 3;
        o.moreThanNdK(arr2, k);

        System.out.println("Third Test");
        int arr3[] = {2, 7, 2};
        k = 2;
        o.moreThanNdK(arr3, k);

        System.out.println("Fourth Test");
        int arr4[] = {2, 3, 3, 2};
        k = 3;
        o.moreThanNdK(arr4, k);
    }
}
