package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*
A sequence of number is called arithmetic if it consists of at least three elements
and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequence:
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The following sequence is not arithmetic.
1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given.
A slice of that array is any pair of integers (P, Q) such that 0 <= P < Q < N.

A slice (P, Q) of array A is called arithmetic if the sequence:
A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q.

The function should return the number of arithmetic slices in the array A.

Example:
A = [1, 2, 3, 4]
return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.

*/

public class ArithmeticSlices {

    /*
     At any index i, if we see it forms arithmetic slice with former two,
     that means running (curCount) sequence gets extended upto this index,
     at the same time we get one more sequence (the three numbers ending at i),
     so curCount++.
     Any time this happens, add the curCount value to total sum => This adds all sub sequences as well.

     Any time we find ith index does not form  arithmetic slice, make currently running no of seqs to zero.
    */
    public int numberOfArithmeticSlices(int[] A) {
        int curCount = 0, sum = 0;

        for (int i = 2; i < A.length; i++)
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                curCount += 1;
                sum += curCount;

            } else {
                curCount = 0;
            }
        return sum;
    }

    // the logic is to look at every middle element and its difference to prev and next
    // while the difference is same, increase count
    // when the diff is not same, add all sub-sequences to the sum
    // all the sub-sequences that could be made => (count * (count + 1)) / 2
    public int numberOfArithmeticSlicesDP(int[] A) {

        int sum = 0;
        if(A == null || A.length == 0) return sum;

        int count = 0; // increment till you find a sequence

        for(int i = 1; i < A.length-1; i++) {
            if(A[i] - A[i-1] == A[i+1] - A[i]) {
                ++count;
            } else{
                sum += (count * (count + 1)) / 2;
                count = 0;
            }
        }
        sum += (count * (count + 1)) / 2;
        return sum;
    }

    public int numberOfArithmeticSlicesBF(int[] A) {

        if(A.length < 3) return 0;
        int sum = 0;

        for(int i = 0; i < A.length - 2; ++i) {
            for(int j = i + 2; j < A.length; ++j) {
                if(isArithmeticSlice(A, i, j)) {
                    sum += 1;
                }
            }
        }

        return sum;
    }

    private boolean isArithmeticSlice(int[] a, int start, int end) {
        int diff = a[start + 1] - a[start];

        for(int i = start+1; i < end; ++i) {
            if(a[i+1] - a[i] != diff) return false;
        }

        return true;
    }


    public static void main(String[] args) {

        ArithmeticSlices arithmeticSlices = new ArithmeticSlices();

        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1,2,3}));

        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1,2,3,4}));

        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1,2,3,4,5}));

        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1,2,3,4,5,6}));

        System.out.println(arithmeticSlices.numberOfArithmeticSlices(new int[]{1,2,3,5,7}));
    }


}
