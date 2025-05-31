package com.dsandalgos.dp;

/**
 * Given a string s and an integer k, find out if the given string is a K-Palindrome or not.
 *
 * A string is K-Palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcdeca", k = 2
 * Output: true
 * Explanation: Remove 'b' and 'e' characters.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s has only lowercase English letters.
 * 1 <= k <= s.length
 */

public class ValidPalindromeIII {


    public boolean isValidPalindrome(String s, int k) {
        int i = 0;
        int j = s.length() - 1;
        Integer[][] cache = new Integer[s.length()][s.length()];
        return isValidPalindromeDP(i, j, s, cache) <= k;
    }

    private int isValidPalindromeDP(int i, int j, String s, Integer[][] cache) {
        // if only 1 char is left, no need to replace anything, cost is 0
        if(j - i < 1) {
            return 0;
        }

        if(cache[i][j] != null) {
            return cache[i][j];
        }

        int step = 0;
        if(s.charAt(i) == s.charAt(j)) {
            step = isValidPalindromeDP(i + 1, j - 1, s, cache);
        } else {
            step = 1 + Math.min(isValidPalindromeDP(i + 1, j, s, cache), isValidPalindromeDP(i, j - 1, s, cache));
        }
        cache[i][j] = step;

        return step;
    }


    public boolean isValidPalindromeR(String s, int k) {
        int i = 0;
        int j = s.length() - 1;
        return isValidPalindromeRecur(i, j, s, k);
    }

    private boolean isValidPalindromeRecur(int i, int j, String s, int k) {
        if(i == j) {
            return s.charAt(i) == s.charAt(j);
        }

        if(i > j) {
            return false;
        }

        if(s.charAt(i) == s.charAt(j)) {
            if(j - i == 1) {
                return true;
            }
            return isValidPalindromeRecur(i + 1, j - 1, s, k);
        }

        if(k == 0) {
            return false;
        }

        return isValidPalindromeRecur(i + 1, j, s, k - 1) || isValidPalindromeRecur(i, j - 1, s, k - 1);
    }
}
