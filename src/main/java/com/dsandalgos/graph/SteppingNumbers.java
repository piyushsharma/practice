package com.dsandalgos.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * A Stepping Number is an integer such that all of its adjacent digits have an absolute difference of exactly 1.
 * For example, 321 is a Stepping Number while 421 is not.
 *
 * Given two integers low and high, find and return a sorted list of all the Stepping Numbers in the range [low, high] inclusive.
 *
 * Example 1:
 *
 * Input: low = 0, high = 21
 * Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
 *
 * Constraints:
 *
 * 0 <= low <= high <= 2 * 10^9
 */

public class SteppingNumbers {


    // 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 == add to queue
    // 10, 12 => x = q.remove, (x*10) + x + 1 ; (x*10) + x - 1 => add to queue
    // 21, 23
    // 32, 34

    public List<Integer> countSteppingNumbers(int low, int high) {

        List<Integer> result = new ArrayList<>();
        if(low == 0) {
            result.add(0);
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i <= 9; ++i) {
            q.add(i);
        }

        while(!q.isEmpty()) {

            int p = q.remove();

            if(p <= high / 10) {

                int lastInteger = p % 10;

                // since for 0, next is only 1, we don't want to add lastInteger - 1;
                if(lastInteger != 0) {
                    q.add((p * 10) + lastInteger - 1);
                }

                // since for 9, next is only 8, we don't want to add lastInteger + 1;
                if(lastInteger != 9) {
                    q.add((p * 10) + lastInteger + 1);
                }
            }

            if(p >= low && p <= high) {
                result.add(p);
            }
        }
        return result;
    }


    private final int[] step = new int[]{1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 8};
    private boolean isStep(int number) {

        if(number <= 10) {
            return true;
        }

        int prev = number % 10;
        while(number / 10 != 0) {
            number = number / 10;

            int cur = number % 10;

            int index = prev + 1;
            if(step[index - 1] != cur && step[index + 1] != cur) {
                return false;
            }

            prev = cur;
        }

        return true;
    }





}
