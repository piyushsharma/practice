package com.dsandalgos.tophundred;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
*/

public class ValidParantheses {


    private static final Map<Character, Character> map =
            new HashMap<Character, Character>() {{
                put('(', ')');
                put('{', '}');
                put('[', ']');
            }};


    public boolean isValid(String s) {
        if (s == null)
            return true;

        Stack<Character> storeOpenParantheses = new Stack<>();
        for(int i = 0; i < s.length(); ++i) {
            char curChar = s.charAt(i);
            if(map.containsKey(curChar)) {
                storeOpenParantheses.push(curChar);
            } else {

                if(storeOpenParantheses.isEmpty()) return false;
                char paranthesesInStack = storeOpenParantheses.pop();
                if (map.get(paranthesesInStack) != curChar) return false;
            }
        }

        return storeOpenParantheses.isEmpty();
    }

    public boolean isValidV2(String s) {
        if(s == null)
            return true;

        int len = s.length();
        Stack<Character> r = new Stack();
        for(int i = 0; i < len; ++i) {
            char x = s.charAt(i);
            if(x == '(' || x == '{' || x == '[') {
                r.push(x);
            } else {
                if(r.empty()) return false;
                char y = r.pop();
                if(!validBrackets(y, x)) {
                    return false;
                }
            }
        }
        return r.isEmpty();
    }

    private boolean validBrackets(char x, char y) {
        return (x == '(' && y == ')')
                || (x == '{' && y == '}')
                || (x == '[' && y == ']');
    }

    public static void main(String[] args) {
        System.out.println(new ValidParantheses().isValidV2("(){}[]"));
        System.out.println(new ValidParantheses().isValidV2("("));
        System.out.println(new ValidParantheses().isValidV2("({}[])"));
        System.out.println(new ValidParantheses().isValidV2("){}[]"));
        System.out.println(new ValidParantheses().isValidV2("{(((())))}[]"));
    }
}
