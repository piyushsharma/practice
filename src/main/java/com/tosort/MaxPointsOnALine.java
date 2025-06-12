package com.tosort;

/**
 * Created by Piyush Sharma on 6/13/14.
 */

/*
Problem Statement:
    Given n points on a 2D plane, find the maximum number of points that lie on the same straight line.
*/

public class MaxPointsOnALine {

    public int maxPoints(Point[] points) {
        int maxResult = 0;
        int result = 0;
        int count = points.length;
        if(count < 2)
            return count;
        for(int i = 0; i < count; ++i) {
            for(int j = i+1; j < count; ++j) {
                result = checkOtherPoints(points, i, j);
                if(result > maxResult) {
                    maxResult = result;
                }
            }
        }
        return maxResult;
    }

    /* Finds out all other points lying on the line made by points p1 and p2 */
    private int checkOtherPoints(Point[] points, int p1, int p2) {
        int res = 0;
        Point lineP1 = points[p1];
        Point lineP2 = points[p2];
        for(int i = 0; i < points.length; ++i) {
            Point checkP = points[i];
            if(i == p1 || i == p2) {
                res++;
                continue;
            }
            /* If the x for both are same, third point should also have the same
                x to be on the same line, cannot follow the
                (x2 - x1)·(y - y1) = (y2 - y1)·(x - x1)
                logic because it will always make both sides 0
            */
            if(lineP1.x == lineP2.x) {
                if(checkP.x == lineP1.x) {
                    res++;
                }
                /* If x is different, not on the same line, so continue */
                continue;
            }
            /* (x2 - x1)·(y - y1) = (y2 - y1)·(x - x1) */
            float lhs = (lineP2.x - lineP1.x)*(checkP.y - lineP1.y);
            float rhs = (lineP2.y - lineP1.y)*(checkP.x - lineP1.x);
            if(lhs == rhs) {
                res += 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Point[] p = new Point[3];
        p[0] = new Point(1,1);
        p[1] = new Point(1,1);
        p[2] = new Point(1,1);
//        Point[] p = new Point[9];
//        p[0] = new Point(84,250);
//        p[1] = new Point(0,0);
//        p[2] = new Point(1,0);
//        p[3] = new Point(0, -70);
//        p[4] = new Point(0, -70);
//        p[5] = new Point(1, -1);
//        p[6] = new Point(21,10);
//        p[7] = new Point(42,90);
//        p[8] = new Point(-42,-230);
        MaxPointsOnALine s = new MaxPointsOnALine();
        System.out.println(s.maxPoints(p));
    }
}
