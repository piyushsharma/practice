package com.dsandalgos.tophundred;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
 */

public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        if(n < 1) {
            return result;
        }

        parantheses("", result, 0, 0, n);

        return result;
    }


    public void parantheses(String s, List<String> result, int openBrackets, int closeBrackets, int n) {
        if(closeBrackets == n) {
            result.add(s);
            return;
        }

        if(openBrackets < n) {
            parantheses(s + "(", result, openBrackets + 1, closeBrackets, n);
        }

        if(openBrackets > closeBrackets) {
            parantheses(s + ")", result, openBrackets, closeBrackets + 1, n);
        }

   }

    public static void main(String[] args) {
        GenerateParentheses g = new GenerateParentheses();

        List<String> r = g.generateParenthesis(3);
        for (String s : r) {
            System.out.println(s);
        }
    }

}
