package com.dsandalgos.tophundred;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoomsTwo {


    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for(int[] in : intervals) {
            if(!pq.isEmpty() && in[0] >= pq.peek()[1]) {
                pq.remove();
            }
            pq.add(in);
        }

        return pq.size();
    }

}
