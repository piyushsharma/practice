package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:
You may assume that nums1 has enough space (size that is greater or equal to m + n)
to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
*/


public class MergeSortedArray {


    public void merge(int[] nums1, int m, int[] nums2, int n) {

        int indexNums1 = m - 1;
        int indexNums2 = n - 1;
        int i = m + n - 1;
        while(i >= 0 && indexNums1 >= 0 && indexNums2 >= 0) {
            if(nums1[indexNums1] > nums2[indexNums2]) {
                nums1[i] = nums1[indexNums1];
                --indexNums1;
            } else {
                nums1[i] = nums2[indexNums2];
                --indexNums2;
            }
            --i;
        }
        // i.e. indexNums2 must have become 0
        while(indexNums1 >= 0) {
            nums1[i] = nums1[indexNums1];
            --indexNums1;
            --i;
        }

        // i.e. indexNums1 must have become 0
        while(indexNums2 >= 0) {
            nums1[i] = nums2[indexNums2];
            --indexNums2;
            --i;
        }
    }


    public void mergeV2(int[] nums1, int m, int[] nums2, int n) {

        int[] temp = new int[m+n];

        int index1 = 0;
        int index2 = 0;

        int i = 0;
        while(index1 < m && index2 < n) {
            if(nums1[index1] <= nums2[index2]) {
                temp[i] = nums1[index1];
                ++index1;
            } else {
                temp[i] = nums2[index2];
                ++index2;
            }
            ++i;
        }

        while(index1 < m) {
            temp[i] = nums1[index1];
            ++i;
            ++index1;
        }

        while(index2 < n) {
            temp[i] = nums2[index2];
            ++i;
            ++index2;
        }

        for(int j = 0; j < temp.length; ++j) {
            nums1[j] = temp[j];
        }
    }


    public static void main(String[] args) {
        int[] m = {4, 0, 0, 0, 0, 0};
        int[] n = {1, 2, 3, 5, 6};

        MergeSortedArray mergeSortedArray = new MergeSortedArray();
        mergeSortedArray.merge(m, 1, n, 5);

        for(int i = 0; i < m.length; ++i)
            System.out.printf("%d ", m[i]);
    }


}
