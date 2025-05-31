package com.dsandalgos.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add binary operators (not unary) +, -, or * between
 * the digits so they evaluate to the target value.
 *
 * Example 1:
 *
 * Input: num = "123", target = 6
 * Output: ["1+2+3", "1*2*3"]
 * Example 2:
 *
 * Input: num = "232", target = 8
 * Output: ["2*3+2", "2+3*2"]
 * Example 3:
 *
 * Input: num = "105", target = 5
 * Output: ["1*0+5","10-5"]
 * Example 4:
 *
 * Input: num = "00", target = 0
 * Output: ["0+0", "0-0", "0*0"]
 * Example 5:
 *
 * Input: num = "3456237490", target = 9191
 * Output: []
 */


public class ExpressionAddOperators {

    private List<String> result = new ArrayList<>();
    private long target;
    private String num;

    public List<String> addOperators(String num, int target) {
        if(num == null || num.length() == 0) return result;

        this.target = target;
        this.num = num;

        addOperators(0, 0,0,0, new ArrayList<>());

        return result;
    }

    public void addOperators(int curIndex, long prevOperand, long curOperand, long expVal, List<String> ops) {

        if(curIndex == num.length()) {
            // If the final value == target expected AND
            // no operand is left unprocessed
            if(expVal == target && curOperand == 0) {
                StringBuilder sb = new StringBuilder();
                ops.subList(1, ops.size()).forEach(v -> sb.append(v));
                result.add(sb.toString());
            }
            return;
        }

        // Extending the current operand by one digit
        curOperand = curOperand * 10 + Character.getNumericValue(num.charAt(curIndex));
        String currentValue = Long.toString(curOperand);

        // To avoid cases where we have 1 + 05 or 1 * 05 since 05 won't be a
        // valid operand. Hence this check
        if (curOperand > 0) {

            // NO OP recursion
            addOperators(curIndex + 1, prevOperand, curOperand, expVal, ops);
        }

        // ADDITION
        ops.add("+");
        ops.add(currentValue);
        addOperators(curIndex + 1, curOperand, 0, expVal + curOperand, ops);
        ops.remove(ops.size() - 1);
        ops.remove(ops.size() - 1);

        if (ops.size() > 0) {

            // SUBTRACTION
            ops.add("-");
            ops.add(currentValue);
            addOperators(curIndex + 1, -curOperand, 0, expVal - curOperand, ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);

            // MULTIPLICATION
            ops.add("*");
            ops.add(currentValue);
            addOperators(
                    curIndex + 1,
                    curOperand * prevOperand,
                    0,
                    expVal - prevOperand + (curOperand * prevOperand),
                    ops);
            ops.remove(ops.size() - 1);
            ops.remove(ops.size() - 1);
        }

    }
}
