package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/* Generate all the strings of n bits
*  Assume A[0 .. n -1] is an array of size n
* */

public class BinaryPermutations {

    public void binary(int[]A, int n) {
        if(n == 0) {
            printArray(A);
            return;
        }

        A[n-1] = 0;
        binary(A, n-1);
        A[n-1] = 1;
        binary(A, n-1);
    }

    private void printArray(int[] a) {
        for(int i = 0; i < a.length; ++i) {
            System.out.print(a[i]);
        }
        System.out.print("\n");
    }

    public static void main(String[] args) {
        new BinaryPermutations().binary(new int[3], 3);
    }

}
