package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma on 1/2/15.
 */


/*
Problem Statement:
    Given an array of integers, every element appears twice except for one. Find that single one.
    Note: Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
*/
public class SingleNumber {

    public int singleNumber(int[] A) {
        int number = 0;
        for(int i = 0; i < A.length; ++i) {
            number = number ^ A[i];
        }
        return number;
    }

}
