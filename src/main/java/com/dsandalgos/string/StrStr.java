package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Implement strStr().
Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
*/

public class StrStr {

    // Read up
    // http://www.geeksforgeeks.org/pattern-searching-set-7-boyer-moore-algorithm-bad-character-heuristic/
    // It has links to naive/KMP/Rabin-Karp/BoyerMoore implementations, advanced stuff


    // Basic implementation with O(m(n-m+1)) ~ O(mn) complexity

    // both empty return 0;
    // haystack empty, needle not empty return -1
    // return first match (if more matches present)
    public int strStr(String haystack, String needle) {
        // handles all corner cases, cleaner solution, except if haystack/needle are null
        for(int i = 0; ; ++i ) {
            for (int j = 0; ; ++j) {
                if(j == needle.length()) return i;
                if(i + j == haystack.length()) return -1;
                if(haystack.charAt(i+j) != needle.charAt(j)) break;
            }
        }
    }


    // naive approach
    // Complexity = O(m(n-m+1)) ; m = length of needle, n = length of haystack
    public int strStrExplained(String haystack, String needle) {
        // because in actual strstr, if needle is empty, return the full haystack
        if(needle == null || needle.isEmpty())
            return 0;

        if(haystack == null)
             return -1;

        int nlen = needle.length();
        int hlen = haystack.length();

        // run the loop for (hlen - nlen + 1) times because after that nlen > hlen, so cannot match
        for(int i = 0; i < hlen - nlen + 1; ++i) {
             int j = 0;
             while(j < nlen && j < hlen && haystack.charAt(i+j) == needle.charAt(j))
                ++j;

             if(j == needle.length())
                 return i;
        }
        return -1;
    }



    public static void main(String[] args) {
        StrStr s = new StrStr();
        System.out.println(s.strStr("a","a"));
        System.out.println(s.strStr("a","aa"));
        System.out.println(s.strStr("abcsd","s"));
        System.out.println(s.strStr("abdsdsdsdasdsadasd","ds"));
    }
}
