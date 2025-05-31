package com.dsandalgos.tophundred;

import java.util.Stack;

/**
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */

public class DecodeString {

    public String decodeString(String s) {
        if(s == null || s.isEmpty()) return s;

        Stack<String> stack = new Stack<>();
        int i = 0;
        while(i < s.length()) {
            if(s.charAt(i) == ']') {

                StringBuilder temp = new StringBuilder();
                while(!stack.isEmpty()) {

                    if(stack.peek().equals("[")) {
                        stack.pop();
                        break;
                    } else {
                        temp.append(stack.pop());
                    }
                }
                String expandStr = temp.toString();

                int rep = 0;
                int place = 1;
                while(!stack.isEmpty()) {
                    int x = stack.peek().charAt(0) - '0';
                    if(x >= 0 && x < 10) {
                       rep += place * x;
                       place *= 10;
                       stack.pop();
                    } else {
                        break;
                    }
                }

                stack.push(multiply(expandStr, rep));

            } else {
                stack.push(String.valueOf(s.charAt(i)));
            }
            ++i;
        }

        StringBuilder result = new StringBuilder();
        while(!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    private String multiply(String expandStr, int rep) {
        StringBuilder res = new StringBuilder();
        while(rep > 0) {
            res.append(expandStr);
            --rep;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(new DecodeString().decodeString("3[a]2[bc]"));

        System.out.println(new DecodeString().decodeString("3[a20[c]]"));
        System.out.println(new DecodeString().decodeString("2[abc]3[cd]ef"));


    }
}
