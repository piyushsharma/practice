package com.dsandalgos.hackerrank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Victoria has a tree, T, consisting of N nodes numbered from  1 to N.
 * Each edge from node U to V in tree T has an integer weight, W.
 *
 * Let's define the cost, C, of a path from some node X to some other node Y as the maximum weight (W)
 * for any edge in the unique path from node X to node Y.
 *
 * Victoria wants your help processing Q queries on tree T, where each query contains 2 integers, L and R,
 * such that L <= R. For each query, she wants to print the number of different paths in T that have a cost, C,
 * in the inclusive range [L, R].
 *
 * It should be noted that path from some node X to some other node Y is considered same as path from node Y to X
 * i.e {X, Y} is same as {Y, X}.
 *
 */


public class SuperMaximumCostQueries {

	static class Edge {
		int x;
		int y;
		int cost;

		public Edge(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	/**
	 * find returns the root of the tree containing element id;
	 * we walk up the parent pointers until there is nowhere to go.
	 * we return root.
	 */
	private static int findRoot(int id) {

		// if I am the owner
		if(parent[id] == id) {
			return id;
		}

		return findRoot(parent[id]);
	}


	static int[] parent;
	static int[] sizeOfSet;


	// tree contains 3 columns, node u, node v, cost from u -> v
	// queries contains 2 columns, L, R i.e. the range of costs for which we need to calculate number of paths

	static long[] solve(int n, int[][] tree, int[][] queries) {

		for(int i = 0; i < parent.length; ++i) {
			parent[i] = i;
			sizeOfSet[i] = 1;
		}

		Edge[] edges = new Edge[n-1];
		for(int i = 0; i < n - 1; ++i) {

			int val1 = tree[i][0];
			int val2 = tree[i][1];
			int cost = tree[i][2];

			edges[i] = new Edge(val1, val2, cost);
		}

		Arrays.sort(edges, Comparator.comparingInt(a -> a.cost));

		List<Path> paths = new ArrayList<>();
		paths.add(new Path(0, 0));

		// process n-1 edges
		int i = 0;
		int numberOfPaths = 0;
		while(i < n-1) {


			int cost = edges[i].cost;

			// union nodes that have edges with cost c
			while(i < n-1 && cost == edges[i].cost) {

				int x = findRoot(edges[i].x);
				int y = findRoot(edges[i].y);

				if(x != y) {

					numberOfPaths += sizeOfSet[x] * sizeOfSet[y];

					parent[y] = parent[x];
					sizeOfSet[x] += sizeOfSet[y];
					sizeOfSet[y] = 0;
				}
				++i;
			}

			paths.add(new Path(numberOfPaths, cost));
		}

		long[] result = new long[queries.length];
		for(int q = 0; q < queries.length; ++q) {
			int l = queries[q][0];
			int r = queries[q][1];

			// using the fact that all paths b/w [l, r] = paths [0, r] - paths [0, l - 1]
			// note that paths[0] is set to 0
			long uptoR = binSearch(paths, r);
			long untilL = binSearch(paths,l - 1);
			result[q] = uptoR - untilL;
		}
		return result;
	}


	private static long binSearch(List<Path> paths, int untilCost) {
		int low = 0;
		int high = paths.size() - 1;

		while (low < high - 1) {
			int mid = low + ((high - low) / 2);

			if(paths.get(mid).cost > untilCost) {
				high = mid;
			} else {
				low = mid;
			}
		}

		if(paths.get(high).cost <= untilCost) {
			return paths.get(high).numberOfPaths;
		}

		return paths.get(low).numberOfPaths;
	}


	private static long search(List<Path> paths, int untilCost) {

		int numberOfPaths = 0;
		for(int i = 0; i < paths.size(); ++i) {
			if(paths.get(i).cost > untilCost) {
				break;
			}
			numberOfPaths = paths.get(i).numberOfPaths;
		}
		return numberOfPaths;
	}

	static class Path {
		int numberOfPaths;
		int cost;
		public Path(int numberOfPaths, int cost) {
			this.numberOfPaths = numberOfPaths;
			this.cost = cost;
		}
	}


//	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
//		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//		String[] nq = scanner.nextLine().split(" ");
//
//		int n = Integer.parseInt(nq[0]);
//
//		int q = Integer.parseInt(nq[1]);
//
//		int[][] tree = new int[n-1][3];
//
//		for (int treeRowItr = 0; treeRowItr < n-1; treeRowItr++) {
//			String[] treeRowItems = scanner.nextLine().split(" ");
//			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//			for (int treeColumnItr = 0; treeColumnItr < 3; treeColumnItr++) {
//				int treeItem = Integer.parseInt(treeRowItems[treeColumnItr]);
//				tree[treeRowItr][treeColumnItr] = treeItem;
//			}
//		}
//
//		int[][] queries = new int[q][2];
//
//		for (int queriesRowItr = 0; queriesRowItr < q; queriesRowItr++) {
//			String[] queriesRowItems = scanner.nextLine().split(" ");
//			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//			for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
//				int queriesItem = Integer.parseInt(queriesRowItems[queriesColumnItr]);
//				queries[queriesRowItr][queriesColumnItr] = queriesItem;
//			}
//		}
//
//		int[] result = solve(tree, queries);
//
//		for (int resultItr = 0; resultItr < result.length; resultItr++) {
//			bufferedWriter.write(String.valueOf(result[resultItr]));
//
//			if (resultItr != result.length - 1) {
//				bufferedWriter.write("\n");
//			}
//		}
//
//		bufferedWriter.newLine();
//
//		bufferedWriter.close();
//
//		scanner.close();

		int[][] tree = new int[][]{
			{1, 2, 3},
			{1, 4, 2},
			{2, 5, 6},
			{3, 4, 1}
		};

		parent = new int[5+1];
		sizeOfSet = new int[5+1];

		int[][] q = new int[][] {
			{1, 1},
			{1, 2},
			{2, 3},
			{2, 5},
			{1, 6}
		};

		long[] result = solve(5, tree, q);

		System.out.println("test");


	}
}
