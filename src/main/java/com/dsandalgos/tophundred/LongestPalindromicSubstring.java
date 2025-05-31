package com.dsandalgos.tophundred;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a string S, find the longest palindromic substring in S.
You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.
 */

/*
We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center,
and there are only 2N-1 such centers.

You might be asking why there are 2N-1 but not N centers?
The reason is the center of a palindrome can be in between two letters.
Such palindromes have even number of letters (such as “abba”) and its center are between the two ‘b’s.

Since expanding a palindrome around its center could take O(N) time, the overall complexity is O(N2)
*/


public class LongestPalindromicSubstring {

    public String longestPalindromeDP(String s) {
        if (s == null || s.isEmpty() || s.length() == 1) {
            return s;
        }

        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); ++i) {
            dp[i][i] = true;
        }

        for (int i = 0; i < s.length(); ++i) {
            if (i + 1 < s.length()) {
                dp[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
            }
        }

        String max = "";
        for (int i = s.length() - 1; i >= 0; --i) {
            for (int j = i; j < s.length(); ++j) {

                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);


                if(dp[i][j] && j - i + 1 > max.length()) {
                    max = s.substring(i, j + 1);
                }
            }
        }
        return max;
    }


    public String longestPalindrome(String s) {
        if(s == null || s.isEmpty() || s.length() == 1)
            return s;

        int len = s.length();
        String longestPalindrome = "";
        for(int i = 0; i < len; ++i) {
            // for odd numbered  palindromic substring expanding at index i
            // example if s = "aba", we want to expand left and right from b
            String p = palindromicSubstring(s, len, i-1, i+1);
            if (p.length() > longestPalindrome.length())
                longestPalindrome = p;

            // check for even numbered palindromic string expanding at index i
            // example if s = "abbc", we want to check between the two b's
            p = palindromicSubstring(s, len, i, i+1);
            if (p.length() > longestPalindrome.length())
                longestPalindrome = p;
        }

        return longestPalindrome;
    }

    // expand around center
    public String palindromicSubstring(String s, int length, int leftIndex, int rightIndex) {
        while (leftIndex >=0 && rightIndex < length && s.charAt(leftIndex) == s.charAt(rightIndex)) {
            --leftIndex;
            ++rightIndex;
        }
        // endIndex in substring is exclusive (so no -1)
        return s.substring(leftIndex+1, rightIndex);
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome("paapa"));
        System.out.println(l.longestPalindrome("palinnilap"));
        System.out.println(l.longestPalindrome("a"));
        System.out.println(l.longestPalindrome("abccba"));
        System.out.println(l.longestPalindrome("aaaaaaaaaaa"));
        System.out.println(l.longestPalindrome("anagrampalinnilapanagram"));

        System.out.println("===");

        System.out.println(l.longestPalindromeDP("paapa"));
        System.out.println(l.longestPalindromeDP("palinnilap"));
        System.out.println(l.longestPalindromeDP("a"));
        System.out.println(l.longestPalindromeDP("abccba"));
        System.out.println(l.longestPalindromeDP("aaaaaaaaaaa"));
        System.out.println(l.longestPalindromeDP("anagrampalinnilapanagram"));
    }
}
