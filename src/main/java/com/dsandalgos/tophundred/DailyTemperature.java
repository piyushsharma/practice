package com.dsandalgos.tophundred;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of daily temperatures T, return a list such that, for each day in the input,
 * tells you how many days you would have to wait until a warmer temperature.
 * If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73],
 * your output should be [1, 1, 4, 2, 1, 1, 0, 0].
 *
 * Note: The length of temperatures will be in the range [1, 30000].
 * Each temperature will be an integer in the range [30, 100].
 *
 */

public class DailyTemperature {

    public int[] dailyTemperatures(int[] T) {
        if(T == null || T.length == 0) {
            return T;
        }

        int[] result = new int[T.length];

        Map<Integer, Integer> tempToIndex = new HashMap<>();

        for(int i = T.length - 1; i >= 0; --i) {

            if(i == T.length - 1) {
                result[i] = 0;
            } else {

                result[i] = findWarmerDay(T[i], i, tempToIndex);

            }
            tempToIndex.put(T[i], i);
        }
        return result;
    }

    // private int getHigherTempIndex(int[] T, int fromIndex, int temp) {
    //     for(int i = fromIndex; i < T.length; ++i) {
    //         if(T[i] > temp) {
    //             return i - fromIndex + 1;
    //         }
    //     }
    //     return 0;
    // }


    private int findWarmerDay(int temp, int index, Map<Integer, Integer> tempToIndex) {
        int minIndex = Integer.MAX_VALUE;
        for(int i = temp + 1; i <= 100; ++i) {
            if(tempToIndex.get(i) != null) {

                minIndex = Math.min(tempToIndex.get(i) - index, minIndex);
            }
        }


        return minIndex == Integer.MAX_VALUE ? 0 : minIndex;
    }
}
