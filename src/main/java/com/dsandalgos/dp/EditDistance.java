package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */

/*
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
(each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

a) Insert a character
b) Delete a character
c) Substitution of a character
*/

public class EditDistance {


    // DP solution
    public int minDistance(String word1, String word2) {

        int w1len = word1.length();
        int w2len = word2.length();
        int[][] dp = new int[w1len + 1][w2len + 1];

        for(int i = 0; i <= w1len; ++i) {

            for(int j = 0; j <= w2len; ++j) {

                // first string is empty, insert all characters of second string
                if(i == 0) {
                    dp[i][j] = j;  // Min operations = j
                }

                // second string is empty, remove all characters of second string
                else if(j == 0) {
                    dp[i][j] = i; // Min operations = i
                }

                // If last characters are same, ignore last char
                // and recur for remaining string
                else if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i-1][j-1];
                }

                // If last character are different, consider all
                // possibilities and find minimum
                else {

                    dp[i][j] = 1 + min(dp[i][j-1], // insert
                                        dp[i-1][j], // remove
                                        dp[i-1][j-1]); // replace

                }
            }
        }

        return dp[w1len][w2len];
    }




    /*
    The idea is process all characters one by one staring from either from left or right sides of both strings.
    Let we traverse from right corner, there are two possibilities for every pair of character being traversed.

    m: Length of str1 (first string)
    n: Length of str2 (second string)
    If last characters of two strings are same, nothing much to do.
    Ignore last characters and get count for remaining strings. So we recur for lengths m-1 and n-1.

    Else (If last characters are not same), we consider all operations on ‘str1′, consider all three operations
    on last character of first string,
    recursively compute minimum cost for all three operations and take minimum of three values.
        Insert: Recur for m and n-1
        Remove: Recur for m-1 and n
        Replace: Recur for m-1 and n-1
    */

    public int minDistanceRecursive(String word1, String word2) {
        if(word1 == null && word2 == null) return 0;

        return minDistance(word1, word1.length(), word2, word2.length());
    }

    private int minDistance(String word1, int w1, String word2, int w2) {
        if(w1 == 0) return w2;

        if(w2 == 0) return w1;

        if(word1.charAt(w1-1) == word2.charAt(w2-1)) {
            return minDistance(word1, w1 - 1, word2, w2 - 1);
        }

        return 1 + min(minDistance(word1, w1, word2, w2 - 1),   // insert
                        minDistance(word1, w1 - 1, word2, w2), // remove
                        minDistance(word1, w1 - 1, word2, w2 - 1)); // substitute

    }

    private int min(int x, int y, int z) {
        return Math.min(x, Math.min(y, z));
    }

}
