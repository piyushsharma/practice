package com.dsandalgos.hackerrank;

// https://www.hackerrank.com/challenges/torque-and-development/problem

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoadsAndLibraries {

	static class GraphNode {
		int x;
		boolean visited;
		List<GraphNode> neighbors;

		public GraphNode(int x) {
			this.x = x;
			this.neighbors = new ArrayList<>();
			this.visited = false;
		}
	}

	// Complete the roadsAndLibraries function below.
	static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {


		Map<Integer, GraphNode> graph = new HashMap<>();
		for (int i = 0; i < cities.length; ++i) {
			int c1 = cities[i][0];
			int c2 = cities[i][1];

			GraphNode cg1 = graph.computeIfAbsent(c1, (ignored) -> new GraphNode(c1));
			GraphNode cg2 = graph.computeIfAbsent(c2, (ignored) -> new GraphNode(c2));

			cg1.neighbors.add(cg2);
			cg2.neighbors.add(cg1);
		}

		for (int i = 1; i <= n; ++i) {
			if (!graph.containsKey(i)) {
				graph.put(i, new GraphNode(i));
			}
		}
		long result = 0;
		for (GraphNode gNode : graph.values()) {
			if (gNode.visited == false) {
				// build library and connect as many cities as possible
				long[] c = new long[]{0l};
				dfs(gNode, c);

				if (c_lib < c_road) {
					result += c[0] * c_lib;
				} else {
					result += c_lib;
					result += c_road * (c[0] - 1l);
				}
			}
		}
		return result;
	}

	static void dfs(GraphNode g, long[] count) {
		g.visited = true;
		count[0] += 1l;
		for (GraphNode n : g.neighbors) {
			if (n.visited == false) {
				dfs(n, count);
			}
		}
	}

}