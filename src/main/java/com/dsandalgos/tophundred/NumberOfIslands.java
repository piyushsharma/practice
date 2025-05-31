package com.dsandalgos.tophundred;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 *
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * Output: 1
 *
 *
 * Example 2:
 *
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * Output: 3
 */

public class NumberOfIslands {

	public int numIslands(char[][] grid) {
		if(grid == null || grid.length == 0)
			return 0;

		int count = 0;
		// do bfs on the grid when we see 1
		for(int i = 0; i < grid.length; ++i) {
			for(int j = 0; j < grid[0].length; ++j) {

				if(grid[i][j] == '1') {
					bfs(grid, i, j);
					count++;
				}
			}

		}
		return count;
	}

	class GridPoint {
		public int x;
		public int y;
		public GridPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public void bfs(char[][] grid, int i, int j) {
		Queue<GridPoint> q = new LinkedList<>();

		q.add(new GridPoint(i,j));

		while(!q.isEmpty()) {

			GridPoint g = q.remove();

			if(grid[g.x][g.y] == '1') {
				grid[g.x][g.y] = '2';

				// exploreTop
				addToQueue(grid, q, g.x - 1, g.y);

				// exploreBottom
				addToQueue(grid, q, g.x + 1, g.y);

				// exploreLeft
				addToQueue(grid, q, g.x, g.y - 1);

				// exploreRight
				addToQueue(grid, q, g.x, g.y + 1);
			}
		}
	}

	public void addToQueue(char[][] grid, Queue<GridPoint> q, int i, int j) {
		if(i >=0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == '1') {
			q.add(new GridPoint(i,j));
		}
	}

}
