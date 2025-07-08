package com.dsandalgos.graph;

import java.util.HashMap;
import java.util.List;
import java.util.*;

public class LargestColorValue {



    public int largestPathValue(String colors, int[][] edges) {

        Map<Integer, List<Integer>> graph = new HashMap<>();
        int n = colors.length();
        int[] indegree = new int[n];

        for(int i = 0; i < n; ++i) {
            graph.put(i, new ArrayList<>());
        }

        for(int i = 0; i < edges.length; ++i) {
            int[] e = edges[i];
            indegree[e[1]]++;
            graph.get(e[0]).add(e[1]);
        }

        int[][] dpCache = new int[n][26];
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; ++i) {
            if(indegree[i] == 0) {
                q.add(i);
            }
            int colorIndex = colors.charAt(i) - 'a';
            dpCache[i][colorIndex] = 1;
        }

        int result = 0;
        int visited = 0;

        while(!q.isEmpty()) {

            int node = q.poll();
            visited++;

            for(int neighbor : graph.get(node)) {
                for(int i = 0; i < 26; ++i) {
                    int increment = colors.charAt(neighbor) - 'a' == i ? 1 : 0;
                    dpCache[neighbor][i] = Math.max(dpCache[neighbor][i], dpCache[node][i] + increment);
                }

                indegree[neighbor]--;
                if(indegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }

            for(int val : dpCache[node]) {
                result = Math.max(result, val);
            }
        }

        return visited == n ? result : -1;
    }
}
