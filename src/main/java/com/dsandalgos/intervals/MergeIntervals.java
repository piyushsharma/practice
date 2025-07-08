package com.dsandalgos.intervals;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*

Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

*/



public class MergeIntervals {

//    public int[][] mergeIntervalArrays(int[][] intervals) {
//        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
//        List<int[]> result = new ArrayList<>();
//        for(int i = 0; i < intervals.length; ++i) {
//            // current starts after prev end
//            if(result.isEmpty() || result.getLast()[1] < intervals[i][0]) {
//                result.add(intervals[i]);
//            } else {
//                result.getLast()[1] = Math.max(intervals[i][1], result.getLast()[1]);
//            }
//        }
//        return result.toArray(new int[result.size()][]);
//    }


    /**
     * Definition for an interval
     */

    public List<Interval> merge(List<Interval> intervals) {

        Collections.sort(intervals, Comparator.comparingInt(Interval::getStart));

        LinkedList<Interval> result = new LinkedList<>();
        for(Interval interval : intervals) {

            if(result.isEmpty() || result.getLast().end < interval.start) {
                result.add(interval);
            } else {
                result.getLast().end = Math.max(result.getLast().end, interval.end);

            }
        }
        return result;
    }


    // You have two arrays which denote start and end time of interviews
    // you can be in k meetings at the same time
    // return true if there is a conflict (i.e. you have more than k overlapping meetings scheduled)
    public boolean kOverlaps(int[] start, int[] end, int k) {

        List<Interval> meetings = new ArrayList<>();

        for(int i = 0; i < start.length; ++i) {
            meetings.add(new Interval(start[i], end[i]));
        }

        // only one meeting so no conflict
        if(meetings.size() < 2) return false;

        // sort the intervals as per start
        Collections.sort(meetings, new Comparator<Object>() {
            @Override
            public int compare(Object o1, Object o2) {
                Interval a = (Interval)o1;
                Interval b = (Interval)o2;
                return a.start - b.start;
            }
        });

        PriorityQueue<Integer> conflicts = new PriorityQueue<>();

        Interval prev = meetings.get(0);
        int countConflict = 0;
        for(int i = 1; i < meetings.size(); ++i) {

            Interval cur = meetings.get(i);

            // check for overlaps
            // note if cur.start == prev.end, it is not a conflict
            if(cur.start >= prev.start && cur.start < prev.end) {
                if(conflicts.isEmpty()) {
                    conflicts.add(prev.end);
                    conflicts.add(cur.end);

                } else {
                    int minEnd = conflicts.peek();
                    while(minEnd <= cur.start) {
                        conflicts.poll();
                        --countConflict;
                        minEnd = conflicts.peek();
                    }
                    conflicts.add(cur.end);
                }

                ++countConflict;
                // k conflicts are ok, as soon as we cross that, we return true
                if(countConflict > k) return true;
            }
            prev = cur;
        }

        return false;
    }


    /*
       Merge two sorted intervals
        For example,
        A: [1,5], [10,14], [16,18]
        B: [2,6], [8,10], [11,20]
        output [1,6], [8, 20]
        // A has no overlap inside A and B has no overlap inside B
        // Write the function to merge two interval lists, output the res with no overlap
     */
    public List<Interval> mergeSortedIntervalLists(List<Interval> A, List<Interval> B ) {

        if(A == null || A.size() == 0) return B;
        if(B == null || B.size() == 0) return A;

        List<Interval> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        Interval prev = null;

        while(i < A.size() && j < B.size()) {

            prev = result.size() > 0 ? result.get(result.size() - 1) : null;
            Interval a = A.get(i);
            Interval b = B.get(j);

            if(a.end < b.start) {

                Interval cur = mergeTwoIntervals(prev, a);
                if(cur != null) {
                    result.add(cur);
                }
                ++i;

            } else if(b.end < a.start) {

                Interval cur = mergeTwoIntervals(prev, b);
                if(cur != null) {
                    result.add(cur);
                }
                ++j;

            } else {

                Interval cur = new Interval(Math.min(a.start, b.start), Math.max(a.end, b.end));
                Interval merged = mergeTwoIntervals(prev, cur);
                if(merged != null) {
                    result.add(merged);
                }
                ++i;
                ++j;
            }
        }

        while(i < A.size()) {
            prev = result.get(result.size() - 1);
            Interval cur = mergeTwoIntervals(prev, A.get(i));
            if(cur != null) result.add(cur);
            ++i;
        }

        while(j < B.size()) {
            prev = result.get(result.size() - 1);
            Interval cur = mergeTwoIntervals(prev, B.get(j));
            if(cur != null) result.add(cur);
            ++j;
        }


        return result;
    }


