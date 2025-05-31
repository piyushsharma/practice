package com.dsandalgos.dp;

/**
 * Created by Piyush Sharma
 */
public class LongestCommonSubsequence {

    // O(mn)
    public int longestCommonSubsequenceDP(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dpCache = new int[m+1][n+1];

        for(int i = 1; i <= m; ++i) {
            for(int j = 1; j <= n; ++j) {

                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dpCache[i][j] = 1 + dpCache[i-1][j-1];

                } else {
                    dpCache[i][j] = Math.max(dpCache[i][j-1], dpCache[i-1][j]);
                }

            }
        }

        return dpCache[m][n];
    }


    // O(2^n) => tree style recursion in worst case
    public int longestCommonSubsequence(String s1, String s2) {

        return lcs(s1, s1.length() - 1, s2, s2.length() - 1);
    }

    private int lcs(String s1, int i, String s2, int j) {

        if(i < 0 || j < 0) return 0;

        if(s1.charAt(i) == s2.charAt(j)) {
            return  1 + lcs(s1, i - 1, s2, j - 1);
        }

        return Math.max(lcs(s1, i - 1, s2, j), lcs(s1, i, s2, j - 1));
    }

    public static void main(String[] args) {
        LongestCommonSubsequence l = new LongestCommonSubsequence();

        System.out.println(l.longestCommonSubsequenceDP("ABCDGH", "AEDFHR"));
        System.out.println(l.longestCommonSubsequenceDP("AGGTAB", "GXTXAYB"));
    }


}
