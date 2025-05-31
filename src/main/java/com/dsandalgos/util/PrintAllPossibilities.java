package com.dsandalgos.util;

/**
 * Created by Piyush Sharma
 */


public class PrintAllPossibilities {



    /**
     *
     * As we all know there are 2^N possible subsets of any given set with N elements.
     * What if we represent each element in a subset with a bit.
     * A bit can be either 0 or 1, thus we can use this to denote whether the corresponding element
     * belongs to this given subset or not. So each bit pattern will represent a subset.
     *
     * Consider a set A of 3 elements.
     * A = {a, b, c}
     *
     * Now, we need 3 bits, one bit for each element.
     * 1 represent that the corresponding element is present in the subset,
     * whereas 0 represent the corresponding element is not in the subset.
     *
     * Let’s write all the possible combination of these 3 bits.
     *
     * 0 = (000)2 = {}
     * 1 = (001)2 = {c}
     * 2 = (010)2 = {b}
     * 3 = (011)2 = {b, c}
     * 4 = (100)2 = {a}
     * 5 = (101)2 = {a, c}
     * 6 = (110)2 = {a, b}
     * 7 = (111)2 = {a, b, c}
     */
    public void printPossibleSubSets(int[] a) {
        for(int i = 0; i < (1 << a.length); ++i) {
            for(int j = 0; j < a.length; ++i) {

                // if jth bit is set in i; print subset
                if((i & (1 << j)) > 0) {
                    System.out.println(a[j]);
                }
            }
            System.out.println("");
        }


    }

    // print all subsets of a set
    /*
    Power set P(S) of a set S is the set of all subsets of S.
    For example S = {a, b, c} then P(s) = {{}, {a}, {b}, {c}, {a,b}, {a, c}, {b, c}, {a, b, c}}.

    If S has n elements in it then P(s) will have 2^n elements
    */

    /*
    ALGO:
        Input: Set[]
        1. Get the size of power set: powerSetSize = pow(2, setSize)
        2  Loop for counter from 0 to powerSetSize
             (a) Loop for i = 0 to setSize
                  If ith bit in counter is set, Print ith element from set for this subset
            (b) Print separator for subsets i.e., newline
    */
    public void printAllSubSets(int[] a) {

        // 2^n possibilities
        // O(n * 2^n) because you need to print all possibilities
        int powerSet = 1 << a.length;

        for(int counter = 0; counter < powerSet; ++counter) {
            for(int j = 0; j < a.length; ++j) {

                int isSet = (1 << j) & counter;
                if(isSet > 0) {
                    System.out.printf("%d ", a[j]);
                }

            }

            System.out.println();
        }

    }



    public void printAllSubSetsUsingString(String a) {

        // 2^n possibilities
        // O(n * 2^n) because you need to print all possibilities
        int powerSet = 1 << a.length();

        for(int counter = 0; counter < powerSet; ++counter) {
            for(int j = 0; j < a.length(); ++j) {

                int isSet = (1 << j) & counter;
                if(isSet > 0) {
                    System.out.printf("%s ", a.charAt(j));
                }

            }

            System.out.println();
        }

    }



    public void printAllSubStrings(String input) {

        // O(n^2) because you need to print all possibilities
        for(int i = 0; i < input.length(); ++i) {
            for(int j = i; j < input.length(); ++j) {
                System.out.println(input.substring(i, j+1));
            }
        }

    }


    public static void main(String[] args) {
        PrintAllPossibilities p = new PrintAllPossibilities();
        p.printAllSubSets(new int[]{1,2,3,4});
        System.out.println("====================");
        p.printAllSubStrings("test");
        System.out.println("====================");
        p.printAllSubSetsUsingString("test");
    }
}
