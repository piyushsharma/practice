package com.dsandalgos.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestArithmeticSubsequenceOfGivenDifference {


    public int longestSubsequence(int[] arr, int difference) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < arr.length; ++i) {
            map.put(arr[i], map.getOrDefault(arr[i] - difference, 0) + 1);
            max = Math.max(max, map.get(arr[i]));
        }
        return max;
    }


}
