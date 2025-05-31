package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Write a function to find the longest common prefix string amongst an array of strings.
*/

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {

        String result = "";
        int arrLen = strs.length;

        if(arrLen == 0)
            return result;

        // initialize as the min length string
        result = strs[0];

        // check for the longest common prefix of this min length string with all other elements
        for(int i = 1; i < arrLen; ++i) {
            String curStr = strs[i];
            int j = 0;

            int lenToCheck = Math.min(result.length(), curStr.length());
            while(j < lenToCheck && result.charAt(j) == curStr.charAt(j)) {
                ++j;
            }
            result = result.substring(0, j);
        }
        return result;
    }

    public static void main(String[] args) {
        LongestCommonPrefix l = new LongestCommonPrefix();
        String[] strs = {"", "ab", "ab", "abc", ""};
//        String[] strs = {"aa", "a"};
        System.out.println(l.longestCommonPrefix(strs));
    }

}
