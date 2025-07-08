package com.dsandalgos.graph;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        int n=board.length;
        int[] arr = new int[n*n+1]; // 1D board mapping
        boolean right = true;
        int start = 1;

        // Flatten the board into 1D (Boustrophedon order)
        for(int i = n-1; i>=0; --i) {
            if(right) {
                for(int j = 0; j < n; ++j) {
                    arr[start++] = board[i][j];
                }
            } else {
                for(int j = n-1; j >= 0; --j) {
                    arr[start++] = board[i][j];
                }
            }
            right = !right;
        }

        boolean[] visited = new boolean[n*n+1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1]=true;
        int result = 0; // moves

        // BFS to find shortest path to end
        while(!q.isEmpty()) {
            int size = q.size();
            for(int k = 0; k < size; ++k) {
                int cur= q.poll();
                if(cur == n*n) return result; // reached the last square

                for(int i = cur + 1; i <= Math.min(cur+6, n*n); ++i) { // Try all dice moves (1–6)
                    int d = arr[i] == -1 ? i : arr[i]; // Jump if ladder/snake
                    if(!visited[d]) {
                        visited[d] = true;
                        q.offer(d);
                    }
                }
            }
            result++;
        }

        return -1; // Not reachable
    }
}
