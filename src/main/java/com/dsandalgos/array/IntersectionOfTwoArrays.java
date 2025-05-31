package com.dsandalgos.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given two arrays, write a function to compute their intersection.
 *
 * Example 1:
 *
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2,2]
 * Example 2:
 *
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [4,9]
 * Note:
 *
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * Follow up:
 *
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk,
 * and the memory is limited such that you cannot load all elements into the memory at once?
 */

public class IntersectionOfTwoArrays {

    /**
     * If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap,
     * read chunks of array that fit into the memory, and record the intersections.
     *
     * If both nums1 and nums2 are so huge that neither fit into the memory,
     * sort them individually (external sort),
     * then read 2 elements from each array at a time in memory, record intersections.
     *
     */

    public int[] intersectSort(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ArrayList<Integer> list = new ArrayList<>();

        int p1 = 0, p2 = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] < nums2[p2]) {
                p1++;
            } else if (nums1[p1] > nums2[p2]) {
                p2++;
            } else {
                list.add(nums1[p1]);
                p1++;
                p2++;
            }
        }

        int[] result = new int[list.size()];
        int i = 0;
        while (i < list.size()) {
            result[i] = list.get(i);
            i++;
        }
        return result;

    }

    public int[] intersect(int[] nums1, int[] nums2) {

        if(nums2.length < nums1.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> smallMap = new HashMap<>();
        for(int i = 0; i < nums1.length; ++i) {
            smallMap.put(nums1[i], smallMap.getOrDefault(nums1[i], 0) + 1);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < nums2.length; ++i) {
            if(smallMap.containsKey(nums2[i])) {
                result.add(nums2[i]);

                if(smallMap.get(nums2[i]) == 1) {
                    smallMap.remove(nums2[i]);
                } else {
                    smallMap.put(nums2[i], smallMap.get(nums2[i]) - 1);
                }
            }
        }

        int[] resultArr = new int[result.size()];
        int i = 0;
        while (i < result.size()) {
            resultArr[i] = result.get(i);
            i++;
        }
        return resultArr;

    }
}
