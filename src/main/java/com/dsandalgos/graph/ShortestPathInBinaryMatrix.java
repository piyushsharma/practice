package com.dsandalgos.graph;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    public int shortestPathBinaryMatrix(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        if(m == 0) {
            return 0;
        }

        if(grid[0][0] == 1 || grid[m-1][n-1] == 1) {
            return -1;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        q.add(new int[]{0, 0, 1});
        visited[0][0] = true;

        while(!q.isEmpty()) {

            int[] point = q.remove();
            int r = point[0];
            int c = point[1];
            int pathLen = point[2];


            if(r == m - 1 && c == n - 1) {
                return pathLen;
            }

            for(int a = r - 1; a <= r + 1; ++a) {
                for(int b = c - 1; b <= c + 1; ++b) {
                    if(a >= 0 && a < m && b >= 0 && b < n && !visited[a][b] && grid[a][b] == 0) {
                        visited[a][b] = true;
                        q.add(new int[]{a, b, pathLen + 1});
                    }
                }
            }
        }

        return -1;
    }
}
