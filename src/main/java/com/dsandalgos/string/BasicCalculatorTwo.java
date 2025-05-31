package com.dsandalgos.string;

import java.util.Stack;

/**
 * Created with IntelliJ IDEA
 * User: Piyush Sharma
 */

/*
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:
"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5
Note: Do not use the eval built-in library function.
*/

public class BasicCalculatorTwo {

    public int calculateWithBracket(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }

        String in = s.replaceAll(" ", "");

        Stack<Integer> calc = new Stack<>();
        int i = 0;
        int result = 0;
        String curNumStr = "";
        int sign = 1;

        while(i < in.length()) {

            char c = in.charAt(i);

            if(c == '+') {
                result += parseInt(curNumStr) * sign;
                curNumStr = "";
                sign = 1;

            } else if (c == '-') {

                result += parseInt(curNumStr) * sign;
                curNumStr = "";
                sign = -1;

            } else if(c >= '0' && c <= '9') {
                curNumStr += c;

            } else if(c == '(') {

                calc.push(result);
                calc.push(sign);
                sign = 1;
                curNumStr = "";
                result = 0;

            } else if(c == ')') {

                result += parseInt(curNumStr) * sign;
                result *= calc.pop(); // sign
                result += calc.pop();
                curNumStr = "";
            }

            ++i;
        }

        result += parseInt(curNumStr) * sign;

        return result;
    }

    private int parseInt(String x) {
        if(x.equals("")) return 0;
        return Integer.parseInt(x);
    }



    public int calculateWithBracketNum(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }

        String in = s.replaceAll(" ", "");

        Stack<Integer> calc = new Stack<>();
        int i = 0;
        int result = 0;
        int curNum = 0;
        int sign = 1;

        while(i < in.length()) {

            char c = in.charAt(i);

            if(c == '+') {
                result += curNum * sign;
                curNum = 0;
                sign = 1;

            } else if (c == '-') {

                result += curNum * sign;
                curNum = 0;
                sign = -1;

            } else if(c >= '0' && c <= '9') {
                curNum = (curNum * 10) + c - '0';

            } else if(c == '(') {

                calc.push(result);
                calc.push(sign);
                sign = 1;
                curNum = 0;
                result = 0;

            } else if(c == ')') {

                result += curNum * sign;
                result *= calc.pop(); // sign
                result += calc.pop();
                curNum = 0;
            }

            ++i;
        }

        if(curNum != 0) {
            result += curNum * sign;
        }

        return result;
    }


    public int calculate(String s) {
        if(s == null || s.isEmpty())
            return 0;

        // remove all spaces
        s = s.replaceAll(" ", "");
        int len = s.length();

        Stack<Integer> stack = new Stack();
        int curIndex = 0;

        // push the first number before the loop
        curIndex = pushNextNumber(s, curIndex, len, stack);
        // curIndex must now be pointing to an operator

        while(curIndex < len) {

            char sign = s.charAt(curIndex);
            // we know that all numbers are positive
            curIndex = pushNextNumber(s, curIndex+1, len, stack);

            // if sign == '+', don't need to do anything as the previous line must have pushed the number in stack
            if (sign == '-') {
                int curVal = stack.pop();
                stack.push(-curVal);
            } else if (sign == '*') {
                stack.push(stack.pop() * stack.pop());
            } else if (sign == '/') {
                int divider = stack.pop();
                stack.push(stack.pop() / divider);
            }
        }

        int exprValue = 0;
        while(!stack.isEmpty()) {
            exprValue += stack.pop();
        }
        return exprValue;
    }

    public int pushNextNumber(String s, int start, int end, Stack<Integer> stack) {
        int nextNum = 0;

        while(start < end) {
            char ch = s.charAt(start);
            if(ch == '*' || ch == '+' || ch == '-' || ch == '/') {
                break;
            }
            nextNum = nextNum * 10 + s.charAt(start) - '0';
            ++start;
        }
        stack.push(nextNum);
        return start;
    }


    public int calculateV2(String s) {
        if(s == null || s.isEmpty())
            return 0;

        // remove all white spaces  -- removing it in the for loop itself
        // s = s.replaceAll(" ", "");
        int len = s.length();

        Stack<Integer> stack = new Stack();
        int num = 0;
        // given that all numbers are positive
        // we do this because we want to push the first number before we encounter the first operator
        char sign = '+';
        for(int i = 0; i < len; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + (s.charAt(i) - '0');
            }
            // this is required because we want to push the last number (i == len -1) also
            if((!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ') || i == len - 1) {
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '-') {
                    stack.push(-num);
                }
                if (sign == '*') {
                    stack.push(stack.pop() * num);
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int result = 0;
        while(!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        BasicCalculatorTwo b = new BasicCalculatorTwo();
//        System.out.println(b.calculate(" 42 "));
//        System.out.println(b.calculate("3+2*2"));
//        System.out.println(b.calculate(" 3/2 "));
//        System.out.println(b.calculate(" 3+5 / 2 "));
//        System.out.println(b.calculate(" 3+5 / 2 + 1 * 5"));
//        System.out.println(b.calculate("1"));
//        System.out.println(b.calculate("1 +1 +1"));
//        System.out.println(b.calculate("1*3 + 4*5"));


        System.out.println(b.calculateWithBracket("2 - 1 + 2"));
        System.out.println(b.calculateWithBracket("((1+4+5+6) - 3) + (6+8)"));
        System.out.println(b.calculateWithBracket("2 - (5 - 6)"));
        System.out.println(b.calculateWithBracket(" 1 + 1 "));
    }
}
