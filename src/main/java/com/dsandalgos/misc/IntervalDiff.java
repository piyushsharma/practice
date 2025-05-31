package com.dsandalgos.misc;


import java.util.ArrayList;
import java.util.List;

public class IntervalDiff {


    public int[][] intervalDiff(int[][] A, int[][] B) {
        if(A == null || A.length == 0 || B == null || B.length == 0)
            return new int[][]{};


        List<int[]> res = new ArrayList<>();

        int i = 0, j = 0;
        int startMax, startMin;
        int endMax, endMin;

        int curMin, curMax;

        while(i < A.length && j < B.length){

            startMin = Math.min(A[i][0], B[j][0]);
            startMax = Math.max(A[i][0], B[j][0]);

            endMax = Math.max(A[i][1], B[j][1]);
            endMin = Math.min(A[i][1], B[j][1]);

            if(startMin < startMax) {
                res.add(new int[]{startMin, startMax - 1});
            }

            if(endMin < endMax) {
                res.add(new int[]{endMin, endMax - 1});
            }

            ++i;
            ++j;
        }

        return res.toArray(new int[res.size()][2]);
    }

    public static void main(String[] args) {
        int[][] A = new int[][]{ {1, 5}, {7, 10}, {12,14}};

        int[][] B = new int[][]{ {2, 6}, {10, 10}};



        int[][] result = new IntervalDiff().intervalDiff(A, B);
        System.out.println("donw");

    }
}
