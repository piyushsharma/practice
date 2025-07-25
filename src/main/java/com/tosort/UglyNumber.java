package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Write a program to check whether a given number is an ugly number.
Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
Note that 1 is typically treated as an ugly number.
*/

public class UglyNumber {

    public boolean isUgly(int num) {
        if(num <= 0) return false;

        while(num % 2 == 0) {
            num = num/2;
        }
        while(num % 3 == 0) {
            num = num/3;
        }
        while(num % 5 == 0) {
            num = num/5;
        }
        if (num == 1) return true;

        return false;
    }


    public static void main(String[] args) {

        String x = "dcab";

        char[] str = x.toCharArray();
        char temp = str[2];
        str[2] = str[1];
        str[1] = temp;
        System.out.println(new String(str));

    }

}
