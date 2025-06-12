package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given an integer, write a function to determine if it is a power of three.

Follow up:
Could you do it without using any loop / recursion?
 */

public class    PowerOfThree {

    public boolean isPowerOfThree(int n) {
        // 1, 3, 9, 27, 81, 243, 729
        if(n <= 0) return false;
        while(n > 0 && n != 1) {
            if(n % 3 == 0) {
                n = n/3;
            } else {
                return false;
            }
        }
        return true;
    }

    // the largest power of 3 for 32 bit is  1162261467... so 1162261467 is sufficient.
    // one line solution without loop =>
    // return n > 0 && 1162261467 % n == 0

    public static void main(String[] args) {
        PowerOfThree p = new PowerOfThree();
        System.out.println(p.isPowerOfThree(0));
        System.out.println(p.isPowerOfThree(1));
        System.out.println(p.isPowerOfThree(3));
        System.out.println(p.isPowerOfThree(9));
        System.out.println(p.isPowerOfThree(8));
        System.out.println(p.isPowerOfThree(27));
        System.out.println(p.isPowerOfThree(45));
        System.out.println(p.isPowerOfThree(19684));
    }

}
