package com.dsandalgos.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

For example,
Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

The number of ways decoding "12" is 2.
 */

public class DecodeWays {

    // only save count // dp way
    public int decodeWays(String s) {

        if(s == null || s.isEmpty() || s.charAt(0) == '0') return 0;

        int len = s.length();

        // relationship => dp[n] = dp[n-1] + dp[n-2]
        int[] count = new int[len + 1];

        // empty string can be decoded only in one way
        // Note that this marks an empty string not string with length 1
        count[0] = 1;

        // we handled charAt(0) == '0' before, so we have a non-zero number at index 0
        // note count[1] stores solution for 1 character (index 0)
        count[1] = 1;

        // go until i = len
        for(int i = 2; i <= len; ++i) {
            int oneDigit = s.charAt(i-1) - '0';
            int twoDigits = (s.charAt(i-2) - '0')*10 + oneDigit;

            if(1 <= oneDigit && oneDigit <= 9) {
                count[i] += count[i-1];
            }
            if(10 <= twoDigits && twoDigits <= 26) {
                count[i] += count[i-2];
            }
        }
        return count[len];
    }

    // Get all decode strings in output // times out on leetcode
    public List<String> decodeWaysList(String s) {
        List<String> result = new ArrayList<>();
        if(s == null || s.isEmpty())
            return result;


        String[] mapping = new String[]{"", "A", "B", "C", "D", "E", "F", "G", "H", "I",
                                            "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                                            "S", "T", "U", "V", "W", "X", "Y", "Z"};

        decodeWays(s, "", 0, s.length(), result, mapping);
        return result;
    }

    public void decodeWays(String message, String curStr, int start, int end, List<String> result, String[] mapping) {

        if(start >= end) {
            if (!curStr.isEmpty()) {
                result.add(curStr);
            }
            return;
        }

        int val = message.charAt(start) - '0';
        if(val == 0)
            return;

        decodeWays(message, curStr + mapping[val], start + 1, end, result, mapping);
        if(start + 1 < end) {
            val = val * 10 + message.charAt(start + 1) - '0';
            if(val >= 1 && val <= 26) {
                decodeWays(message, curStr + mapping[val], start + 2, end, result, mapping);
            }
        }
    }

    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.decodeWays(""));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("100"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("01"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("0"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("001"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("1"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("101"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("101012"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("12345"));
        System.out.println("-----------------");
        System.out.println(d.decodeWays("47575625458446174945557745813412115112968167865867877552577411785" +
                "99337186486723247528324612117156948"));
    }
}
