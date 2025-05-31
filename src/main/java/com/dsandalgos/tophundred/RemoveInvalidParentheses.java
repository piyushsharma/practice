package com.dsandalgos.tophundred;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 *
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 *
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 * Example 2:
 *
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 * Example 3:
 *
 * Input: ")("
 * Output: [""]
 */

public class RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {

        Set<String> result = new HashSet<>();

        removeAndCheck(s, "", 0, 0, 0, 0, result);

        return new ArrayList<>(result);
    }

    private int MAX = Integer.MAX_VALUE;

    private void removeAndCheck(String orig, String cur, int lc,
                                int rc, int removedCharsCount, int index,
                                Set<String> result) {

        if(index == orig.length()) {

            if(removedCharsCount <= MAX && lc == rc) {
                MAX = removedCharsCount;
                result.add(cur);
            }
            return;
        }

        if(orig.charAt(index) == '(') {

            // include it
            removeAndCheck(orig, cur + "(", lc + 1, rc, removedCharsCount, index + 1, result);

            // exclude it
            removeAndCheck(orig, cur, lc, rc, removedCharsCount + 1, index + 1, result);

        } else if (orig.charAt(index) == ')') {

            // pruning -- only include if lc > rc
            if(lc > rc) {
                // include it
                removeAndCheck(orig, cur + ")", lc, rc + 1, removedCharsCount, index + 1, result);
            }
            // exclude it
            removeAndCheck(orig, cur, lc, rc, removedCharsCount + 1, index + 1, result);

        } else {

            // always include
            removeAndCheck(orig, cur + orig.charAt(index), lc, rc, removedCharsCount, index + 1, result);
        }
    }


}
