package com.tosort;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters.
No two characters may map to the same character but a character may map to itself.

For example,
Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

Note:
You may assume both s and t have the same length.
*/

public class IsomorphicStringsV2 {

    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> sMap = new HashMap<Character, Character>();
        HashSet<Character> tSet = new HashSet<Character>();

        for(int i = 0; i < s.length(); ++i) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            Character sv = sMap.get(sc);
            if(sv == null) {
                if(tSet.contains(tc)) {
                    return false;
                }
                sMap.put(sc, tc);
                sv = tc;
                tSet.add(tc);
            }
            if(sv != tc) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsomorphicStringsV2 isomorphicStrings = new IsomorphicStringsV2();
        System.out.println(isomorphicStrings.isIsomorphic("egg", "add"));
        System.out.println(isomorphicStrings.isIsomorphic("foo", "bar"));
        System.out.println(isomorphicStrings.isIsomorphic("paper", "title"));
        System.out.println(isomorphicStrings.isIsomorphic("aba", "baa"));
        System.out.println(isomorphicStrings.isIsomorphic("ab", "aa"));
    }
}