    public Interval mergeTwoIntervals(Interval prev, Interval cur) {
        // if we can extend previous, then just update existing interval,
        // return null
        if(prev != null && prev.start <= cur.start && prev.end >= cur.start) {
            prev.end = Math.max(cur.end, prev.end);
            return null;
        }

        // if prev is null or there is no overlap, return a new copy of cur
        return new Interval(cur.start, cur.end);
    }


    public List<List<Interval>> createInput() {
        // [1,3],[2,6],[8,10],[15,18]

//        List<Interval> input = new ArrayList<>();
//        input.add(new Interval(1,4));
//        input.add(new Interval(2,3));
//        input.add(new Interval(15,18));
//        input.add(new Interval(8,10));
//
//        return input;

        List<List<Interval>> input = new ArrayList<>();
        List<Interval> A = new ArrayList<>();
        A.add(new Interval(2,6));
        A.add(new Interval(1,3));
        A.add(new Interval(16,18));
        A.add(new Interval(20,25));
        input.add(A);

        List<Interval> B = new ArrayList<>();
        B.add(new Interval(5,6));
        B.add(new Interval(11,20));
        B.add(new Interval(22,30));
        input.add(B);

        return input;
    }


    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a.start));
        PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.end));

        for(Interval in : intervals) {
            if(!pq.isEmpty() && in.start >= pq.peek().end) {
                pq.remove();
            }
            pq.add(in);
        }

        return pq.size();
    }


    public static void main(String[] args) {

        MergeIntervals mergeIntervals = new MergeIntervals();

        List<Interval> result1 = mergeIntervals.merge(mergeIntervals.createInput().get(0));
        for(Interval interval : result1) {
            System.out.printf("[%d, %d] ", interval.start, interval.end);
        }
        System.out.printf("\n");

        List<List<Interval>> input = mergeIntervals.createInput();
        List<Interval> result = mergeIntervals.mergeSortedIntervalLists(input.get(0), input.get(1));
        for(Interval interval : result) {
            System.out.printf("[%d, %d] ", interval.start, interval.end);
        }
        System.out.printf("\n");

        int[] start = new int[]{1,1,1,1,1,1,1,1};
        int[] end = new int[]{3,4,5,6,5,6,9,10};
        System.out.println(mergeIntervals.kOverlaps(start, end, 3));

    }

    class Interval {
        public int start;
        public int end;

        // for interval tree
        public int max;
        public Interval left;
        public Interval right;

        public Interval(int s, int e) {
            start = s;
            end = e;
        }

        public Interval(int s, int e, int m) {
            start = s;
            end = e;
            max = m;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        // A utility function to insert a new Interval Search Tree Node
        // This is similar to BST Insert.  Here the start value of interval
        // is used to maintain BST property
        public Interval insert(Interval root, Interval interval) {
            // Base case: Tree is empty, new node becomes root
            if (root == null)
                return new Interval(interval.start, interval.end, interval.end);

            // Get start value of interval at root
            int start = root.start;

            // If root's start value is smaller, then new interval goes to left subtree
            if (interval.start < start) {
                root.left = insert(root.left, interval);

            } else {
                // Else, new node goes to right subtree.
                root.right = insert(root.right, interval);
            }
            // Update the max value of this ancestor if needed
            if (root.max < interval.end) {
                root.max = interval.end;
            }

            return root;
        }
    }
}
