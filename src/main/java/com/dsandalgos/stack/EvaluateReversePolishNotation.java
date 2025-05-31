package com.dsandalgos.stack;

import java.util.*;

/**
 * Created by Piyush Sharma on 6/13/14.
 */

/* Problem Statement:
    Evaluate the value of an arithmetic expression in Reverse Polish Notation.
    Valid operators are +, -, *, /. Each operand may be an integer or another expression.
    Some examples:
        ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
        ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
*/

interface Operator {
    int applyOperation(int x, int y);
}

public class EvaluateReversePolishNotation {


    private static final Map<Character, Operator> operatorMap = new HashMap<Character, Operator>(){
        {   put('+', (x, y) -> x + y);
            put('-', (x, y) -> x - y);
            put('*', (x, y) -> x * y);
            put('/', (x, y) -> x / y);
        }
    };

    public int evalRPN(String[] tokens) {

        Stack<Integer> s = new Stack<>();

        for(int i = 0; i < tokens.length; ++i) {
            if(operatorMap.containsKey(tokens[i])) {
                int y = s.pop();
                int x = s.pop();

                int r = operatorMap.get(tokens[i]).applyOperation(x, y);
                s.push(r);
            } else {
                s.push(Integer.parseInt(tokens[i]));
            }
        }
        return s.pop();
    }

    /* Ideally should check for exceptions as well
    *  Particularly -> Number format exception and ??
    * */
    public int evalRPNV2(String[] tokens) {
        int result = 0;

        int len = tokens.length;

        /* Used as a stack to push and pop numbers */
        List<Integer> numbers = new ArrayList<Integer>();

        for(int i = 0; i < len; ++i) {
            String op = tokens[i];

            while(!isOperator(op)) {
                numbers.add(Integer.parseInt(op));
                ++i;
                op = tokens[i];
            }

            int operand2 = numbers.remove(numbers.size() - 1);
            int operand1 = 0;
            if(numbers.size() > 0) {
                operand2 = numbers.remove(numbers.size() - 1);
            }

            if(op.equals("-")) {
                result = operand1 - operand2;
            }
            else if(op.equals("+")) {
                result = operand1 + operand2;
            }
            else if(op.equals("*")) {
                result = operand1 * operand2;
            }
            else if(op.equals("/")) {
                result = operand1 / operand2;
            }
            numbers.add(result);
        }
        // System.out.println(result);
        return result;
    }

    private boolean isOperator(String op) {
        if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))
            return true;
        return false;
    }


    public static void main(String[] args) {
//        String [] tokens =  {"2", "1", "+", "3", "*"}; // 9
        String [] tokens = {"4", "13", "5", "/", "+"};  // 6
        EvaluateReversePolishNotation s = new EvaluateReversePolishNotation();
        System.out.println(s.evalRPN(tokens));
    }

}
