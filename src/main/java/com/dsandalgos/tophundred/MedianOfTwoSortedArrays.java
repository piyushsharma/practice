package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Piyush Sharma
 */

/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] A, int[] B) {

        int m = A.length;
        int n = B.length;

        // partition A, B to find 0 <= i <= m, and j = (m + n)/2 - i
        // such that i + j = m + n - i - j
        // A[0] ..... A[i - 1]  |  A[i] ..... A[m - 1]
        // B[0] ..... B[j - 1]  |  B[j] ..... B[n - 1]

        // if we make sure m <= n, j becomes >= 0, because the max value of i = m
        // because =>  j =  (m + n) / 2 - m = (n - m) / 2 => >= 0
        if(m > n) {
            return findMedianSortedArrays(B, A);
        }

        int iLow = 0;
        int iHigh = m;  // end condition, if i == m, means, we don't want to pick anything from A

        int medianIndex = (m + n) / 2;

        while(iLow <= iHigh) {

            int i = iLow + (iHigh - iLow) / 2;

            int j = medianIndex - i;

            if(i > 0 && j < n && A[i-1] > B[j]) {
                // left part > right part
                // decrease i
                iHigh = i - 1;

            } else if(j > 0 && i < m && A[i] < B[j-1]) {
                // left part < right part
                // increase i
                iLow = i + 1;
            } else {
                // we have reached a condition such that
                // A[i] >= B[j-1] && B[j] >= A[i-1]
                // i.e. left part <= right part

                int minRight = 0;
                if(i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(A[i], B[j]);
                }

                if((m + n) % 2 != 0) {
                    return minRight;
                } else {

                    int maxLeft = 0;
                    if(i == 0) {
                        maxLeft = B[j - 1];
                    } else if(j == 0) {
                        maxLeft = A[i - 1];
                    } else {
                        maxLeft = Math.max(A[i-1], B[j-1]);
                    }

                    return (double) (minRight + maxLeft) / 2;
                }
            }
        }
        return 0.0;
    }

    public double findMedianSortedArraysON(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int medianOneIndex = (m + n)/2; // points to the index of the first median
        int medianTwoIndex = -1;
        if((m + n) % 2 == 0) {
            // when m + n is even,
            medianTwoIndex = medianOneIndex - 1;
        }

        int count = 0;
        // we need to take the average of the elements at n-1 and n index while merging
        int numsOneIndex = 0;
        int numsTwoIndex = 0;

        double median = 0;
        while(count <= medianOneIndex) {
            int a = numsOneIndex < m ? nums1[numsOneIndex] : Integer.MAX_VALUE;
            int b = numsTwoIndex < n ? nums2[numsTwoIndex] : Integer.MAX_VALUE;

            if(medianTwoIndex != -1 && (count == medianTwoIndex || count == medianOneIndex)) {
                // when m + n is even
                median += a < b ? a : b;
            } else if(medianTwoIndex == -1 && count == medianOneIndex){
                // when m + n is odd
                median = a < b ? a : b;
            }

            if(a <= b) {
                ++numsOneIndex;
            } else {
                ++numsTwoIndex;
            }
            ++count;
        }
        return medianTwoIndex == -1 ? median : median/2;

    }


    // case when nums1 and nums2 are equal length
    public double findMedianSortedArraysEqualLength(int[] nums1, int[] nums2) {
        int n = nums1.length;

        int count = 0;
        // we need to take the average of the elements at n-1 and n index while merging
        int numsOneIndex = 0;
        int numsTwoIndex = 0;

        double median = 0;
        while(count <= n) {
            int a = numsOneIndex < n ? nums1[numsOneIndex] : Integer.MAX_VALUE;
            int b = numsTwoIndex < n ? nums2[numsTwoIndex] : Integer.MAX_VALUE;

            if(count == n - 1 || count == n) {
                median += a < b ? a : b;
            }

            if(a <= b) {
                ++numsOneIndex;
            } else {
                ++numsTwoIndex;
            }
            ++count;
        }
        return median/2;
    }

    public static void main(String[] args) {
        MedianOfTwoSortedArrays md = new MedianOfTwoSortedArrays();

//        int[] m = new int[]{0,4,10,20,30};
//        int[] n = new int[]{1,5};

        int[] m = new int[]{100001};
        int[] n = new int[]{100000};

        System.out.println(md.findMedianSortedArrays(m, n));

        List<Integer> a = new ArrayList<>();
        Collections.sort(a);
    }
}
