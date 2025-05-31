package com.dsandalgos.tophundred;

/*
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

/* Refer
  http://www.programcreek.com/2014/05/leetcode-unique-binary-search-trees-java/
*/

/*
Number of binary search trees =
                     (Number of ways root can be chosen) *
                     (Number of Left binary search sub-trees) *
                     (Number of Right binary search sub-trees)
*/

public class UniqueBinarySearchTrees {

    /* We know that all node in left subtree are smaller than root and in right subtree are larger than root
       so if we have ith number as root,
       all numbers from 1 to i-1 will be in left subtree
       and i+1 to N will be in right subtree.

       If 1 to i-1 can form X different trees and i+1 to N can from Y different trees
       then we will have X*Y total trees when ith number is root

       We also have N choices for root also so we can simply iterate from 1 to N for root and
       another loop for left and right subtree.

       If we take a closer look, we can notice that the count is basically n’th Catalan number.

       ASIDE:
       Catalan numbers are a sequence of natural numbers that occurs in many interesting counting problems like following.
       http://www.geeksforgeeks.org/applications-of-catalan-numbers/

        The first few Catalan numbers for n = 0, 1, 2, 3, … are 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862

        Recursive Solution
        Catalan numbers satisfy the following recursive formula.
                          ____n
                          \
                           \
        C0 = 1;   C(n+1) = /        Ci * C(n - i)
                          /___i=0

        // A recursive function to find nth catalan number
        public int catalan(int n) {
            // Base case
            if (n <= 1) return 1;

            int res = 0;
            // catalan(n) is sum of catalan(i)*catalan(n-i-1)
            for (int i = 1; i < n; ++i) {
                res += catalan(i)*catalan(n-i-1);
            }
            return res;
        }

    */

    // dp way of getting catalan number
    public int numTrees(int n) {

        int count[] = new int[n+1];
        count[0] = 1;
        count[1] = 1;

        for(int i = 2; i <= n; ++i)
            for(int j = 0; j < i; ++j) {
                count[i] += count[j] * count[i-j-1];
            }

        return count[n];
    }
}