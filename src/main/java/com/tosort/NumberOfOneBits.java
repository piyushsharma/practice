package com.tosort;

/**
 Write a function that takes an unsigned integer and returns the number of ’1'
 bits it has (also known as the Hamming weight).

 For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011,
 so the function should return 3.
 */

public class NumberOfOneBits {

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count = 0;
        for(int i = 31; i >= 0; --i) {
            int bitValue = 1 & (n >> i);

            if(bitValue == 1) {
                ++count;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new NumberOfOneBits().hammingWeight(3));
        System.out.println(new NumberOfOneBits().hammingWeight(11));
        System.out.println(new NumberOfOneBits().hammingWeight(-2147483648));
    }


}
