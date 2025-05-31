package com.dsandalgos.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BfsShortestReach {



	static class GraphNode {
		int val;
		List<GraphNode> neighbors;
		GraphNode(int x) {
			this.val = x;
			this.neighbors = new ArrayList<>();
		}
	}


	// Complete the bfs function below.
	static int[] bfs(int n, int m, int[][] edges, int s) {

		Map<Integer, GraphNode> graph = new HashMap<>();

		for(int i = 1; i <= n; ++i) {
			graph.put(i, new GraphNode(i));
		}

		for(int i = 0; i < m; ++i) {
			GraphNode u = graph.get(edges[i][0]);
			GraphNode v = graph.get(edges[i][1]);

			u.neighbors.add(v);
			v.neighbors.add(u);
		}

		boolean[] visited = new boolean[n+1];

		GraphNode start = graph.get(s);
		Queue<GraphNode> q = new LinkedList<>();
		q.add(start);
		Queue<Integer> costQueue = new LinkedList<>();
		costQueue.add(0);


		Map<Integer, Integer> costToDest = new HashMap<>();

		while (!q.isEmpty()) {

			GraphNode node = q.remove();
			int cost = costQueue.remove();

			if(node.val != s) {
				costToDest.put(node.val, cost);
			}

			visited[node.val] = true;

			for(GraphNode neighbor : node.neighbors) {

				if(visited[neighbor.val] == false) {
					// add to queue

					q.add(neighbor);
					costQueue.add(cost + 6);
				}
			}
		}

		int[] result = new int[n-1];
		int rIndex = 0;
		for(int i = 1; i <= n; ++i) {
			if(i != s) {
				Integer cost = costToDest.get(i);
				if(cost == null) {
					cost = -1;
				}
				result[rIndex] = cost.intValue();
				++rIndex;
			}
		}

		return result;
	}

}
