package com.dsandalgos.hash;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */


/*
Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false.

Note:
You may assume the string contains only lowercase alphabets.

Follow up:
What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if(s == null || t == null || s.length() != t.length())
            return false;

        char[] a = s.toCharArray();
        Arrays.sort(a);
        char[] b = t.toCharArray();
        Arrays.sort(b);

        for(int i = 0; i < a.length; ++i) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    // assuming only lower alphabets
    public boolean isAnagramHash(String s, String t) {
        int[] sc = new int[26];
        int[] tc = new int[26];

        for (int i = 0; i < s.length(); ++i) {
            sc[s.charAt(i) - 'a'] += 1;
        }
        for (int i = 0; i < t.length(); ++i) {
            tc[t.charAt(i) - 'a'] += 1;
        }

        for (int i = 0; i < 26; ++i) {
            if (sc[i] != tc[i]) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        ValidAnagram v = new ValidAnagram();
        System.out.println(v.isAnagramHash("anagram", "nagaram"));
    }

}
