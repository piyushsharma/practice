package com.tosort;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
 */

public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) {
            return false;
        }
        HashMap<Character, String> pMap = new HashMap<Character, String>();
        HashSet<String> tSet = new HashSet<String>();

        for(int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            String item = strs[i];
            String value = pMap.get(c);
            if(value == null) {
                if(tSet.contains(item)) {
                    return false;
                }
                tSet.add(item);
                pMap.put(c, item);
                value = item;
            }
            if(!value.equals(item)) {
                return false;
            }
        }
        return true;

    }

}
