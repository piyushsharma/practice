package com.dsandalgos.tophundred;

/**
 * Created by Piyush Sharma
 */


/*

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num
calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)).
But can you do it in linear time O(n) /possibly in a single pass?

Space complexity should be O(n).
Can you do it like a boss?

*/


public class CountingBits {

    public int[] countBits(int num) {

        int[] result = new int[num + 1];

        if(num == 0) return result;

        // the recurrence for this problem is
        // f(i) = f(i / 2) + i % 2
        for(int i = 1; i <= num; ++i) {

            // this could also be written as res[i] = res[ i >> 1 ] + (i & 1)
            result[i] = result[i/2] + i % 2;
        }

        return result;
    }

    public static void main(String[] args) {
        CountingBits c = new CountingBits();

        int[] result = c.countBits(60);

        for(int i : result) {
            System.out.printf("%d ", i);
        }
    }


}
