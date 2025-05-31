package com.dsandalgos.string;

/**
 * Created by Piyush Sharma on 6/13/14.
 */

/*
Problem Statement:
    Given an input string, reverse the string word by word.
    For example:
        Given s = "the sky is blue",
        return "blue is sky the".
    Clarification:
        What constitutes a word?
            A sequence of non-space characters constitutes a word.
        Could the input string contain leading or trailing spaces?
            Yes. However, your reversed string should not contain leading or trailing spaces.
        How about multiple spaces between two words?
            Reduce them to a single space in the reversed string.
*/
public class ReverseWordsInAString {

    /* O(n) runtime, O(n) space, two passes: 1. split and create array 2. iterating array */
    public String reverseWords(String s) {
        if(s == null || s.length() == 0)
            return s;

        /* split to words by space */
        String[] arr = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; --i) {
            if (!arr[i].trim().equals("")) {
                sb.append(arr[i]).append(" ");
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }


    // The input string does not contain leading or trailing spaces and the words are separated by a single space.
    // we can reverse words in place
    // consider input is a char array of the string
    public String reverseWords(char[] s) {
        reverseSection(s, 0, s.length - 1);

        int j = 0;
        for(int i = 0; i < s.length; ++i) {
            if(s[i] == ' ') {
                reverseSection(s, j, i-1);
                j = i+1;
            }
        }
        // reverse the last word
        reverseSection(s, j, s.length - 1);
        return String.valueOf(s);
    }

    public void reverseSection(char[] s, int start, int end) {
        while(start < end) {
            char temp = s[end];
            s[end] = s[start];
            s[start] = temp;
            ++start;
            --end;
        }
    }



    /* Not so fast Method */
    public String reverseWordsV2(String s) {
        String trimmedString = s.trim();
        String[] words = trimmedString.split(" ");

        int len = words.length;
        String reversedWords = "";
        for(int i = len - 1; i >= 0; --i) {
            if(words[i].length() == 0)
                continue;
            reversedWords += words[i].trim();
            if(i != 0) {
                reversedWords += " ";
            }
        }
        return reversedWords;
    }


    public static void main (String args[]) {
        ReverseWordsInAString s = new ReverseWordsInAString();
        System.out.println(s.reverseWords("   the    sky is blue!"));

        System.out.println(s.reverseWords("the sky is blue".toCharArray()));
        System.out.println(s.reverseWords("test this with a different string".toCharArray()));
    }
}
