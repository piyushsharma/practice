package com.dsandalgos.util;

import java.util.Comparator;

/**
 * Created by Piyush Sharma
 */

public class Interval {
    public int start;
    public int end;

    // for interval tree
    public int max;
    public Interval left;
    public Interval right;

    public Interval() { start = 0; end = 0; }
    public Interval(int s, int e) { start = s; end = e; }

    public Interval(int s, int e, int m) {
        start = s;
        end = e;
        max = m;
    }

    public static class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start - b.start;
        }
    }

    // A utility function to insert a new Interval Search Tree Node
    // This is similar to BST Insert.  Here the start value of interval
    // is used to maintain BST property
    public Interval insert(Interval root, Interval interval)  {
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
