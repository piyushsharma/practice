package com.dsandalgos.graph;
import java.util.*;

public class MostStonesRemoved {

    public int removeStones(int[][] stones) {
        int n = stones.length;

        // Adjacency list to store graph connections
        List<Integer>[] adjacencyList = new List[n];
        for (int i = 0; i < n; i++) {
            adjacencyList[i] = new ArrayList<>();
        }

        // Build the graph: Connect stones that share the same row or column
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    adjacencyList[i].add(j);
                    adjacencyList[j].add(i);
                }
            }
        }

        int cc = 0;
        boolean[] visited = new boolean[n];

        for(int i = 0; i < n; ++i) {
            if(!visited[i]) {
                dfs(i, visited, adjacencyList);
                cc++;
            }
        }
        return n - cc;
    }

    void dfs(int start, boolean[] visited, List<Integer>[] adjacencyList) {
        visited[start] = true;

        for(int n : adjacencyList[start]) {
            if(!visited[n]) {
                dfs(n, visited, adjacencyList);
            }
        }
    }
}
