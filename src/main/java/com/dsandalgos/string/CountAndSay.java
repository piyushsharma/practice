package com.dsandalgos.string;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
The count-and-say sequence is the sequence of integers beginning as follows:
1, 11, 21, 1211, 111221, ...

1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth sequence.

Note: The sequence of integers will be represented as a string.
*/

public class CountAndSay {

    public String countAndSay(int n) {
        if(n <= 0)
            return null;

        String prev = "1";
        for(int i = 2; i <= n; ++i) {
            StringBuilder sb = new StringBuilder();
            // process the previous sequence to generate the next one
            for(int j = 0; j < prev.length(); ++j) {
                int count = 1;
                while(j + 1 < prev.length() && prev.charAt(j) == prev.charAt(j+1)) {
                    ++count;
                    ++j;
                }
                sb.append(count);
                sb.append(prev.charAt(j));
            }
            prev = sb.toString();
        }
        return prev;
    }


    public String countAndSayA(int n) {
        if(n <= 0)
            return null;

        String result = "1";

        int i = 1;
        while(i < n) {
            int count = 1;
            StringBuilder sb = new StringBuilder();

            // process the previous sequence to generate the next one
            for(int j = 1; j < result.length(); ++j) {
                if(result.charAt(j) == result.charAt(j-1)) {
                    ++count;
                } else {
                    sb.append(count);
                    sb.append(result.charAt(j-1));
                    count = 1;
                }
            }
            // append the last counted character
            sb.append(count);
            sb.append(result.charAt(result.length() - 1));
            result = sb.toString();

            ++i;
        }
        return result;
    }

    public static void main(String[] args) {
        CountAndSay c = new CountAndSay();
        System.out.println(c.countAndSay(1));
        System.out.println(c.countAndSay(2));
        System.out.println(c.countAndSay(3));
        System.out.println(c.countAndSay(4));
        System.out.println(c.countAndSay(5));
        System.out.println(c.countAndSay(6));
        System.out.println(c.countAndSay(7));
    }
}
