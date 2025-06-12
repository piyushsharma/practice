package com.tosort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits,
    and repeat the process until the number equals 1 (where it will stay),
    or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number

1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
*/

public class HappyNumbers {

    public boolean isHappy(int n) {
        int[] cache = new int[10];
        for(int i = 0; i < 10; ++i) {
            cache[i] = i*i;
        }

        Set<Integer> seenNumbers = new HashSet<Integer>();
        seenNumbers.add(n);

        int number = 0;
        while(true) {
            while(n != 0) {
                number += cache[n%10];
                n = n/10;
            }
            if(number == 1) {
                return true;
            }
            if(seenNumbers.contains(number)) {
                break;
            }
            seenNumbers.add(number);
            n = number;
            number = 0;
        }

        return false;
    }

    public static void main(String[] args) {
        HappyNumbers h = new HappyNumbers();
        System.out.println(h.isHappy(20));
    }

}
