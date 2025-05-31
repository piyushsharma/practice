package com.dsandalgos.dp;

import java.util.Stack;

/**
 * Created by Piyush Sharma
 */

/*

Given a string containing just the characters '(' and ')',
find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.
Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

*/


public class LongestValidParentheses {

    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n <= 0) return n;

        int[] dp = new int[n];
        int max = 0;
        // dp stores the longest valid parentheses up until that index of the string
        dp[0] = 0;
        int open = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                ++open;
            }
            // open > 0 makes sure we do not access i - 1 when i == 0
            if (s.charAt(i) == ')' && open > 0) {
                // since we have at least one open parentheses present, add 2
                dp[i] = 2 + dp[i - 1];

                // add the previous valid parentheses length to this index
                // dp [ i - dp[i] ] will contain whatever valid length was observed before this stretch
                if (i - dp[i] > 0) {
                    dp[i] += dp[i - dp[i]];
                }
                --open;
            }
            if (dp[i] > max) max = dp[i];
        }

        return max;
    }

    /*
        1. Scan the string from beginning to end. If current character is '(', push its index to the stack.
        If current character is ')' and the character at the index of the top of stack is '(', we just find a
        matching pair so pop from the stack. Otherwise, we push the index of ')' to the stack.

        2. After the scan is done, the stack will only contain the indices of characters which cannot be matched.
        Then let's use the opposite side - substring between adjacent indices should be valid parentheses.

        3. If the stack is empty, the whole input string is valid. Otherwise, we can scan the stack to get longest
        valid substring as described in step 2.
     */

    public int longestValidParenthesesDP(String s) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            }
            if (s.charAt(i) == ')') {
                if (!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                } else {
                    stack.push(i);
                }
            }
        }

        if (stack.isEmpty()) return s.length();

        int result = 0;
        int end = s.length();
        // for the first element in the stack - we need to see the length of that index to the string length
        // stack, so we are pushing all break points, first one that we pop is the last break point
        while (!stack.isEmpty()) {
            int unmatchedIndex = stack.pop();
            result = Math.max(result, end - unmatchedIndex - 1);
            end = unmatchedIndex;
        }

        // end will now be the last element in the stack - we need to see the length of 0 to that index
        // which will be end
        result = Math.max(result, end);
        return result;
    }


    // the same logic as in the above function, but code is more concise
    /*
        Eg. "()()"
        stack: -1, 0,
        when we get to index 1 ")", the peek is "(" so we pop it out and see what's before "(".
        In this example it's -1. So the gap is "current_index" - (-1) = 2.
        The idea only update the result (max) when we find a "pair" and push -1 to stack first covered all edge cases.
    */
    //")()())()()("
    public int longestValidParenthesesConcise(String s) {
        Stack<Integer> stack = new Stack<>();

        int result = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); ++i) {
            // note we do size > 1 because we have pushed -1 in the beginning to handle edge cases.
            if (s.charAt(i) == ')' && stack.size() > 1 && s.charAt(stack.peek()) == '(') {
                stack.pop();
                result = Math.max(result, i - stack.peek());
            } else {
                stack.push(i); // note you push here, to get all ranges
            }
        }

        return result;
    }

    private static int longestValidLength(String str, char ch, int start, int end, int step) {
        int len = 0, maxLen = 0, count = 0;
        for (int i = start; i != end; i += step) {
            if (str.charAt(i) == ch) {
                count++;
            } else {
                if (count > 0) {
                    count--;
                    len += 2;
                    if (count == 0) maxLen = Math.max(maxLen, len);
                } else
                    len = 0;
            }
        }
        return maxLen;
    }

    /**
     * Same counter approach. Find the longest valid from left to right
     * and the longest from right to left. Return the maximum
     *
     * @param str
     * @return
     */
    private static int getLongestValidParenthesis(String str) {
        return Math.max(longestValidLength(str, '(', 0, str.length(), 1),
                longestValidLength(str, ')', str.length() - 1, -1, -1));
    }


    public static void main(String[] args) {
        LongestValidParentheses l = new LongestValidParentheses();

        System.out.println(l.longestValidParentheses(""));
        System.out.println(l.longestValidParentheses("("));

        System.out.println(l.longestValidParentheses("()"));
        System.out.println(l.longestValidParentheses("(()"));
        System.out.println(l.longestValidParentheses("()(()"));
        System.out.println(l.longestValidParentheses(")())("));
        System.out.println(l.longestValidParentheses(")())())"));
        System.out.println(l.longestValidParentheses("())"));

        System.out.println(l.longestValidParentheses(")()())"));
        System.out.println(l.longestValidParentheses(")()()()()("));

        System.out.println(getLongestValidParenthesis("((()())()(("));
    }


}
