package com.dsandalgos.string;

public class ShortestPalindrome {


    public String shortestPalindrome(String s) {

        String revStr = new StringBuilder(s).reverse().toString();


        for(int i = 0; i < s.length(); ++i) {

            if(s.startsWith(revStr.substring(i))) {
                return revStr.substring(0,i) + s;
            }

        }

        return revStr + s;

    }
}
