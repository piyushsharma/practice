package com.dsandalgos.string;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Piyush Sharma
 */


/*
Given a string S, find the length of the longest substring T that contains at most two distinct characters.
For example,
Given S = “eceba”,
T is "ece" which its length is 3.
 */

public class LongestSubstringWithAtMostTwoDistinctCharacters {

    /*
    The trick is to maintain a sliding window that always satisfies the invariant where there are always
    at most two distinct characters in it.

    When we add a new character that breaks this invariant, how can we move the begin pointer to satisfy the invariant?

    Example: "abbac", our first window is the substring “abba”. When we add the character ‘c’ into the sliding window,
    it breaks the invariant. Therefore, we have to readjust the window to satisfy the invariant again.
    We need to choose the starting point such that the invariant is satisfied. New window "ac", but we only update the
    max length when we get a window size more than the current max window size.

    Let’s look at another example where S = “abaac”. We found our first window “abaa”. When we add ‘c’ to the window,
    the next sliding window should be “aac”.
    This method iterates n times and therefore its runtime complexity is O(n). We use three pointers to maintain this
    window.
     */

    public int longestSubstringTwo(String s) {
        if(s == null || s.isEmpty())
            return 0;

        int len = s.length();

        // i is the currentIndex
        // j will point to the last occurrence of the character that is not the same as the character at curIndex (i)
        // k is the start of the sliding window
        // curIndex(i) will also be the end of the sliding window that we keep moving on every iteration

        int i = 0; int j = -1; int k = 0;
        int maxLen = 0;

        for(i = 1; i < len; ++i) {

            char ch1 = s.charAt(i-1);
            char ch2 = s.charAt(i);

            if(ch1 == ch2) continue;

            if(j >= 0 && ch2 != s.charAt(j)) {
                // update the maxLen and start new window
                maxLen = Math.max(i - k, maxLen);
                k = j + 1;
            }

            // curIndex (i) and j are always pointing to the two different characters we are considering in the window
            j = i - 1;
        }

        return Math.max(len - k, maxLen);
    }


    /*
    Although the above method works fine, it could not be easily generalized to the case where string S
    contains at most k distinct characters.
    The key is when we adjust the sliding window to satisfy the invariant, we need a counter of the number
    of times each character appears in the substring.
     */

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int[] count = new int[256];
        int j = 0, numDistinct = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {

            if (count[s.charAt(i)] == 0) numDistinct++;

            count[s.charAt(i)]++;

            // start removing characters from the sliding window until numDistinct becomes <= k;
            while (numDistinct > k) {
                count[s.charAt(j)]--;
                if (count[s.charAt(j)] == 0) numDistinct--;
                j++;
            }
            maxLen = Math.max(i - j + 1, maxLen);
        }
        return maxLen;
    }



    public int lengthOfLongestSubstringKDistinctV2(String s, int k) {

        Map<Character, Integer> map = new HashMap<>();

        int left = 0;
        int right = 0;

        int result = 0;
        int resLeft = 0;
        int resRight = 0;

        while(right < s.length()) {

            int c = map.getOrDefault(s.charAt(right), 0);
            map.put(s.charAt(right), c + 1);

            while(map.size() > k) {

                 int l = map.get(s.charAt(left));
                 if(l == 1) {
                     map.remove(s.charAt(left));
                 } else {
                     map.put(s.charAt(left), l - 1);
                 }

                 ++left;
            }

            resLeft = left;
            resRight = right;

            result = Math.max(result, resRight - resLeft + 1);

            ++right;
        }

        return result;
    }




    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters l = new LongestSubstringWithAtMostTwoDistinctCharacters();

        System.out.println(l.longestSubstringTwo("ecews"));
        System.out.println(l.longestSubstringTwo("abaac"));

        System.out.println(l.lengthOfLongestSubstringKDistinct("abaac", 2));
        System.out.println(l.lengthOfLongestSubstringKDistinct("abaac", 3));
        System.out.println(l.lengthOfLongestSubstringKDistinct("abaacdddddddddde", 4));
        System.out.println(l.lengthOfLongestSubstringKDistinct("a", 2));


        System.out.println(l.lengthOfLongestSubstringKDistinctV2("abaac", 2));
        System.out.println(l.lengthOfLongestSubstringKDistinctV2("abaac", 3));
        System.out.println(l.lengthOfLongestSubstringKDistinctV2("abaacdddddddddde", 4));
        System.out.println(l.lengthOfLongestSubstringKDistinctV2("a", 2));
    }

}
