package com.dsandalgos.string;

/**
 * Created by Piyush Sharma
 */

/*
Given a string, that contains special character together with alphabets (‘a’ to ‘z’ and ‘A’ to ‘Z’),
reverse the string in a way that special characters are not affected.

Examples:
Input:   str = "a,b$c"
Output:  str = "c,b$a"
Note that $ and , are not moved anywhere.
Only subsequence "abc" is reversed

Input:   str = "Ab,c,de!$"
Output:  str = "ed,c,bA!$"

*/


public class ReverseStringWithoutSpecialChars {

    public boolean validChar(char ch) {
        if((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
            return true;
        }

        return false;
    }

    public String reverseNonSpecialChars(String input) {

        char[] inputChars = input.toCharArray();

        int start = 0;
        int end = inputChars.length - 1;

        while(start < end) {

            if(!validChar(inputChars[start])) {
                ++start;
            } else if(!validChar(inputChars[end])) {
                --end;
            }
            else {
                char temp = inputChars[start];
                inputChars[start] = inputChars[end];
                inputChars[end] = temp;
                ++start;
                --end;
            }
        }
        return new String(inputChars);
    }

    public static void main(String[] args) {
        ReverseStringWithoutSpecialChars r = new ReverseStringWithoutSpecialChars();

        System.out.println(r.reverseNonSpecialChars("Ab,c,de!$"));
        System.out.println(r.reverseNonSpecialChars("a,b$c"));
    }

}
