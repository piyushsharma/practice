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


    public List<String> addOperatorsV2(String num, int target) {
        List<String> result = new ArrayList<>();
        if(num == null || num.isEmpty()) return result;
        dfs(result, num, target, "", 0, 0, 0);
        return result;
    }

    /**
     * @param result: result list to store valid expressions
     * @param num: input num candidates
     * @param target: input target number
     * @param expr: current expression string
     * @param calcVal: current calculation value
     * @param preVNum: previous number, in order to multiply current number if we want to put * between preNum and curNum
     * @param pos: current index in the input num array
     * */
    public void dfs(List<String> result, String num, int target, String expr, long calcVal, long preVNum, int pos) {
        if (pos == num.length()) {
            if (calcVal == target) {
                result.add(expr);
            }
            return;
        }

        // start from first index of current position in num string, try all possible length of nums
        for (int i = pos; i < num.length(); i++) {
            // corner case: if current position is 0, we can only use it as a single digit number, should be 0
            // if it is not a single digit number with leading 0, it should be considered as an invalid number
            if (i != pos && num.charAt(pos) == '0') {
                break;
            }
            long curNum = Long.parseLong(num.substring(pos, i + 1));

            // position 0 should be considered individually, since it does not have any operand character before curNum
            if (pos == 0) {
                dfs(result, num, target, expr + curNum, curNum, curNum, i + 1);
            } else {
                dfs(result, num, target, expr + "+" + curNum, calcVal + curNum, curNum, i + 1);
                dfs(result, num, target, expr + "-" + curNum, calcVal - curNum, -curNum, i + 1);
                // trick part: to calculate multiplication, we should subtract previous number,
                // and then add current multiplication result to the subtraction result
                dfs(result, num, target, expr + "*" + curNum, calcVal - preVNum + preVNum * curNum, preVNum * curNum, i + 1);
            }
        }
    }
}
