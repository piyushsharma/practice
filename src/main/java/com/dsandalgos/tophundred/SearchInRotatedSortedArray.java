package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma on 8/30/16.
 */

/*
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 */

public class SearchInRotatedSortedArray {

    public int search(int[] n, int target) {
        if(n == null || n.length == 0) {
            return -1;
        }

        // pass 1 is to figure out point of rotation if any
        int low = 0;
        int high = n.length - 1;

        while(n[low] > n[high]) {
            int mid = low + (high - low)/2;

            if(n[mid] > n[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        if(n[low] == target) {
            return low;
        }

        // low now has the rotation index (smallest number in the array)
        if(low == 0) {
            return binarySearch(n, low, n.length - 1, target);
        }

        if(target < n[0]) {
            // search right side
            return binarySearch(n, low + 1, n.length - 1, target);
        }

        // search left side
        return binarySearch(n, 0, low - 1, target);
    }

    private int binarySearch(int[] n, int low, int high, int target) {

        while(low <= high) {

            int mid = low + (high - low)/2;

            if(n[mid] == target) {
                return mid;
            } else if (n[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
        int[] test1 = {1,2,3,4,5,6,7,8,0};
        int[] test2 = {2,3,4,5,6,7,8,0,1};
        int[] test3 = {3,4,5,6,7,8,0,1,2};
        int[] test4 = {4,5,6,7,8,0,1,2,3};
        int[] test5 = {5,6,7,8,0,1,2,3,4};
        int[] test6 = {6,7,8,0,1,2,3,4,5};
        int[] test7 = {7,8,0,1,2,3,4,5,6};
        int[] test8 = {8,0,1,2,3,4,5,6,7};
        int[] test9 = {0,1,2,3,4,5,6,7,8};
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();

        for(int i = 0; i < 9; ++i) {
            System.out.printf("%d ", s.search(test1, i));
            System.out.printf("%d ", s.search(test2, i));
            System.out.printf("%d ", s.search(test3, i));
            System.out.printf("%d ", s.search(test4, i));
            System.out.printf("%d ", s.search(test5, i));
            System.out.printf("%d ", s.search(test6, i));
            System.out.printf("%d ", s.search(test7, i));
            System.out.printf("%d ", s.search(test8, i));
            System.out.printf("%d \n", s.search(test9, i));
        }

        int[] test = {1};
        System.out.println(s.search(test, 0));

    }



}


