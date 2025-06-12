package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an integer, write a function to determine if it is a power of two.
*/

public class PowerOfTwo {

    public boolean isPowerOfTwo(int n) {

        int count = 0;
        while(n > 0 && count <= 1) {
            if((n & 1) == 1) {
                ++count;
            }
            n = n >>> 1;
        }

        return count == 1 ? true : false;
    }

    // 0, 0010, 0100, 1000, 0001 0000, 0010 0000,
    public boolean isPowerOfTwoA(int n) {
        // leetcode does not consider -2147483648 as power of two for some reason
        if(n == Integer.MIN_VALUE)
            return false;

        return  Integer.bitCount(n) == 1 ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(new PowerOfTwo().isPowerOfTwo(1));
        System.out.println(new PowerOfTwo().isPowerOfTwo(2));
        System.out.println(new PowerOfTwo().isPowerOfTwo(4));
        System.out.println(new PowerOfTwo().isPowerOfTwo(12));
        System.out.println(new PowerOfTwo().isPowerOfTwo(64));
        System.out.println(new PowerOfTwo().isPowerOfTwo(Integer.MIN_VALUE));
    }
}
