package com.dsandalgos.math;

/**
 * Created by Piyush Sharma
 */

/*

Given a list of numbers and a function that returns Low, Medium, or High,
sort the list by Lows, then Mediums, then Highs.

*/

public class DutchNationalFlag {


    // 0 for low, 1 for medium and 2 for high
    public int func(int val) {
        if(val <= 1) {
            return 0;
        }
        if(val > 1 && val <= 2) {
            return 1;
        } else {
            return 2;
        }
    }


    public void sortDutchFlag(int[] arr) {

        int lowPtr = 0;
        int midPtr = 0;
        int highPtr = arr.length - 1;

        while(midPtr <= highPtr) {

            int range = func(arr[midPtr]);
            // if low =>
            if(range == 0) {

                swap(arr, lowPtr, midPtr);
                ++lowPtr;
                ++midPtr;

            } else if(range == 2) { // if high =>
                swap(arr, midPtr, highPtr);
                --highPtr;

            } else {
                ++midPtr;
            }
        }
    }

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        DutchNationalFlag d = new DutchNationalFlag();

//        int[] test = new int[]{10,5,1,9,4,2,6};
//        int test[] = new int[]{0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1};
        int[] test = {1,8,2,8,0,4,1,2};
        d.sortDutchFlag(test);

        for(int i : test) {
            System.out.printf("%d ", i);
        }
    }

}
