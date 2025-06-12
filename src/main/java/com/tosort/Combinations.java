package com.tosort;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


public class Combinations {

    // prints the empty string as well
    private static void combinations2(String prefix, String inStr) {
        System.out.println(prefix);
        for(int i = 0; i < inStr.length(); ++i) {
            combinations2(prefix + inStr.charAt(i), inStr.substring(i + 1));
        }
    }

    // print all combinations of size k
    private static void combinationsK(String inStr, int k) {
        if(k > inStr.length())
            return;

        String prefix = "";
        combinationsK(prefix, inStr, k);
    }

    // print all subsets that take k of the remaining elements, with given prefix
    private static void combinationsK(String prefix, String inStr, int k) {
        if(k == 0) {
            System.out.println(prefix);
            return;
        }

        for(int i = 0; i < inStr.length(); ++i)
            combinationsK(prefix + inStr.charAt(i), inStr.substring(i+1), k-1);
    }

    // with array instead of string
    private static void combinationsK(int[] s, int index, int nextInt, int k, int N) {
        // base case to stop recursion
        if(k == index) {
            for(int i = 0; i < s.length; ++i)
                System.out.printf("%d ", s[i]);
            System.out.printf("\n");
            return;
        }

        // note we iterate over all possible integers
        for(int i = nextInt; i < N; ++i) {
            s[index] = i;
            combinationsK(s, index+1, i+1, k, N);
        }
    }

    public static void main(String[] args) {
        String inStr = "abc";
        String prefix = "";

        // combinations prints out all 2^n combinations of any size - subset of n elements, independent of order
        combinations(prefix, inStr);
        System.out.println("++++++");
        combinations2(prefix, inStr);

        System.out.println("++++++");
        combinationsK(inStr, 1);
        System.out.println("++++++");


        /* Enumerates all subsets of size k on N elements */
        int N = 6;
        int k = 4;
        // basically get all combinations of size 4 from the set [0,1,2,3,4,5]

        int s[] = new int[k];
        combinationsK(s, 0, 0, k, N);
    }

    // does not print empty string
    private static void combinations(String prefix, String inStr) {
        if (inStr.length() > 0) {
            System.out.println(prefix + inStr.charAt(0));
            combinations(prefix + inStr.charAt(0), inStr.substring(1));
            combinations(prefix, inStr.substring(1));
        }
    }
}
