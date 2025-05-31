package com.dsandalgos.misc;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D array binaryMatrix of 0s and 1s, implement a function
 * getNumberOfIslands that returns the number of islands of 1s in binaryMatrix.
 *
 * An island is defined as a group of adjacent values that are all 1s.
 * A cell in binaryMatrix is considered adjacent to another cell
 * if they are next to each either on the same row or column.
 * Note that two values of 1 are not part of the same island
 * if they’re sharing only a mutual “corner” (i.e. they are diagonally neighbors).
 *
 * Explain and code the most efficient solution possible and analyze its time and space complexities.
 *
 * Example:
 * input:  binaryMatrix = [ [0,    1,    0,    1,    0],
 *                          [0,    0,    1,    1,    1],
 *                          [1,    0,    0,    1,    0],
 *                          [0,    1,    1,    0,    0],
 *                          [1,    0,    1,    0,    1] ]
 *
 * output: 6 # since this is the number of islands in binaryMatrix.
 *
 * Constraints:
 *
 * [time limit] 5000ms
 * [input] array.array.int binaryMatrix
 *              1 ≤ binaryMatrix.length ≤ 100
 *              1 ≤ binaryMatrix[i].length ≤ 100
 * [output] integer
 */

public class IslandCount {


	static int getNumberOfIslands(int[][] binaryMatrix) {
		int result = 0;
		if(binaryMatrix == null || binaryMatrix.length == 0) {
			return result;
		}

		for(int i = 0; i < binaryMatrix.length; ++i) {
			for(int j = 0; j < binaryMatrix[0].length; ++j) {
				if(binaryMatrix[i][j] == 1) {
					markIsland(i, j, binaryMatrix);
					++result;
				}
			}
		}
		return result;
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void markIsland(int i, int j, int[][] binaryMatrix) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(i, j));

		while (!q.isEmpty()) {
			Point p = q.remove();
			int curI = p.x;
			int curJ = p.y;

			if(binaryMatrix[i][j] == 1) {
				binaryMatrix[curI][curJ] = -1;
				// left
				pushIfValid(binaryMatrix, q, curI, curJ - 1);
				// right
				pushIfValid(binaryMatrix, q, curI, curJ + 1);
				// top
				pushIfValid(binaryMatrix, q, curI - 1, curJ);
				// bottom
				pushIfValid(binaryMatrix, q, curI + 1, curJ);
			}
		}
	}

	private static void pushIfValid(int[][] binaryMatrix, Queue<Point> q, int i, int j) {
		if(i >=0 && i < binaryMatrix.length && j >=0 && j < binaryMatrix[0].length) {
			q.add(new Point(i,j));
		}
	}


	static int getNumberOfIslandsWithBFS(int[][] binaryMatrix) {
		if(binaryMatrix == null || binaryMatrix.length == 0) {
			return 0;
		}

		// your code goes here
		int rowLength = binaryMatrix.length;
		int colLength = binaryMatrix[0].length;

		boolean[][] visited = new boolean[rowLength][colLength];
		int result = 0;
		for(int i = 0; i < rowLength; ++i) {
			for(int j = 0; j < colLength; ++j) {

				if(visited[i][j] == false) {
					visited[i][j] = true;
					if(binaryMatrix[i][j] == 1) {
						bfs(i, j, binaryMatrix, visited);
						++result;
					}
				}
			}
		}
		return result;
	}

	private static void bfs(int i, int j, int[][] b, boolean[][] visited) {

		if(i-1 >=0 && visited[i-1][j] == false && b[i-1][j] == 1) {
			visited[i-1][j] = true;
			bfs(i-1, j, b, visited);
		}

		if(i+1 < b.length && visited[i+1][j] == false && b[i+1][j] == 1) {
			visited[i+1][j] = true;
			bfs(i+1, j, b, visited);
		}

		if(j-1 >= 0 && visited[i][j-1] == false && b[i][j-1] == 1) {
			visited[i][j-1] = true;
			bfs(i, j-1, b, visited);
		}

		if(j+1 < b[0].length && visited[i][j+1] == false && b[i][j+1] == 1) {
			visited[i][j+1] = true;
			bfs(i, j+1, b, visited);
		}
	}
}
