package com.dsandalgos.math;

import java.util.PriorityQueue;

public class KClosestPointsToOrigin {

    public int[][] kClosest(int[][] points, int k) {

        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (b[2] - a[2]));

        for(int[] d : points) {
            int dist = (d[0] * d[0]) + (d[1] * d[1]);

            if(q.size() < k) {
                q.add(new int[]{d[0], d[1], dist});

            } else if(dist < q.peek()[2]) {

                q.remove();
                q.add(new int[]{d[0], d[1], dist});

            }
        }

        int[][] result = new int[k][2];
        for(int i = 0; i < k; ++i) {
            int[] item = q.remove();
            result[i] = new int[]{item[0], item[1]};
        }

        return result;

    }
}
