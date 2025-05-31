package com.dsandalgos.hackerrank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Kundu is true tree lover. Tree is a connected graph having N vertices and N-1 edges.
 * Today when he got a tree, he colored each edge with one of either red(r) or black(b) color.
 * He is interested in knowing how many triplets(a,b,c) of vertices are there, such that,
 * there is at-least one edge having red color on all the three paths
 * i.e. from vertex a to b, vertex b to c and vertex c to a.
 * Note that (a,b,c), (b,a,c) and all such permutations will be considered as the same triplet.
 *
 * If the answer is greater than 10 pow 9 + 7, print the answer modulo (%) 10 pow 9 + 7.
 *
 * Input Format
 * The first line contains an integer N, i.e., the number of vertices in tree.
 * The next N-1 lines represent edges:
 *      2 space separated integers denoting an edge followed by a color of the edge.
 *      A color of an edge is denoted by a small letter of English alphabet, and it can be either red(r) or black(b).
 *
 * Output Format
 * Print a single number i.e. the number of triplets.
 *
 * Constraints
 * 1 ≤ N ≤ 10 pow 5
 * A node is numbered between 1 to N.
 *
 * Sample Input
 * 5
 * 1 2 b
 * 2 3 r
 * 3 4 r
 * 4 5 b
 *
 * Sample Output
 * 4
 *
 * Explanation
 *
 * Given tree is something like this.
 *
 *     1 ---b--- 2 ---r--- 3 ---r--- 4 ---b--- 5
 *
 * (2,3,4) is one such triplet because on all paths i.e 2 to 3, 3 to 4 and 2 to 4 there
 * is atleast one edge having red color.
 * (2,3,5), (1,3,4) and (1,3,5) are other such triplets.
 * Note that (1,2,3) is NOT a triplet, because the path from 1 to 2 does not have an edge with red color.
 *
 */


public class KunduAndTree {

	static int MOD = 1000000007;

	static int[] parent;
	static int[] sizes;

	private static void merge(int node1, int node2) {
		int p1 = find(node1);
		int p2 = find(node2);

		if(p1 == p2) return;

		if(sizes[p1] >= sizes[p2]) {
			parent[p2] = p1;
			sizes[p1] += sizes[p2];
			sizes[p2] = 0;
		} else {
			parent[p1] = p2;
			sizes[p2] += sizes[p1];
			sizes[p1] = 0;
		}
	}

	private static int find(int n) {
		if(parent[n] == n) return n;

		return find(parent[n]);
	}

	public static void main(String[] args) {
		/* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

		Scanner scanner = new Scanner(System.in);
		Integer N = Integer.parseInt(scanner.nextLine().trim());

		parent = new int[N+1];
		sizes = new int[N+1];
		for(int i = 0; i <= N; ++i) {
			parent[i] = i;
			sizes[i] = 1;
 		}

		// the logic used is to not connect trees with red edges
		// this way we build components that are connected by red edges
		// suppose we build k components and size of each components is s1, s2, .... sk

		// a valid triplet is choosing 3 nodes from the different k components created above
		// this can be done in the following S ways
		// S = 0;
		// for(p = 0; p < k; ++p)
		//      for(q = p+1; q < k; ++q)
		//          for(r = q+1; r < k; ++r)
		//              S += s[p] * s[q] * s[r]

		// select 1 from pth subset, 1 from qth subset and 1 from rth subset
		//
		// this is O(n^3)
		//

		for(int i = 0; i < N-1; ++i) {
			String[] b = scanner.nextLine().trim().split(" ");

			int node1 = Integer.parseInt(b[0]);
			int node2 = Integer.parseInt(b[1]);

			if(b[2].equals("r")) {
				continue;
			}
			merge(node1, node2);
		}


		List<Integer> sizeOfSubsets = new ArrayList<>();
		for(int i = 1; i < sizes.length; ++i) {
			if(sizes[i] != 0) {
				sizeOfSubsets.add(sizes[i]);
			}
		}
		int totalNumberOfSubsets = sizeOfSubsets.size();

		// this solution works but gives us time limit exceeded issue as n^3
		long sum = 0;
		for(int p = 0; p < totalNumberOfSubsets; ++p) {
			for (int q = p + 1; q < totalNumberOfSubsets; ++q) {
				for (int r = q + 1; r < totalNumberOfSubsets; ++r) {
					sum += ( ((sizeOfSubsets.get(p) * sizeOfSubsets.get(q)) % MOD) * sizeOfSubsets.get(r) ) % MOD;
				}
			}
		}
		System.out.println(sum);

		System.out.println("Done");
	}

}
