package com.dsandalgos.tophundred;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Piyush Sharma on 6/17/15.
 */
/*
Given a string, find the length of the longest substring without repeating characters.

For example:
    The longest substring without repeating letters for "abcabcbb" is "abc",
             which the length is 3.
    For "bbbbb" the longest substring is "b", with the length of 1.
*/


public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * By using HashSet as a sliding window, checking if a character in the current can be done in O(1)O(1).
     *
     * A sliding window is an abstract concept commonly used in array/string problems.
     * A window is a range of elements in the array/string which usually defined by the start and end indices,
     * i.e. [i, j)[i,j) (left-closed, right-open). A sliding window is a window "slides"
     * its two boundaries to the certain direction. For example, if we slide [i, j)[i,j)
     * to the right by 11 element, then it becomes [i+1, j+1)[i+1,j+1) (left-closed, right-open).
     *
     * Back to our problem. We use HashSet to store the characters in current window [i, j)[i,j) (j=i initially).
     * Then we slide the index j to the right. If it is not in the HashSet, we slide j further.
     * Doing so until s[j] is already in the HashSet. At this point,
     * we found the maximum size of substrings without duplicate characters start with index i.
     * If we do this for all i, we get our answer.
     */

    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int result = 0, i = 0, j = 0;
        Set<Character> chars = new HashSet<>();

        while(i < len && j < len) {
            // extend j

            if(!chars.contains(s.charAt(j))) {
                chars.add(s.charAt(j));
                ++j;
                result = Math.max(result, j - i);
            } else {

                chars.remove(s.charAt(i));
                ++i;

            }
        }
        return result;
    }

    /**
     * The above solution requires at most 2n steps.
     * In fact, it could be optimized to require only n steps.
     * Instead of using a set to tell if a character exists or not,
     * we could define a mapping of the characters to its index.
     *
     * Then we can skip the characters immediately when we found a repeated character.
     *
     * The reason is that if s[j]s[j] have a duplicate in the range [i, j) with index j',
     * we don't need to increase i little by little. We can skip all the elements in the range [i, j']
     * and let i = j' + 1 directly.
     */
    public int lengthOfLongestSubstringBest(String s) {
        int len = s.length();
        int result = 0, i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();

        for(j = 0; j < len; ++j) {

            if(map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }

            result = Math.max(result, j - i + 1);
            map.put(s.charAt(j), j+1);
        }
        return result;
    }


    /*

    Does it contain ASCII character? unicode set?
    Algo:

    When you have found a repeated character (let’s say at index j), it means that the current substring
    (excluding the repeated character of course) is a potential maximum, so update the maximum if necessary.

    It also means that the repeated character must have appeared before at an index i, where i is less than j.

    Since you know that all substrings that start before or at index i would be less than your current maximum,
    you can safely start to look for the next substring with head which starts exactly at index i + 1.

    Therefore, you would need two indices to record the head and the tail of the current substring.
    Since i and j both traverse at most n steps, the worst case would be 2n steps,
    which the runtime complexity must be O(n).

    Optimization: Could do this in only n steps. Instead of using a table to tell if a character exists or not,
    we could define a mapping of the characters to its index. Then we can skip the characters immediately
    when we found a repeated character.
     */


    // sliding window problem
    public int lengthOfLongestSubstringSW(String s) {
        Map<Character, Integer> map = new HashMap<>();

        int result = 0 ;
        for(int i = 0, j = 0; j < s.length(); ++j) {

            // duplicate in window - move starting pointer
            if(map.containsKey(s.charAt(j))) {
                i = Math.max(i, map.get(s.charAt(j)));
            }

            result = Math.max(result, j - i + 1);

            // j + 1 because we are storing the ending of the sliding window here
            // and to calculate the length of the sliding window we need to do the +1
            map.put(s.charAt(j), j+1);
        }

        return result;
    }



    public int lengthOfLongestSubstringV2(String s) {
        if(s == null || s.isEmpty())
            return 0;

        Map<Character, Integer> m = new HashMap<Character, Integer>();
        int curLen = 0;
        int maxLen = 0;

        for(int i = 0; i < s.length(); ++i) {

            if(m.containsKey(s.charAt(i))) {
                // you only want to look back as much as curLength, because the index value might belong to a
                // character that is not part of the current string you are considering, in the case that happens,
                // you only want to increase curlen, in other cases, you will do i - index, as all other letters
                // are all part of the current string being considered to be the answer

                int prevIndex = m.get(s.charAt(i));
                if(i - prevIndex <= curLen) {
                    curLen = i - prevIndex;
                } else {
                    ++curLen;
                }

            } else {
                ++curLen;
            }
            m.put(s.charAt(i), i);
            maxLen = Math.max(curLen, maxLen);
        }
        return maxLen;
    }

    public static void main(String args[]) {
        LongestSubstringWithoutRepeatingCharacters l = new LongestSubstringWithoutRepeatingCharacters();
//        System.out.println(l.lengthOfLongestSubstring("abcabca"));
//        System.out.println(l.lengthOfLongestSubstring("aaaaaaa"));
//        System.out.println(l.lengthOfLongestSubstring("pwwkew"));
//        System.out.println(l.lengthOfLongestSubstring("abcabcab"));
//        System.out.println(l.lengthOfLongestSubstring("bbbb"));
//        System.out.println(l.lengthOfLongestSubstring("ababdfghi"));
//        System.out.println(l.lengthOfLongestSubstring("dvdf"));
//        System.out.println(l.lengthOfLongestSubstring("abb"));
//        System.out.println(l.lengthOfLongestSubstring("abba"));


        System.out.println(l.lengthOfLongestSubstringV2("abcabca"));
        System.out.println(l.lengthOfLongestSubstringV2("aaaaaaa"));
        System.out.println(l.lengthOfLongestSubstringV2("pwwkew"));
        System.out.println(l.lengthOfLongestSubstringV2("abcabcab"));
        System.out.println(l.lengthOfLongestSubstringV2("bbbb"));
        System.out.println(l.lengthOfLongestSubstringV2("ababdfghi"));
        System.out.println(l.lengthOfLongestSubstringV2("dvdf"));
        System.out.println(l.lengthOfLongestSubstringV2("abb"));
        System.out.println(l.lengthOfLongestSubstringV2("abba"));

        System.out.println(l.lengthOfLongestSubstringV2("tmmzuxt"));
//        3
//        1
//        3
//        3
//        1
//        7
//        3
//        2
//        2
//        4
    }
}
