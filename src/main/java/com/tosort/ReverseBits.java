package com.tosort;

/**
 Reverse bits of a given 32 bits unsigned integer.
 For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 return 964176192 (represented in binary as 00111001011110000010100101000000).

 Follow up:
 If this function is called many times, how would you optimize it?
 */

public class ReverseBits {

    public int reverseBits(int n) {
        int result = 0;
        for(int i = 31; i >= 0; --i) {
            int bitValue = 1 & (n >> i);
            result = result | (bitValue << (31 - i));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseBits().reverseBits(43261596));
        System.out.println(new ReverseBits().reverseBits(2));
    }
}
