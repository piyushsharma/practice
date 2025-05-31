package com.dsandalgos.string;

/*

Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True

Example 2:
Input: "abca"
Output: True

Explanation: You could delete the character 'c'.
Note: The string will only contain lowercase characters a-z. The maximum length of the string is 50000.

*/

public class ValidPalindromeTwo {

    public boolean validPalindrome(String s) {
        if(s == null || s.isEmpty())
            return true;

        int i = 0;
        int l = s.length();
        while(i < l/2) {
            if (s.charAt(i) != s.charAt(l - 1 - i)) {

//                return isPalindrome(s.substring(i+1, l-i)) || isPalindrome(s.substring(i, l - i - 1));

                return isPalindrome(s, i+1, l-i-1) || isPalindrome(s, i, l - i - 2);
            }
            ++i;
        }

        return true;
    }

    public boolean isPalindrome(String s, int start, int end) {
        int l = start + (end - start)/2;

        while(start <= l) {
            if(s.charAt(start) != s.charAt(end)) {
                return false;
            }
            ++start;
            --end;
        }
        return true;
    }


    public boolean isPalindrome(String s) {
        if(s.equals(new StringBuilder(s).reverse().toString())) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.println(new ValidPalindromeTwo().validPalindrome("AbA"));
//        System.out.println(new ValidPalindromeTwo().validPalindrome("AbbA"));
        System.out.println(new ValidPalindromeTwo().validPalindrome("AbbAc"));
        System.out.println(new ValidPalindromeTwo().validPalindrome("abc"));
    }
}
