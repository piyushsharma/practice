package com.dsandalgos.array;

import java.util.PriorityQueue;

public class KthSmallestInASortedMatrix {

    public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        q.add(new int[]{0, 0, matrix[0][0]});
        visited[0][0] = true;
        int count = 0;

        while(!q.isEmpty()) {

            int[] cur = q.remove();
            ++count;

            if(count == k) {
                return cur[2];
            }

            int r = cur[0];
            int c = cur[1];


            if(r + 1 < m && !visited[r+1][c]) {
                visited[r+1][c] = true;
                q.add(new int[]{r+1, c, matrix[r+1][c]});
            }

            if(c + 1 < n && !visited[r][c+1]) {
                visited[r][c+1] = true;
                q.add(new int[]{r, c+1, matrix[r][c+1]});
            }

        }

        return -1;
    }
}
