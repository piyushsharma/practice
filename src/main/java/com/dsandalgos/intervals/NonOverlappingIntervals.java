package com.dsandalgos.intervals;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * Given an array of intervals intervals where intervals[i] = [starti, endi],
 * 
 *  return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.

 Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104

 */


public class NonOverlappingIntervals {
    

    public int eraseOverlapIntervals(int[][] intervals) {
        
        // sort by end time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        // counts non-overlaps
        int count = 1;
        int[] prev = intervals[0];

        for(int i = 1; i < intervals.length; i++) {
    
            int[] cur = intervals[i];

            // if prev ends before the current one, no overlap
            if(prev[1] <= cur[0]) {
                prev = cur;
                ++count;
            }
        }
        return intervals.length - count;    
    }

    public int eraseOverlapIntervalsV2(int[][] intervals) {
    
        if(intervals.length < 2) {
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int count = 0;
        int[] prev = intervals[0];

        for(int i = 1; i < intervals.length; i++) {
    
            int[] cur = intervals[i];

            int start = Math.max(prev[0], cur[0]);
            int end = Math.min(prev[1], cur[1]);

            if(start < end) {
                count++;

                if(prev[1] > cur[1]) {
                    prev = cur;
                }
     
            } else {

                // no overlap
                prev = cur;
            }
            
        }

        return count;
    
    }



}
