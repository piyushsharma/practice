package com.dsandalgos.graph;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*
For a undirected graph with tree characteristics, we can choose any node as the root.
The result graph is then a rooted tree. Among all possible rooted trees,
those with minimum height are called minimum height trees (MHTs).
Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1.
You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example 1:

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3
return [1]

Example 2:

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5
return [3, 4]

Show Hint
Note:

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.”

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

*/


public class MinimumHeightTree {

    List<Integer>[] adjList;

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if(n <= 0) return result;
        if(n == 1) {
            result.add(0);
            return result;
        }

        adjList = new List[n];
        for(int i = 0; i < n; ++i) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] pair : edges) {

            int u = pair[0];
            int v = pair[1];

            adjList[u].add(v);
            adjList[v].add(u);
        }

        int[] dist = new int[n];
        int[] prev = new int[n];
        dfs(0, n, dist, prev);

        int maxDepthNode = 0;
        for(int i = 0; i < n; ++i) {
            if(dist[i] > dist[maxDepthNode]) {
                maxDepthNode = i;
            }
        }

        int[] secondDist = new int[n];
        dfs(maxDepthNode, n, secondDist, prev);

        int dNode = 0;
        for(int i = 0; i < n; ++i) {
            if(secondDist[i] > secondDist[dNode]) {
                dNode = i;
            }
        }

        // add all nodes in the path starting from maxDepthNode as starting point
        // of the graph to reach dNode (basically use prev to trace back nodes until we reach maxDepthNode
        // for which prev == -1
        while (dNode != -1) {
            result.add(dNode);
            dNode = prev[dNode];
        }

        if(result.size() % 2 == 1) return Arrays.asList(result.get(result.size()/2));

        return Arrays.asList(result.get((result.size() - 1)/ 2), result.get(result.size()/2));
    }

    private void dfs(int node, int n, int[] dist, int[] prev) {
        boolean[] visited = new boolean[n];
        visited[node] = true;

        Stack<Integer> s = new Stack<>();
        s.push(node);
        dist[node] = 0;
        prev[node] = -1;

        while(!s.isEmpty()) {
            int topNode = s.pop();
            for (Integer neighborNode : adjList[topNode]) {
                if (!visited[neighborNode]) {
                    visited[neighborNode] = true;
                    dist[neighborNode] = dist[topNode] + 1;
                    s.push(neighborNode);
                    prev[neighborNode] = topNode;
                }
            }
        }
    }


    // Gets TLE on leetcode
    // Inspired by =>
    /*
        To find the graph centre/center of an undirected tree graph you could:

        1. Do a DFS to find a list of all leaf nodes O(n)
        2. Remove all these leaf nodes from the graph and note during the deletion which new nodes become leaf nodes
        3. Repeat step 2 until the graph is completely deleted
        The node/nodes deleted in the last stage of the algorithm will be the graph centres of your tree.
        Each node is deleted once, so this whole process can be done in O(n).
     */
    public List<Integer> findMinHeightTreesBruteForce(int n, int[][] edges) {

        List<Integer> result = new ArrayList<>();
        if(n <= 0) return result;
        if(n == 1) {
            result.add(0);
            return result;
        }

        int numberOfedges = edges.length;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < numberOfedges; ++i) {

            for (int j = 0; j < 2; ++j) {
                HashSet<Integer> neighbors = map.get(edges[i][j]);

                if (neighbors == null) {
                    map.put(edges[i][j], new HashSet<>());
                }

                if (j == 0)
                    map.get(edges[i][j]).add(edges[i][1]);
                else
                    map.get(edges[i][j]).add(edges[i][0]);
            }
        }

        while(true) {
            List<HashSet<Integer>> values = new ArrayList<>(map.values());
            boolean onlyLeafNodes = true;
            for (HashSet<Integer> h : values) {
                if (h.size() > 1) {
                    onlyLeafNodes = false;
                    break;
                }
            }
            if(onlyLeafNodes) {
                break;
            }
            map = pruneLeafNodes(map);
        }

        for(Integer center : map.keySet()) {
            result.add(center);
        }
        return result;

    }

    private HashMap<Integer, HashSet<Integer>> pruneLeafNodes(HashMap<Integer, HashSet<Integer>> map) {

        HashSet<Integer> visited = new HashSet<>();

        Iterator it = map.entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry<Integer, HashSet> pair = (Map.Entry) it.next();

            Integer gn = pair.getKey();
            if(visited.contains(gn)) {
                continue;
            }

            HashSet<Integer> neighbors = pair.getValue();
            if (neighbors.size() == 1) {
                it.remove();
                visited.add(gn);
            }
        }

        for (Integer node : map.keySet()) {
            map.get(node).removeAll(visited);
        }

        return map;
    }


    public static void main(String[] args) {

        MinimumHeightTree m = new MinimumHeightTree();

        int[][] edges = new int[][]{{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}};
        List<Integer> r = m.findMinHeightTrees(6, edges);
        System.out.println(r.toString());

        int[][] edges1 = new int[][]{{1, 0}, {1, 2}, {1, 3}};
        List<Integer> rr = m.findMinHeightTrees(4, edges1);
        System.out.println(rr.toString());

        int[][] edges2 = new int[][]{{0,1},{0,2},{0,3},{3,4},{4,5}};
        List<Integer> res = m.findMinHeightTrees(6, edges2);
        System.out.println(res.toString());
    }

}
