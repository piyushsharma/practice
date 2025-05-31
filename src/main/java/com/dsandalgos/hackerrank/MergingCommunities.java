package com.dsandalgos.hackerrank;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * People connect with each other in a social network. A connection between Person I and Person J is represented as M I J.
 * When two persons belonging to different communities connect, the net effect is the merger of both communities which I and J belongs to.
 *
 * At the beginning, there are N people representing N communities. Suppose person 1 and 2 connected and later 2 and 3 connected,
 * then 1,2, and 3 will belong to the same community.
 *
 * There are two type of queries:
 * 1. M I J - communities containing person I and J merged (if they belong to different communities).
 * 2. Q I - print the size of the community to which person  belongs.
 *
 * Input Format
 *
 * The first line of input will contain integers N and Q, i.e. the number of people and the number of queries.
 * The next Q lines will contain the queries.
 *
 * Constraints :
 * 1 <= N <= 10 pow 5
 * 1 <= Q <= 2 val 10 pow 5
 *
 * Output Format
 *
 * The output of the queries.
 *
 * Sample Input
 *
 * 3 6
 * Q 1
 * M 1 2
 * Q 2
 * M 2 3
 * Q 3
 * Q 2
 * Sample Output
 *
 * 1
 * 2
 * 3
 * 3
 *
 * Initial size of each of the community is 1.
 */

public class MergingCommunities {

	int[] communityOwner;
	int[] sizeOfCommunity;

	public MergingCommunities(int n) {
		communityOwner = new int[n + 1];
		sizeOfCommunity = new int[n + 1];
		for(int i = 1; i <= n; ++i) {
			communityOwner[i] = i;
			sizeOfCommunity[i] = 1;
		}
	}

	private void union(int a, int b) {

		int rootA = findRoot(a);
		int rootB = findRoot(b);

		// already connected
		if(rootA == rootB) {
			return ;
		}

		// otherwise we want to connect to the smaller tree to the root of the bigger tree
		// this is the least expensive way -
		// as we just add one more level to all nodes in smaller tree to reach the root

		/**
		 *  imagine tree
		 *                1
		 *              /
		 *             2
		 * As long as we add single nodes to it, the height never changes since all nodes get added to 1,
		 * eg.          1 ---
		 *             / \   \
		 *            2   3  4
		 *
		 *  Only when we join another tree of height > 1 does the height change for nodes in the smaller tree
		 */

		if(sizeOfCommunity[rootA] >= sizeOfCommunity[rootB]) {
			// make B a subtree of A
			communityOwner[rootB] = rootA;
			sizeOfCommunity[rootA] += sizeOfCommunity[rootB];
		} else {
			communityOwner[rootA] = rootB;
			sizeOfCommunity[rootB] += sizeOfCommunity[rootA];
		}
	}

	/**
	 * find returns the root of the tree containing element id;
	 * we walk up the parent pointers until there is nowhere to go.
	 * we return root.
	 */
	private int findRoot(int id) {

		// if I am the owner
		if(communityOwner[id] == id) {
			return id;
		}

		return findRoot(communityOwner[id]);
	}



	public static void main(String[] args) throws IOException {

		Scanner in = new Scanner(System.in);
		String[] s = in.nextLine().split(" ");
		int numberOfPeople = Integer.parseInt(s[0]);
		int numberOfQueries = Integer.parseInt(s[1]);

		MergingCommunities mergingCommunities = new MergingCommunities(numberOfPeople);
		List<String> result = new ArrayList<>();

		while(numberOfQueries > 0) {
			String[] line = in.nextLine().split(" ");

			if(line[0].equals("M")) {
				int idA = Integer.parseInt(line[1]);
				int idB = Integer.parseInt(line[2]);
				mergingCommunities.union(idA, idB);

			} else if (line[0].equals("Q")) {

				int id = Integer.parseInt(line[1]);

				result.add(Integer.toString(mergingCommunities.sizeOfCommunity[mergingCommunities.findRoot(id)]));

			} else {
				// should not come here
			}

			numberOfQueries--;
		}

		result.forEach(str -> System.out.println(str));
	}

}
