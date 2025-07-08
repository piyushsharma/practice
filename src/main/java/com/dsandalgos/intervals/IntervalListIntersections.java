package com.dsandalgos.intervals;

/**
 * You are given two lists of closed intervals, firstList and secondList, 
 * where firstList[i] = [starti, endi] and secondList[j] = [startj, endj]. 
 * Each list of intervals is pairwise disjoint and in sorted order.

 * Return the intersection of these two interval lists.
 *
 * A closed interval [a, b] (with a <= b) denotes the set of real numbers x with a <= x <= b.
 * The intersection of two closed intervals is a set of real numbers that are either empty or represented as a closed interval. 
 * For example, the intersection of [1, 3] and [2, 4] is [2, 3].
 *
 * Example 1:
 *
 * Input: firstList = [[0,2],[5,10],[13,23],[24,25]], secondList = [[1,5],[8,12],[15,24],[25,26]]
 * Output: [[1,2],[5,5],[8,10],[15,23],[24,24],[25,25]]
 *
 * Example 2:
 *
 * Input: firstList = [[1,3],[5,9]], secondList = []
 * Output: []
 *
*/

import java.util.ArrayList;
import java.util.List;

class IntervalListIntersection {


    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        List<int[]> result = new ArrayList<>();

        for (int i = 0, j = 0; i < firstList.length && j < secondList.length; ) {

            int s1 = firstList[i][0];
            int e1 = firstList[i][1];

            int s2 = secondList[j][0];
            int e2 = secondList[j][1];

            int low = Math.max(s1, s2);
            int high = Math.min(e1, e2);

            if(low <= high) {
                result.add(new int[]{low, high});
            }

            if(e1 < e2) {
                ++i;
            } else {
                ++j;
            }
        } 
        
        return result.toArray(new int[result.size()][]);
    }

    public int[][] intervalIntersectionV2(int[][] firstList, int[][] secondList) {

        List<int[]> result = new ArrayList<>();

        for (int i = 0, j = 0; i < firstList.length && j < secondList.length; ) {

            int s1 = firstList[i][0];
            int e1 = firstList[i][1];

            int s2 = secondList[j][0];
            int e2 = secondList[j][1];

            // if(e1 < s2) {
            //     ++i;
            // } else if(e2 < s1) {
            //     ++j;
            // } else if (s1 < s2) {
                
            // }

            if(s1 <= s2 && s2 <= e1) {                
                result.add(new int[]{s2, Math.min(e1, e2)});

                if(e1 <= e2) {
                    ++i;
                } else {
                    ++j;
                }
            } else if (s2 <= s1 && s1 <= e2) {
                result.add(new int[]{s1, Math.min(e1, e2)});

                if(e1 <= e2) {
                    ++i;
                } else {
                    ++j;
                }
            } else if (e1 < s2) {
                ++i;
            } else if (e2 < s1) {
                ++j;
            }
        }

        int[][] answer = new int[result.size()][2];
        int i = 0;
        for (int[] item : result) {
            answer[i++] = item; 
        }
        
        return answer;
    }
}