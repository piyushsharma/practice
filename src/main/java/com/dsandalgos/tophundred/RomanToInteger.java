package com.dsandalgos.tophundred;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a roman numeral, convert it to an integer.
Input is guaranteed to be within the range from 1 to 3999.
*/

public class RomanToInteger {

    Map<Character, Integer> T = new HashMap<Character, Integer>() {
        {
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }
    };

    public int romanToInt(String s) {

        // add the last char
        int sum = T.get(s.charAt(s.length() - 1));
        for(int i = s.length() - 2; i >= 0; --i) {
            if(T.get(s.charAt(i)) < T.get(s.charAt(i + 1))) {
                sum -= T.get(s.charAt(i));
            } else {
                sum += T.get(s.charAt(i));
            }
        }
        return sum;
    }

    public int romanToIntV1(String s) {

        if(s == null || s.isEmpty())
            return 0;

        int prev = 0;
        int len = s.length();
        int result = 0;

         for(int i = 0; i < len; ++i) {
            int cur = T.get(s.charAt(i));
            result += cur;
            if (prev < cur) {
                // subtract prev twice because it must have already been added in the previous step once
                // since roman representation is descending (prev > cur) unless we intend to subtract
                result = result - (2 * prev);
            }
            prev = cur;
        }
        return result;
    }


    public int romanToIntV2(String s) {

        if(s == null || s.isEmpty())
            return 0;

        Map<Character, Integer> rToI = new HashMap<Character, Integer>();
        rToI.put('I', 1);
        rToI.put('V', 5);
        rToI.put('X', 10);
        rToI.put('L', 50);
        rToI.put('C', 100);
        rToI.put('D', 500);
        rToI.put('M', 1000);

        int result = 0;

        int len = s.length();
        for(int i = 0; i < len; ++i) {
            int v1 = rToI.get(s.charAt(i));
            if(i + 1 < len) {
                int v2 = rToI.get(s.charAt(i + 1));

                if (v1 >= v2) {
                    result += v1;
                } else {
                    result += v2 - v1;
                    ++i;
                }
            } else {
                result += v1;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomanToInteger r = new RomanToInteger();

        System.out.println(r.romanToInt("I"));
        System.out.println(r.romanToInt("II"));
        System.out.println(r.romanToInt("IV"));
        System.out.println(r.romanToInt("VI"));
        System.out.println(r.romanToInt("VII"));
        System.out.println(r.romanToInt("VIIC"));
        System.out.println(r.romanToInt("VCM"));
        System.out.println(r.romanToInt("VIIM"));
        System.out.println(r.romanToInt("MCMXCVI")); // 1996

        assert(7 == r.romanToInt("VII"));
        assert(184 == r.romanToInt("CLXXXIV"));
        assert(9 == r.romanToInt("IX"));
        assert(40 == r.romanToInt("XL"));
        assert(60 == r.romanToInt("LX"));
        assert(1500 == r.romanToInt("MD"));
        assert(400 == r.romanToInt("CD"));
        assert(1900 == r.romanToInt("MCM"));
        assert(9919 == r.romanToInt("MMMMMMMMMCMXIX"));
        if (args.length == 1) {
            System.out.println(args[0] + " equals to " + r.romanToInt(args[0]));
        }
    }
}
