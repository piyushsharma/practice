package com.dsandalgos.array;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a non-negative number represented as an array of digits, plus one to the number.
The digits are stored such that the most significant digit is at the head of the list.
 */


public class PlusOne {

    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1; i >= 0; --i) {

            if(digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        // if we reached here, that means, all 9's, so we need to add a one at index 0
        int[] newArr = new int[digits.length + 1];
        newArr[0] = 1;
        // note we don't need to copy digits into newArr because all elements in digits must be zero
        // (for us to reach here)
        return newArr;
    }


    public static void main(String[] args) {
        int[] test = {1, 1, 1, 1, 1, 1, 9, 9, 9, 9};

        int[] result = new PlusOne().plusOne(test);

        for(int i = 0; i < result.length; ++i)
            System.out.printf("%d", result[i]);

    }

}
