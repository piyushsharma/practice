package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
 */

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if(s == null || s.isEmpty())
            return true;

        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        int i = 0;
        int l = s.length();
        while(i < l/2) {
            if (s.charAt(i) != s.charAt(l - 1 - i))
                return false;

            ++i;
        }

        return true;
    }


    public boolean isPalindromeFirst(String s) {
        if(s == null || s.isEmpty())
            return true;


        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; ++i) {
            if((arr[i] >= 'a' && arr[i] <= 'z') || (arr[i] >= 'A' && arr[i] <= 'Z')) {
                sb.append(Character.toLowerCase(arr[i]));
            }
            if(arr[i] >= '0' && arr[i] <= '9') {
                sb.append(arr[i]);
            }
        }
        String validStr = sb.toString();
        String reverseStr = sb.reverse().toString();
        if(validStr.equals(reverseStr)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(new ValidPalindrome().isPalindrome("1a2"));
        System.out.println(new ValidPalindrome().isPalindrome("race a car"));
    }
}
