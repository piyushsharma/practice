package com.dsandalgos.math;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

For example:

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.

Follow up:
Could you do it without any loop/recursion in O(1) runtime?
 */

public class AddDigits {

//    https://en.wikipedia.org/wiki/Digital_root
//    http://www.thonky.com/nine-hours-nine-persons-nine-doors/digital-root
//    http://mathworld.wolfram.com/DigitalRoot.html
    public int addDigitsWithoutLooping(int num) {
        return 1 + ((num - 1) % 9);
    }


    // loop
    public int addDigits(int num) {
        int sum = num;
        while(sum > 9) {
            sum = 0;
            while (num != 0) {
                int mod = num % 10;
                sum += mod;
                num = num / 10;
            }
            num = sum;
        }
        return sum;
    }


    public static void main(String[] args) {

        System.out.println(new AddDigits().addDigits(3));
        System.out.println(new AddDigits().addDigits(38));
        System.out.println(new AddDigits().addDigits(31));
        System.out.println(new AddDigits().addDigits(99));
        System.out.println(new AddDigits().addDigits(2132143));

    }
}
